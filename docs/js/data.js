// data.js — Definição data-driven dos locais e ações.
// Fonte da verdade: portado 1:1 de src/duzao/view/Janela*.java (valores e mensagens
// preservados literalmente, apenas com acentos corrigidos da codificação Windows-1252).
//
// Formato de uma ação:
//   {
//     label:    texto do botão (== JButton do original),
//     custo:    { min: N, falha: "msg" }        // opcional — checa dinheiro >= N
//     requisito:{ stat: "inteligencia", min: N, falha: "msg" }  // opcional
//     efeitos:  { stat: delta, ... }            // deltas aplicados; level +1 é implícito
//     especial: "comprarCasa1" | ...            // opcional (imobiliária)
//     msg:      "feedback"                       // vai para o log/toast (não modal)
//     msgExtra: "feedback secundário"            // opcional (ex.: caça-níquel "Você Perdeu!")
//   }
//
// Toda ação bem-sucedida faz level += 1 (tratado em game.js), fiel ao original.

const SEM_DINHEIRO = "Você não tem dinheiro!";

const LOCAIS = {
  escola: {
    id: "escola",
    botao: "Escola",
    titulo: "EsCoolAA PuuBliCaah",
    acoes: [
      { label: "Estudar",
        efeitos: { inteligencia: +2, depressao: +1 },
        msg: "Inteligencia +2 e Depressão +1" },
      { label: "Beber água",
        efeitos: { sede: -3, banheiro: +1 },
        msg: "Sede -3 e Banheiro +1" },
      { label: "Cagar",
        efeitos: { banheiro: -2, depressao: +5, fome: +1 },
        msg: "Banheiro -2, Depressao +5 e Fome +1" },
      { label: "Zoar",
        efeitos: { depressao: -3, inteligencia: -1 },
        msg: "Depressão -3 e Inteligencia -1" },
    ],
  },

  boteco: {
    id: "boteco",
    botao: "Boteco do Jeberson",
    titulo: "Bar da Brahma",
    acoes: [
      { label: "Tomar dose de 61",
        efeitos: { inteligencia: -2, depressao: -3, sede: -1, dinheiro: -1 },
        msg: "Depressão -3, Inteligencia -2, Sede -1 e Dinheiro -1" },
      { label: "Comer pão na chapa",
        custo: { min: 2, falha: SEM_DINHEIRO },
        efeitos: { fome: -4, banheiro: +1, dinheiro: -2 },
        msg: "Fome -4, Banheiro +1 e Dinheiro -2" },
      { label: "Trabalhar de garçom",
        efeitos: { dinheiro: +2, depressao: +3, fome: +1 },
        msg: "Dinheiro +2, Depressao +3 e Fome +1" },
      { label: "Jogar caça níquel",
        custo: { min: 1, falha: SEM_DINHEIRO },
        efeitos: { depressao: -1, dinheiro: -1 },
        msgExtra: "Você Perdeu!",
        msg: "Depressão -1 e Dinheiro -1" },
    ],
  },

  senai: {
    id: "senai",
    botao: "SENAI",
    titulo: "SENAI PORRA!",
    acoes: [
      { label: "Aula do Glauber",
        efeitos: { inteligencia: +4, depressao: +4, fome: +1 },
        msg: "Depressão +4, Inteligencia +4 e Fome +1" },
      { label: "Comer marmita",
        custo: { min: 1, falha: SEM_DINHEIRO },
        efeitos: { fome: -4, banheiro: +1, dinheiro: -1 },
        msg: "Fome -4, Banheiro +1 e Dinheiro -1" },
      { label: "Aula do Chile",
        efeitos: { depressao: +1, fome: +1, inteligencia: +3 },
        msg: "Inteligencia +3, Depressao +1 e Fome +1" },
      { label: "Jogar CS na aula da Kellyn",
        efeitos: { depressao: -2, fome: +1 },
        msg: "Depressão -2 e Fome +1" },
    ],
  },

  apple: {
    id: "apple",
    botao: "Apple",
    titulo: "Apple Store",
    acoes: [
      { label: "Trabalhar de faxineiro",
        efeitos: { dinheiro: +2, depressao: +1, fome: +1 },
        msg: "Depressão +1, Dinheiro +2 e Fome +1" },
      { label: "Trabalhar de programador",
        requisito: { stat: "inteligencia", min: 35, falha: "Você é muito burro pra fazer isso!" },
        efeitos: { fome: +1, banheiro: +1, dinheiro: +8 },
        msg: "Fome +1, Banheiro +1 e Dinheiro +8" },
      { label: "Comprar iPhone",
        custo: { min: 50, falha: SEM_DINHEIRO },
        efeitos: { depressao: -20, dinheiro: -50 },
        msg: "Depressao -20 e Dinheiro -50" },
      { label: "Gritar que android é melhor",
        efeitos: { depressao: -1, fome: +1 },
        msg: "Depressão -1 e Fome +1" },
    ],
  },

  academia: {
    id: "academia",
    botao: "Academia",
    titulo: "VEM FICA MONSTRAO",
    acoes: [
      { label: "Fazer uns supinos",
        custo: { min: 1, falha: SEM_DINHEIRO },
        efeitos: { dinheiro: -1, depressao: +1, fome: +1, sede: +1, forca: +3 },
        msg: "Depressão +1, Dinheiro -1, Sede +1, Fome +1 e Força +3" },
      { label: "Tomar steroides",
        custo: { min: 5, falha: SEM_DINHEIRO },
        efeitos: { fome: +3, banheiro: +3, dinheiro: -5, forca: +10, depressao: +3, inteligencia: -3, sede: +3 },
        msg: "Fome +3, Banheiro +3, Dinheiro -5, Depressao +3, Inteligencia -3, Sede +3 e Força +10" },
      { label: "Ver as gostosas",
        custo: { min: 1, falha: SEM_DINHEIRO },
        efeitos: { depressao: -2, dinheiro: -1 },
        msg: "Depressao -2 e Dinheiro -1" },
      { label: "Trabalhar de personal",
        requisito: { stat: "forca", min: 20, falha: "Você não tem força!" },
        efeitos: { depressao: +1, fome: +1, dinheiro: +5 },
        msg: "Depressão +1, Fome +1 e Dinheiro +5" },
    ],
  },

  lanhouse: {
    id: "lanhouse",
    botao: "LanHouse do Jeremias",
    titulo: "Vende uns pipas também",
    acoes: [
      { label: "Jogar Minecraft",
        custo: { min: 1, falha: SEM_DINHEIRO },
        efeitos: { dinheiro: -1, depressao: -4, fome: +1, sede: +1 },
        msg: "Depressão -4, Dinheiro -1, Sede +1 e Fome +1" },
      { label: "Jogar Habbo",
        custo: { min: 2, falha: SEM_DINHEIRO },
        efeitos: { fome: +1, dinheiro: -2, depressao: -6, sede: +1 },
        msg: "Fome +1, Dinheiro -2, Depressao -6 e Sede +1" },
      { label: "Orkut/Msn",
        custo: { min: 1, falha: SEM_DINHEIRO },
        efeitos: { depressao: -2, dinheiro: -1 },
        msg: "Depressao -2 e Dinheiro -1" },
      { label: "Arrumar uns Pcs",
        requisito: { stat: "inteligencia", min: 8, falha: "Você é muito burro!" },
        efeitos: { depressao: +1, fome: +1, dinheiro: +3 },
        msg: "Depressão +1, Fome +1 e Dinheiro +3" },
    ],
  },

  // Casa é resolvida por casaAtual (1|2|3) em game.js — fiel ao switch de btCasa.
  casa1: {
    id: "casa1",
    botao: "Casa",
    titulo: "BaRRaaCo nAh CoMuunIDadI",
    acoes: [
      { label: "Assistir IXQUENTA",
        efeitos: { depressao: -2, fome: +1, sede: +1 },
        msg: "Depressão -2, Sede +1 e Fome +1" },
      { label: "Comer amianto",
        efeitos: { fome: -3, banheiro: +1, forca: -1, depressao: +1 },
        msg: "Fome -3, Banheiro +1, Depressao +1 e Força -1" },
      { label: "Mijar na parede",
        efeitos: { depressao: +1, banheiro: -2 },
        msg: "Depressao +1 e Banheiro -2" },
      { label: "Beber água da enchente",
        efeitos: { depressao: +1, sede: -3, banheiro: +1 },
        msg: "Depressão +1, Sede -3 e Banheiro +1" },
    ],
  },

  casa2: {
    id: "casa2",
    botao: "Casa",
    titulo: "Ap em Ermelino $$",
    acoes: [
      { label: "Baixar filme pirata",
        efeitos: { depressao: -4, fome: +1, sede: +1 },
        msg: "Depressão -4, Sede +1 e Fome +1" },
      { label: "Comer ovo com farinha",
        efeitos: { fome: -4, banheiro: +1 },
        msg: "Fome -4 e Banheiro +1" },
      { label: "Mijar na privada",
        efeitos: { banheiro: -3 },
        msg: "Banheiro -3" },
      { label: "Beber água da torneira",
        efeitos: { sede: -4, banheiro: +1 },
        msg: "Sede -4 e Banheiro +1" },
    ],
  },

  casa3: {
    id: "casa3",
    botao: "Casa",
    titulo: "MANSÃO DOS MAGNATA",
    acoes: [
      { label: "Jogar Playstation 6",
        efeitos: { depressao: -6, fome: +1, sede: +1 },
        msg: "Depressão -6, Sede +1 e Fome +1" },
      // Obs.: no original o efeito é Fome -5 mas a mensagem diz "Fome -4" (bug preservado).
      { label: "Comer esposa gostosa",
        efeitos: { fome: -5, banheiro: +1, depressao: -2 },
        msg: "Fome -4, Banheiro +1 e Depressão -2" },
      { label: "Cagar na suíte",
        efeitos: { banheiro: -5, depressao: -1 },
        msg: "Banheiro -5 e Depressão -1" },
      { label: "Tomar a bebida que pisca",
        efeitos: { sede: -5, banheiro: +1 },
        msg: "Sede -5 e Banheiro +1" },
    ],
  },

  imobiliaria: {
    id: "imobiliaria",
    botao: "Imobiliária",
    titulo: "Corretora de casinhas",
    acoes: [
      { label: "Comprar Casa lvl LIXO",
        especial: "comprarCasa",
        casa: 1, preco: 2,
        msg: "Agora você mora em uma casa lvl LIXO" },
      { label: "Comprar Casa lvl NORMAL",
        especial: "comprarCasa",
        casa: 2, preco: 30,
        msg: "Agora você mora em uma casa lvl NORMAL" },
      { label: "Comprar Casa lvl FODA",
        especial: "comprarCasa",
        casa: 3, preco: 60,
        msg: "Agora você mora em uma casa lvl FODA" },
      { label: "Trabalhar de corretor",
        requisito: { stat: "inteligencia", min: 12, falha: "Você é muito burro!" },
        efeitos: { depressao: +5, dinheiro: +4, banheiro: -1, fome: -1, sede: -1 },
        msg: "Depressão +5, Dinheiro +4, Banheiro -1, Fome -1 e Sede -1" },
    ],
  },
};

// Ordem dos botões de locais na tela principal (fiel ao layout de JanelaJogo).
const ORDEM_LOCAIS = ["escola", "boteco", "senai", "apple", "academia", "lanhouse", "casa", "imobiliaria"];

// Mensagens de morte (fiel a JanelaJogo.atualizar), avaliadas nesta ordem.
const MORTES = [
  { cond: (s) => s.banheiro >= 20,    msg: "Muito tempo sem cagar, faleceu." },
  { cond: (s) => s.depressao >= 20,   msg: "Muito triste, se matou." },
  { cond: (s) => s.fome >= 20,        msg: "Saco vazio não para em pé, morreu de fome pará." },
  { cond: (s) => s.inteligencia < 0,  msg: "Você é tão burro que virou um vegetal." },
  { cond: (s) => s.sede >= 20,        msg: "ÁGUA NEM É IMPORTANTE NÃO" },
];
