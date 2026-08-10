# SENAI Simulator

Um "life simulator" de humor brasileiro: sobreviva 7 dias gerenciando fome, sede, banheiro,
depressão, inteligência, força e dinheiro, escolhendo atividades pela cidade (Escola, Boteco,
SENAI, Apple, Academia, LanHouse, Casa e Imobiliária).

### ▶️ [Jogar agora](https://theoaked.github.io/SenaiSimulator/)

## Sobre o projeto

Originalmente feito em **Java Swing** (em `src/`), o jogo foi portado para uma **aplicação web**
(HTML/CSS/JS puro, sem build) que roda no GitHub Pages, preservando as mecânicas, valores e
textos do original com visual modernizado, log de ações não-bloqueante, salvamento automático
em `localStorage`, suporte a mobile/touch e efeitos sonoros.

- Versão web: [`docs/`](docs/) — veja [docs/README.md](docs/README.md) para detalhes e como rodar localmente.
- Versão original em Java: [`src/`](src/) e `senai_simulator.jar`.

## Rodar a versão web localmente

```bash
python -m http.server 8000 --directory docs
# abra http://127.0.0.1:8000
```
