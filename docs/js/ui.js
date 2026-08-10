// ui.js — Camada de apresentação: render, eventos, log/toast, modais, som, mobile.
(function () {
  "use strict";

  // ---------- refs ----------
  const $ = (id) => document.getElementById(id);
  const telaInicio = $("tela-inicio");
  const inicioBotoes = $("inicio-botoes");
  const jogoEl = $("jogo");
  const locaisEl = $("locais");
  const logEl = $("log");
  const barrasEl = $("stats-barras");

  const overlayLocal = $("overlay-local");
  const localTitulo = $("local-titulo");
  const localAcoes = $("local-acoes");

  const modalEl = $("modal");
  const modalMsg = $("modal-msg");
  const modalInput = $("modal-input");
  const modalBotoes = $("modal-botoes");
  const toastEl = $("toast");

  // Stats exibidos como barra /20 (ruimAlto = quanto maior, pior).
  const BARRAS = [
    { key: "inteligencia", nome: "Inteligência", ruimAlto: false },
    { key: "fome", nome: "Fome", ruimAlto: true },
    { key: "sede", nome: "Sede", ruimAlto: true },
    { key: "banheiro", nome: "Banheiro", ruimAlto: true },
    { key: "depressao", nome: "Depressão", ruimAlto: true },
    { key: "forca", nome: "Força", ruimAlto: false },
  ];

  let toastTimer = null;

  // ---------- Modais (Promise-based) ----------
  function abrirModal(opts) {
    // opts: { msg, input?, botoes:[{label, valor}] }
    return new Promise((resolve) => {
      modalMsg.textContent = opts.msg;
      if (opts.input) {
        modalInput.classList.remove("oculto");
        modalInput.value = "";
      } else {
        modalInput.classList.add("oculto");
      }
      modalBotoes.innerHTML = "";
      const finalizar = (valor) => {
        modalEl.classList.add("oculto");
        modalEl.setAttribute("aria-hidden", "true");
        modalInput.onkeydown = null;
        resolve(valor);
      };
      opts.botoes.forEach((b) => {
        const btn = document.createElement("button");
        btn.className = "menu-btn";
        btn.type = "button";
        btn.textContent = b.label;
        btn.addEventListener("click", () => {
          finalizar(opts.input ? modalInput.value : b.valor);
        });
        modalBotoes.appendChild(btn);
      });
      modalEl.classList.remove("oculto");
      modalEl.setAttribute("aria-hidden", "false");
      if (opts.input) {
        modalInput.onkeydown = (e) => {
          if (e.key === "Enter") finalizar(modalInput.value);
        };
        setTimeout(() => modalInput.focus(), 50);
      }
    });
  }
  const alerta = (msg) => abrirModal({ msg: msg, botoes: [{ label: "OK", valor: true }] });
  const confirma = (msg) =>
    abrirModal({ msg: msg, botoes: [{ label: "Sim", valor: true }, { label: "Não", valor: false }] });
  const perguntar = (msg) => abrirModal({ msg: msg, input: true, botoes: [{ label: "OK", valor: true }] });

  // ---------- Toast ----------
  function mostrarToast(msg, tipo) {
    toastEl.textContent = msg;
    toastEl.className = "toast entrada" + (tipo ? " toast-" + tipo : "");
    if (toastTimer) clearTimeout(toastTimer);
    toastTimer = setTimeout(() => {
      toastEl.className = "toast saida" + (tipo ? " toast-" + tipo : "");
      setTimeout(() => toastEl.classList.add("oculto"), 260);
    }, 2200);
  }

  // ---------- Log ----------
  function adicionarLog(msg, tipo) {
    const li = document.createElement("li");
    li.className = "log-" + (tipo || "ok");
    const badge = document.createElement("span");
    badge.className = "badge";
    badge.textContent = tipo === "aviso" ? "!" : tipo === "evento" ? "★" : "•";
    li.appendChild(badge);
    li.appendChild(document.createTextNode(msg));
    logEl.appendChild(li);
    logEl.scrollTop = logEl.scrollHeight;
    // limita o histórico
    while (logEl.children.length > 60) logEl.removeChild(logEl.firstChild);
  }

  // ---------- Render dos stats ----------
  function construirBarras() {
    barrasEl.innerHTML = "";
    BARRAS.forEach((b) => {
      const item = document.createElement("div");
      item.className = "barra-item";
      item.innerHTML =
        '<span class="barra-nome">' + b.nome + "</span>" +
        '<span class="barra-track"><span class="barra-fill" id="fill-' + b.key + '"></span></span>' +
        '<span class="barra-valor" id="val-' + b.key + '"></span>';
      barrasEl.appendChild(item);
    });
  }

  function render(s) {
    if (!s) return;
    $("v-nome").textContent = s.nome;
    $("v-dia").textContent = s.dia;
    $("v-level").textContent = s.level;
    $("v-dinheiro").textContent = s.dinheiro;
    BARRAS.forEach((b) => {
      const v = s[b.key];
      const pct = Math.max(0, Math.min(100, (v / 20) * 100));
      const fill = $("fill-" + b.key);
      const val = $("val-" + b.key);
      if (fill) {
        fill.style.width = pct + "%";
        fill.classList.toggle("perigo", b.ruimAlto ? v >= 15 : v <= 3);
      }
      if (val) val.textContent = v + "/20";
    });
  }

  // ---------- Locais ----------
  function construirLocais() {
    locaisEl.innerHTML = "";
    ORDEM_LOCAIS.forEach((id) => {
      const local = id === "casa" ? { botao: "Casa" } : LOCAIS[id];
      const btn = document.createElement("button");
      btn.className = "local-btn";
      btn.type = "button";
      btn.textContent = local.botao;
      btn.addEventListener("click", () => {
        Sfx.ensureCtx();
        abrirLocal(id);
      });
      locaisEl.appendChild(btn);
    });
  }

  function abrirLocal(id) {
    const local = Game.getLocal(id);
    localTitulo.textContent = local.titulo;
    localAcoes.innerHTML = "";
    local.acoes.forEach((acao, i) => {
      const btn = document.createElement("button");
      btn.className = "acao-btn";
      btn.type = "button";
      btn.textContent = acao.label;
      btn.addEventListener("click", () => {
        Sfx.ensureCtx();
        Sfx.clique();
        Game.executarAcao(id, i);
      });
      localAcoes.appendChild(btn);
    });
    overlayLocal.dataset.local = id;
    overlayLocal.classList.remove("oculto");
    overlayLocal.setAttribute("aria-hidden", "false");
  }

  function fecharLocal() {
    overlayLocal.classList.add("oculto");
    overlayLocal.setAttribute("aria-hidden", "true");
    overlayLocal.dataset.local = "";
  }

  // ---------- Telas ----------
  function mostrarInicio() {
    fecharLocal();
    jogoEl.classList.add("oculto");
    telaInicio.classList.remove("oculto");
    const saved = Storage.carregar();
    inicioBotoes.innerHTML = "";
    if (saved) {
      const bc = document.createElement("button");
      bc.className = "menu-btn";
      bc.type = "button";
      bc.textContent = "Continuar (" + saved.nome + " — Dia " + saved.dia + ")";
      bc.addEventListener("click", () => { Sfx.ensureCtx(); continuarJogo(saved); });
      inicioBotoes.appendChild(bc);
    }
    const bn = document.createElement("button");
    bn.className = "menu-btn";
    bn.type = "button";
    bn.textContent = "Novo Jogo";
    bn.addEventListener("click", () => { Sfx.ensureCtx(); novoJogo(); });
    inicioBotoes.appendChild(bn);
  }

  function entrarNoJogo() {
    telaInicio.classList.add("oculto");
    jogoEl.classList.remove("oculto");
  }

  async function novoJogo() {
    const nome = await perguntar("Digite seu nome:");
    if (nome === null || nome === undefined) return; // cancelado
    construirLocais();
    entrarNoJogo();
    Game.iniciar(nome.trim());
    adicionarLog("Novo jogo iniciado. Boa sorte, " + (nome.trim() || "meu filho") + "!", "evento");
  }

  function continuarJogo(saved) {
    construirLocais();
    entrarNoJogo();
    Game.carregar(saved);
    adicionarLog("Jogo carregado (Dia " + saved.dia + ").", "evento");
  }

  // ---------- Eventos do Game ----------
  Game.on("render", render);

  Game.on("log", (e) => {
    adicionarLog(e.msg, e.tipo);
    mostrarToast(e.msg, e.tipo === "aviso" ? "aviso" : undefined);
    if (e.tipo === "aviso") Sfx.aviso();
  });

  Game.on("fimDia", (msg) => {
    Sfx.fimDia();
    adicionarLog(msg, "evento");
    mostrarToast(msg, "evento");
  });

  Game.on("morte", async (msg) => {
    Sfx.morte();
    fecharLocal();
    adicionarLog("☠ " + msg, "aviso");
    await alerta(msg);
  });

  Game.on("vitoria", async (status) => {
    Sfx.vitoria();
    fecharLocal();
    await alerta("Parabéns, você sobreviveu uma fucking semana nesse jogo lixo!!!");
    await alerta("Status:\n" + status);
    const cont = await confirma("Quer continuar?");
    if (cont) {
      Game.continuarAposVitoria();
      adicionarLog("Nova semana começou!", "evento");
    } else {
      await alerta("flw tmj");
      Storage.limpar();
      mostrarInicio();
    }
  });

  // ---------- Barra superior ----------
  $("btn-novo").addEventListener("click", async () => {
    Sfx.ensureCtx();
    if (!jogoEl.classList.contains("oculto")) {
      const ok = await confirma("Começar um novo jogo? O progresso atual será perdido.");
      if (!ok) return;
    }
    novoJogo();
  });

  $("btn-sair").addEventListener("click", async () => {
    const ok = await confirma("Tem certeza que quer sair?");
    if (ok) {
      await alerta("flw tmj");
      mostrarInicio();
    }
  });

  const btnMute = $("btn-mute");
  function atualizarIconeMute() {
    btnMute.textContent = Sfx.isMudo() ? "🔇" : "🔊";
  }
  btnMute.addEventListener("click", () => {
    Sfx.ensureCtx();
    Sfx.toggleMute();
    atualizarIconeMute();
  });
  atualizarIconeMute();

  // Fechar overlay do local (botão ✕ ou clique no fundo).
  $("local-fechar").addEventListener("click", fecharLocal);
  overlayLocal.addEventListener("click", (e) => {
    if (e.target === overlayLocal) fecharLocal();
  });

  // ---------- Boot ----------
  construirBarras();
  mostrarInicio();
})();
