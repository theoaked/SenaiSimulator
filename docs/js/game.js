// game.js — Estado do jogo e loop central.
// Espelha duzao.model.GameModel + duzao.view.JanelaJogo (init/reset/atualizar).
// Emite eventos que a camada de UI (ui.js) escuta: render, log, morte, fimDia, vitoria.

const Game = (function () {
  let state = null;
  const listeners = {};

  function on(evt, fn) { (listeners[evt] = listeners[evt] || []).push(fn); }
  function emit(evt, payload) { (listeners[evt] || []).forEach((fn) => fn(payload)); }

  // Estado inicial — fiel a JanelaJogo.inicializarComponentes e ao bloco de reset.
  function novoEstado(nome) {
    return {
      nome: nome,
      level: 1,
      inteligencia: 0,
      fome: 0,
      sede: 0,
      banheiro: 0,
      depressao: 0,
      dinheiro: nome === "Duzao" ? 9000 : 5, // easter egg do original
      forca: 0,
      casaAtual: 1,
      varAux: 0, // == level - 1
      dia: 1,
    };
  }

  function iniciar(nome) {
    state = novoEstado(nome);
    Storage.salvar(state);
    emit("render", state);
  }

  function carregar(saved) {
    state = saved;
    emit("render", state);
  }

  // Reset em caso de morte — preserva apenas o nome (fiel ao original).
  function resetMorte() {
    state = novoEstado(state.nome);
  }

  function getState() { return state; }

  // Resolve o local; "casa" depende de casaAtual (fiel ao switch de btCasa).
  function getLocal(id) {
    if (id === "casa") return LOCAIS["casa" + state.casaAtual];
    return LOCAIS[id];
  }

  function log(msg, tipo) { emit("log", { msg: msg, tipo: tipo || "ok" }); }

  // Executa uma ação de um local. Retorna true se foi aplicada.
  function executarAcao(localId, index) {
    const local = getLocal(localId);
    const acao = local.acoes[index];

    // Imobiliária: compra de casa (checagens especiais do original).
    if (acao.especial === "comprarCasa") {
      if (state.casaAtual === acao.casa) { log("Você já mora aqui!", "aviso"); return false; }
      if (state.dinheiro < acao.preco) { log(SEM_DINHEIRO, "aviso"); return false; }
      state.level += 1;
      state.dinheiro -= acao.preco;
      state.casaAtual = acao.casa;
      log(acao.msg, "ok");
      posAcao();
      return true;
    }

    // Requisito de stat (ex.: Int >= 35 p/ programador).
    if (acao.requisito && state[acao.requisito.stat] < acao.requisito.min) {
      log(acao.requisito.falha, "aviso");
      return false;
    }
    // Custo em dinheiro.
    if (acao.custo && state.dinheiro < acao.custo.min) {
      log(acao.custo.falha, "aviso");
      return false;
    }

    state.level += 1; // toda ação bem-sucedida sobe 1 (fiel ao original)
    const efeitos = acao.efeitos || {};
    for (const stat in efeitos) state[stat] += efeitos[stat];

    if (acao.msgExtra) log(acao.msgExtra, "ok"); // ex.: caça-níquel "Você Perdeu!"
    log(acao.msg, "ok");
    posAcao();
    return true;
  }

  function posAcao() {
    atualizar();
    if (state) Storage.salvar(state);
    emit("render", state);
  }

  // Loop central — ordem fiel a JanelaJogo.atualizar.
  function atualizar() {
    // 1) Mortes (na ordem do original). Ao morrer: mostra msg e reseta.
    for (let i = 0; i < MORTES.length; i++) {
      if (MORTES[i].cond(state)) {
        emit("morte", MORTES[i].msg);
        resetMorte();
        emit("render", state);
        return;
      }
    }
    // 3) Fim de dia a cada 10 jogadas.
    if (state.level >= state.varAux + 10) {
      emit("fimDia", "Você efetuou 10 jogadas, o dia terminou!");
      state.varAux = state.level;
      state.dia += 1;
    }
    // 4) Vitória no dia 7.
    if (state.dia >= 7) {
      emit("vitoria", statusTexto());
    }
  }

  // Continuar após vencer a semana (fiel: apenas o dia volta a 1).
  function continuarAposVitoria() {
    state.dia = 1;
    Storage.salvar(state);
    emit("render", state);
  }

  function statusTexto() {
    return (
      "Nome -> " + state.nome + "\n" +
      "Level -> " + state.level + "\n" +
      "Sede -> " + state.sede + "/20\n" +
      "Banheiro -> " + state.banheiro + "/20\n" +
      "Depressão -> " + state.depressao + "/20\n" +
      "Fome -> " + state.fome + "/20\n" +
      "Força -> " + state.forca + "/20\n" +
      "Inteligencia -> " + state.inteligencia + "/20\n" +
      "Dinheiro -> " + state.dinheiro
    );
  }

  return {
    on: on,
    iniciar: iniciar,
    carregar: carregar,
    getState: getState,
    getLocal: getLocal,
    executarAcao: executarAcao,
    continuarAposVitoria: continuarAposVitoria,
  };
})();
