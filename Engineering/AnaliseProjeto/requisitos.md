# **Especificação dos Requisitos** #

### **Histórico de Versões**  ###
|   Data   |  Versão  |    Descrição    |    Autor    |
|     :---:      |     :---:      |     :---:      |     :---:      |
| 13/08/2019   | 0.1    | Criação e modelação do documento. | Arthur Becker |
| 19/08/2019   | 0.2    | Preenchimento do documento. | Arthur Becker |

### **1 Introdução** ###
#### **1.1 Escopo** ####
O objetivo deste documento é fornecer informações específicas dos requisitos necessários para o desenvolvimento do  sistema para gerenciar torneios de Padel. Ao especificar os requisitos, esperamos reduzir a probabilidade de incoerência entre os interessados.

### **2 Requisitos** ###
#### **2.1 Requisitos Pedro (Organizador de Torneios - Rio Grande)** ####

**P1: Quais os documentos necessários para realizar uma inscrição em um torneio de Padel?**

>**R1:** Nome completo, Categoria, algum impedimento (caso não possa jogar em algum horário especifico), Celular e E-mail. Obs: segundo ele, se for um sistema, seria interessante ter um cpf

**P2: Quais são as categorias do Padel?**

>**R2:** 1ª, 2ª, 3ª, 4ª, 5ª e iniciantes nos naipes feminino e iniciante.

**P3: Como é feito o cálculo da pontuação individual de cada jogador?**

>**R3:** A dupla campeã da etapa recebe 80 pontos, vice recebe 60, semi final 50, quartas de final 30, oitavas 20, 16-tavas ganha 10 e primeira fase ganha 5 só por participação.

**P4: Como são organizadas/montadas as chaves durante os torneios?**

>**R4:** As chaves são pelo que chamamos de S invertido.

**P5: Como são distribuídos os campos para os jogos?**

>**R5:** A distribuição de quadras eu faço conforme meu interesse, seguindo a lógica das melhores categorias nas principais quadras.

#### **2.2 Requisitos Patrick (Padel House Alegrete - Alegrete)** ####

**P1: Quais são as categorias do Padel?**

>**R1:** 1ª, 2ª, 3ª, 4ª, 5ª e 6ª(iniciantes) nos naipes feminino e iniciante.

**P2: Como são organizadas/montadas as chaves durante os torneios?**

>**R2:** As chaves são montadas seguindo as normas seguintes:
>
>* As chaves preferencialmente têm um número ímpar de duplas
>* Ex.: 6 duplas  - 3 na chave A e 3 na chave B
>* A primeira chave tem a melhor dupla(dupla com maior ponto) e a dupla mais fraca(dupla com menos ponto)
>* Ex.: 9 duplas - 100pts, 99pts, 98pts, 97pts, 96pts,95 pts, 94pts, 93pts, 92pts..
>
>>Chave A  | Chave B | Chave C
>>-------- | ------- | -------
>>   100   |    99   |    98
>>    97   |    96   |    95
>>    92   |    93   |    94
>
>* Todos da mesma chave joga contra todos.
>* O vencedor da Chave A vai direto para semifinal.
>
>__Quartas de Finais__
>
>>     1ª Chave C _VS_ 2ª Chave A (JOGO 1)
>>     2ª Chave C _VS_ 2ª Chave B (JOGO 2) 
>
>__Semifinais__
>
>>     1ª Chave A _VS_ Vencedor Jogo 2 (JOGO 3)
>>     2ª Chave B _VS_ Vencedor Jogo 1 (JOGO 4) 
>
>__Finais__
>
>>     Vencedor Jogo 3 _VS_ 2ª Vencedor Jogo 1 

**P3: Qual duração das partidas e como é feita a separação das duplas que vão jogar?**

>**R3:** Cada jogo tem uma duração estimada de 1h10M para as partidas nas etapas de semifinal, e final, para as outras, é estimado um tempo de 50 minutos. 

**P4: Como é feita a distribuição de pontos para os jogadores dos torneios?**

>**R4:** O Gerenciador do Torneio tem uma regra própria para distribuição de pontos entre as duplas, quanto mais próximo de ganhar um campeonato uma dupla chegar , mais pontos ela vai ganhar. Os pontos são acumulados durante 1 ano, e são utilizados para rankear os jogadores em cada circuito afins de separá - los em chaves mais fortes e mais fracas.

**P5: Como é feita o cadastro dos jogadores/duplas em um torneio?**

>**R5:** Um integrante da dupla se cadastra em um torneio em uma modalidade que será verificada pelos coordenadores do torneio.

#### **2.3 Requisitos Arian Fagundes Rodrigues (Conhecido - Rosario do Sul)** ####

**P1: Como faz para se inscrever em um campeonato?**

