// storage.js — Persistência do jogo em localStorage (substitui o DaoJogo/CSV do original).
const Storage = (function () {
  const CHAVE = "senai_simulator_save";
  const CHAVE_MUTE = "senai_simulator_mute";

  function disponivel() {
    try {
      const t = "__t";
      localStorage.setItem(t, t);
      localStorage.removeItem(t);
      return true;
    } catch (e) {
      return false;
    }
  }

  function salvar(state) {
    if (!disponivel() || !state) return;
    try { localStorage.setItem(CHAVE, JSON.stringify(state)); } catch (e) {}
  }

  function carregar() {
    if (!disponivel()) return null;
    try {
      const raw = localStorage.getItem(CHAVE);
      if (!raw) return null;
      const s = JSON.parse(raw);
      // validação mínima
      if (s && typeof s.nome === "string" && typeof s.level === "number") return s;
      return null;
    } catch (e) {
      return null;
    }
  }

  function limpar() {
    if (!disponivel()) return;
    try { localStorage.removeItem(CHAVE); } catch (e) {}
  }

  function getMute() {
    if (!disponivel()) return false;
    return localStorage.getItem(CHAVE_MUTE) === "1";
  }

  function setMute(v) {
    if (!disponivel()) return;
    try { localStorage.setItem(CHAVE_MUTE, v ? "1" : "0"); } catch (e) {}
  }

  return { salvar: salvar, carregar: carregar, limpar: limpar, getMute: getMute, setMute: setMute };
})();
