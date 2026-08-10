# SENAI Simulator — versão Web

Porte web (HTML/CSS/JS puro, sem build) do "life simulator" originalmente feito em
Java Swing. Preserva as mecânicas, valores e textos do original, com visual modernizado,
log de ações não-bloqueante, salvamento automático, suporte a mobile/touch e efeitos sonoros.

## Rodar localmente

Basta abrir `index.html` no navegador. Para servir como um site (recomendado):

```bash
# a partir da raiz do repositório
python -m http.server 8000 --directory docs
# depois abra http://localhost:8000
```

## Publicar no GitHub Pages

1. Faça commit/push desta pasta `docs/` no branch `master`.
2. No GitHub: **Settings → Pages**.
3. Em **Build and deployment → Source**, escolha **Deploy from a branch**.
4. Selecione o branch `master` e a pasta **`/docs`**. Salve.
5. Aguarde alguns minutos; o jogo ficará disponível na URL indicada pelo GitHub.

## Estrutura

```
docs/
├── index.html         # página única
├── css/style.css      # estilo (cores/fonte/layout responsivo)
├── js/
│   ├── data.js        # locais e ações (portado 1:1 do Java)
│   ├── game.js        # estado + loop central (atualizar)
│   ├── storage.js     # save/load em localStorage
│   ├── audio.js       # efeitos sonoros (WebAudio) + mute
│   └── ui.js          # render, eventos, log/toast, modais
└── assets/            # Fundo.jpg e Fundo2.jpg (do projeto original)
```