>**R1:** As inscrições acontecem diretamente com quem organiza os campeonatos.  Geralmente entra em contato e inscreve a dupla. Tanto em Federação quanto torneio amador tem que pagar pra jogar. Existem diferentes categorias para se inscrever. Para inscrição de dupla masculina há 5 categorias Sendo: 1, 2, 3, 4, 5. Dupla feminina há três categorias Ou Dupla Mista (composta de homem e mulher). Não há requisitos específicos para se inscrever nas categorias. A enumeração define as habilidades, os participantes que escolhem em qual quer jogar. Existe campeonatos que dividem as categorias por idade.
>
>* Arian sugeriu, que a inscrição fosse online via-formulário e ao final da inscrição o sistema gerasse um boleto para efetuar o pagamento.

**P2: Como funciona o sistema de pontuação do jogo?**

>**R2:** O Sistema de pontuação é semelhante ao Tênis. O primeiro ponto é 15, o segundo 30, terceiro 40 e o quarto um “GAME”. A dupla que fizer o quarto ponto primeiro ganha o “GAME”, desde que tenha uma diferença de dois pontos da dupla adversária

>Caso ocorra empate, os pontos recebem nomes de “iguais”, até que uma das duplas façam dois pontos consecutivos a mais.

>Há cada  6 “GAME” equivale há um “Set”. Se a dupla alcançar 6 GAME com uma diferença de dois pontos ganha o set.

>Nos campeonatos oficiais é necessário atingir três set para ganhar.

>Quando acontece o empate das duplas no sexto game, ocorre o Tie-break, que é uma forma de desempate. O sistema de pontos muda, onde ganha a dupla que atingir 7 pontos. nesse caso a contagem acontece sequencialmente iniciando em “Zero”. Nessa modalidade também tem que ter diferença de dois pontos entre as duplas.

**P3: Quando uma dupla ganha?**

>**R3:** O jogo possui três sets, a dupla que ganhar dois é a vitoriosa.

**P4: Existe tempo de partida?**

>**R4:** Não existe tempo máximo, o jogo só encerra quando uma dupla ganhar.

**P5: Como são definidas as chaves?**

>**R5:** Geralmente, são três ou quatro duplas por chave. Cada chave se classificam duas duplas. A eliminação ocorre no formato “mata-mata”. As chaves são definidas por sorteio.

#### **2.4 Requisitos Órgãos Regulamentadores** ####
##### **2.4.1 International Padel Federation (IPF)** #####
Os idiomas oficiais da Federação são inglês e espanhol.

###### **2.4.1.1 Associação de organizações ao IPF** ######
É essencial que cada Associação seja uma Associação sem fins lucrativos.

A constituição e as regras de um membro não podem estar em conflito com o da IPF. Em caso de conflito deve prevalecer as regras do IPF.

Cada associação membro ou federação de padel ou organização correspondente que solicita a associação ao IPF deve emitir licenças para seus jogadores registrados.

Após a aceitação, as referidas Associações ou Organizações Nacionais de Padel serão conhecidas como “Membro Associado”.

Os membros da Federação serão os seguintes:

* _Membros Classe A:_ São as organizações fundadoras da Federação Internacional de Padel e a organização da Nação onde foi registrada a criação do Padel.

* _Membros Classe B:_ São associações ou federações nacionais de padel ou organizações correspondentes de países ou territórios independentes que também podem ser da Classe A e que, na opinião do Conselho,estão suficientemente desenvolvidas em questões de papel para garantir a filiação à Classe B.

* _Membros Classe C:_ São associações de padel ou organizações correspondentes de países ou territórios que, na opinião do conselho, não estão suficientemente maduras em questões de papel para garantir a afiliação Classe B, mas estão suficientemente desenvolvidas para garantir a afiliação Classe C.

###### **2.4.1.2 Competições** ######

O título “Campeonatos Mundiais” em conexão com Padel, não deve ser instituído ou revivido em qualquer momento sem o consentimento unânime de uma Assembléia Geral do Conselho.

Os Campeonatos Mundiais de Equipes, Absolutos, Júnior e Sênior, e World Open Championship estarão abertos apenas para os Membros da Classe B da IPF. No caso de todas as outras competições de equipes, as inscrições podem ser aceitas dos membros da Classe B e da Classe C. Apenas jogadores que são elegíveis para representar um país de acordo com os regulamentos adotados podem ser membros nomeados da equipe daquele país.

Os jogadores que residem em países onde não há entidades com qualquer tipo de relação com a IPF, necessitarão de autorização prévia do Conselho de Administração para participar de uma competição do IPF.

###### **2.4.1.3 Arbitragem** ######

Qualquer Arbitragem será regida pela Lei Suíça.
