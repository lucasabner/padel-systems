# **Análise do Sistema de Gerenciamento de Torneio (PADEL)** #

### **Histórico de Versões** ###
|   Data   |  Versão  |    Descrição    |    Autor    |
|     :---:      |     :---:      |     :---:      |     :---:      |
| 13/08/2019   | 0.1    | Criação e modelação do documento. | Arthur Becker |
| 19/08/2019   | 0.2    | Preenchimento do documento. | Arthur Becker |

### **Introdução** ###

O sistema será voltado para o gerenciamento de Torneios de Padel, sendo flexível ao usuário final que poderá customizar o evento a ser promovido. Além disso, o sistema contará com regras gerais, definidas de acordo com  a IPF. O sistema irá permitir a criação de Torneios, na qual o gerenciador é responsável por esta ação, mas também a inscrição dos respectivos jogadores no torneio. Ademais, o sistema define o chaveamento de acordo com as regras especificadas de acordo com os requisitos especificados durante a o levantamento de requisitos. 

Como especificado abaixo, foi feito o Levantamento de Requisitos com três organizadores de Torneios distintos, na qual cada um faz as especificações de acordo com suas necessidades. No primeiro momento, o sistema a ser desenvolvidos será com base na necessidade do Patrick, ou seja, de acordo com os requisitos especificados. Na qual, o mesmo realiza torneios maiores e com regras semelhantes com a Confederação Brasileira de Padel (COBRAPA).

Os requisitos, dos demais gerenciadores de Torneio, estão documentados, pois, serão atendidos (desenvolvidos) durante a etapa de Linha de Produto. Outrossim,  

### **Especificação de Requisitos do cliente Patrick** ###

O Gerenciador do Sistema pode cadastrar torneios e esportistas, os esportistas têm nome cpf, categoria, e-mail idade, pontuação, gênero e o gerenciador mantém todas essas informações. 

Para a criação de torneios é necessário o nome, data, os horários que irá acontecer em cada dia, preço de inscrição por equipe, prêmios, período de inscrições, status do torneio(processo de inscrição, em andamento, finalizado).

Para a inscrição de esportistas no torneio o gerenciador pega os usuários inscritos no software e forma as duplas de acordo com o solicitado, o status da inscrição tem pago e pendente, e tem a também uma observação quanto aos impedimentos(horários e datas que algum esportista não poderá jogar). 


### **Especificação de Requisitos do cliente Arian Fagundes Rodrigues** ###

As inscrições serão feitas pelos jogadores através de um formulário do respectivo torneio em aberto. O organizador de torneios terá acesso ao sistema de gerenciamento, na qual o  mesmo poderá criar os Torneios de acordo com suas especificações, inscrever as duplas nas categorias, definir os horários de jogos. Ademais, o gerenciador poderá alterar as informações dos participantes inscrito caso tenha alguma divergência. 

O sistema deverá persistir as informações dos jogadores e seus pontos. Além disso, deve gerenciar a chaves de forma randômica, na qual a eliminação ocorre no formato “mata-mata”. O sistema deve atualizar os pontos das duplas ao final do torneio, pois, os pontos serão utilizados como parâmetro para definir em qual categoria a dupla será inscrita. 


### **Especificação de Requisitos do cliente Pedro** ###

Para realizar a inscrição, as seguintes informações devem ser apresentadas:  nome completo, CPF, categoria (1ª, 2ª, 3ª, 4ª, 5ª e iniciantes), impedimentos, celular e e-mail. 

O sistema deve calcular a pontuação individual de cada jogador de acordo com a pontuação obtida pela dupla na qual o jogador está contido. Os pontos obtidos são calculados através da vitória ou eliminação em etapas da seguinte maneira:

* Dupla campeã - 80
* Vice campeã - 60
* Semifinalista - 50
* Eliminada nas quartas de final - 30
* Eliminada nas oitavas de final - 20
* Eliminada nas 16-avas de final - 10
* Eliminada na primeira fase (Ponto de Participação)  - 5

As duplas participantes nos torneios devem ser organizadas em chaves geradas automaticamente pelo sistema utilizando o padrão S invertido e o usuário terá a permissão de distribuir as quadras para os jogos de acordo com o seu interesse, também, as quadras podem ser distribuídas automaticamente pelo sistema  seguindo a lógica das melhores categorias nas principais quadras.


### **Visão Geral do Sistema** ###

Com base nos requisitos do descritos por Patrick, e na perspectiva do grupo Zeta, no primeiro momento o sistema, contará com uma funcionalidade de persistir os registros de inscrições no banco, ou seja, a realização do cadastro. A princípio as inscrições serão realizadas por um formulário do Google Docs, associado à uma planilha, de modo que, ao término das inscrições o gerenciador poderá exportar os dados da planilha em um arquivo “csv”. A partir disso, o mesmo poderá importar o arquivo, que será persistido no banco, ademais, vale ressaltar que, haverá um algoritmo com várias regras de negócio que realizará a validação dos dados antes que os mesmos sejam persistidos no banco. Sendo algumas dessas regras: 

* Verificar se há um registro com base na chave de identificação (CPF), caso sim, será realizado uma atualização de dados caso seja necessário; 
* Persistir os dados de acordo com o modelo Lógico do Banco; 

A partir da persistência dos dados, o gerenciador poderá realizar as demais funcionalidades, bem como editar as informações dos inscritos caso seja necessário, mas também  criar Circuitos, Torneios. Pesquisar Registros Atletas, Cadastrar Quadras. Ademais, o sistema também contará com a funcionalidade de formar duplas quando há inscrições de apenas um atleta, ou seja, sem dupla. 
Ademais, outra funcionalidade do sistema será de Gerar Chaves, de acordo com a regra de montagem “S Invertido”.
