// audio.js — Efeitos sonoros sintetizados via WebAudio (sem arquivos binários).
// O jogo original não tinha som; isto é uma melhoria opcional com toggle de mute.
const Sfx = (function () {
  let ctx = null;
  let mudo = Storage.getMute();

  function ensureCtx() {
    if (!ctx) {
      const AC = window.AudioContext || window.webkitAudioContext;
      if (!AC) return null;
      ctx = new AC();
    }
    if (ctx.state === "suspended") ctx.resume();
    return ctx;
  }

  // Toca uma nota simples.
  function tom(freq, dur, tipo, vol, delay) {
    if (mudo) return;
    const c = ensureCtx();
    if (!c) return;
    const t0 = c.currentTime + (delay || 0);
    const osc = c.createOscillator();
    const gain = c.createGain();
    osc.type = tipo || "square";
    osc.frequency.setValueAtTime(freq, t0);
    gain.gain.setValueAtTime(0.0001, t0);
    gain.gain.exponentialRampToValueAtTime(vol || 0.15, t0 + 0.01);
    gain.gain.exponentialRampToValueAtTime(0.0001, t0 + dur);
    osc.connect(gain).connect(c.destination);
    osc.start(t0);
    osc.stop(t0 + dur + 0.02);
  }

  // Efeitos nomeados.
  function clique() { tom(220, 0.06, "square", 0.12); }
  function ok() { tom(523, 0.08, "triangle", 0.14); tom(784, 0.10, "triangle", 0.12, 0.06); }
  function aviso() { tom(180, 0.14, "sawtooth", 0.12); }
  function fimDia() { tom(392, 0.10, "triangle", 0.14); tom(587, 0.14, "triangle", 0.13, 0.10); }
  function morte() {
    tom(300, 0.18, "sawtooth", 0.16);
    tom(200, 0.22, "sawtooth", 0.16, 0.16);
    tom(110, 0.35, "sawtooth", 0.16, 0.34);
  }
  function vitoria() {
    const notas = [523, 659, 784, 1047];
    notas.forEach((f, i) => tom(f, 0.16, "triangle", 0.16, i * 0.14));
  }

  function toggleMute() {
    mudo = !mudo;
    Storage.setMute(mudo);
    return mudo;
  }
  function isMudo() { return mudo; }

  return {
    clique: clique, ok: ok, aviso: aviso, fimDia: fimDia,
    morte: morte, vitoria: vitoria, toggleMute: toggleMute, isMudo: isMudo,
    ensureCtx: ensureCtx,
  };
})();
