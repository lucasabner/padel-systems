# Especificação dos Casos de Teste #

### Histórico de Versões  ###
|   Data   |  Versão  |    Descrição    |    Autor    |
|     :---:      |     :---:      |     :---:      |     :---:      |
| 13/08/2019   | 0.1    | Criação e modelação do documento. | Arthur Becker |
| 19/08/2019   | 0.2    | Preenchimento do documento. | Arthur Becker |
| 29/09/2020   | 0.3    | Definição dos casos de teste. | Arthur Becker |

### **1 Introdução** ###
#### 1.1 Escopo ####
O objetivo deste documento é fornecer informações específicas dos casos de teste necessários para a execução dos  testes referentes ao projeto. Ao fornecer os casos de teste, esperamos reduzir a probabilidade de ignorar itens e melhorar a cobertura dos testes do sistema para gerenciar torneios de Padel.

#### 1.2 Glossário ####
As seguintes abreviaturas são utilizadas neste documento:

(CRnn) - é relacionada com os Conjuntos de Recursos contidos no documento de [_Especificação de Teste do Projeto_]()




### **2 Condições de Teste** ###
Neste capítulo, as condições de teste para cada conjunto de recursos são documentadas, seguindo a sequente estrutura:

- (nn): Número único que nunca deve ser alterado. Isso é usado para fins de rastreabilidade;
- Objetivo: Descreve a condição de teste, ou seja, o que pode ser testado;
- Prioridade: A prioridade usará a classificação da tabela abaixo;

|   Prioridade   |  Descrição  |
|     :---:      |     :---:      |
| Alto   | Testadas mais cedo, com grau de extensivamente alto/maior.    |
| Média  | Testadas com o decorrer do plano, com grau de extensivamente media.    |
| Baixa  | Testadas tardiamente, com grau de extensivamente baixo/menor.    |



### **3 Casos de Teste** ###
  
|   **Identificador:**   |  TC-01 Inscrição Nova Dupla  |
|     ---      |     ---      |
| **Prioridade:**   | Alta    |
| **Descrição:**  | O presente caso de teste tem como finalidade testar e validar a funcionalidade de inscrever duplas.    |
| **Pré-condições:**  | Deve-se ter cadastrado no sistema pelo menos um torneio e um circuito, estando esses com inscrições abertas.    |
| **Comportamento esperado:**   | Não contendo nenhum dado inválido, deve-se prosseguir com o cadastramento.    |
| **Pós-condições:**   | Deve-se salvar a inscrição e atualizar o status da dupla na competição, bem como fazer a soma de pontos.   |

|   **Identificador:**   |  TC-02 Inscrição Dupla Jogador Existente  |
|     ---      |     ---      |
| **Prioridade:**   | Alta    |
| **Descrição:**  | Com o objetivo de não se ter cadastros duplicados, deve-se sempre verificar os cpf dos jogadores da dupla a ser inscrita, verificação essa com o intuito de verificar se o jogador já está cadastrado no sistema.    |
| **Pré-condições:**  | Para a execução deste teste, deve-se ter cadastrado pelo menos um torneio, um circuito, bem como um dos jogadores deve já ter sido cadastrado anteriormente. Vale ressaltar que as inscrições no torneio e circuito devem estar abertas.    |
| **Comportamento esperado:**   | Ao verificar a existencia de cadastro de um dos jogadores, assim na hora de cadastrar a dupla deve-se ao invés de realizar um novo cadastro do jogador fazer uso do cadastro antigo.    |
| **Pós-condições:**   | Deve-se salvar a inscrição e atualizar o status da dupla na competição, bem como fazer a soma de pontos.    |

|   **Identificador:**   |  TC-03 Validação do CPF na Inscrição  |
|     ---      |     ---      |
| **Prioridade:**   | Alta    |
| **Descrição:**  | Afim de garantir veracidade, deve-se validar o CPF dos jogadores a serem cadastrados no sistema.    |
| **Pré-condições:**  | Não fazer uso de um CPF já cadastrado.    |
| **Comportamento esperado:**   | No momento se for cadastrar a dupla o sistema deve validar o CPF dos jogadores.    |
| **Pós-condições:**   | Caso CPF seja inválido deve-se informar o usuário.    |

|   **Identificador:**   |  TC-04 Inscrição Jogador C/ Dados Inválidos  |
|     ---      |     ---      |
| **Prioridade:**   | Média    |
| **Descrição:**  | Deve-se verificar certas informações de cadastros como: Data de nascimento, não selecionar categoria, torneio ou circuito. Caso estas não sejam válidas é preciso informar o usuárioio sobre os campos inválidos    |
| **Pré-condições:**  | Deve-se ter cadastrado no sistema pelo menos um torneio e um circuito,  estando esses com inscrições abertas.    |
| **Comportamento esperado:**   | O sistema não pode realizar a inscrição ou salvar os jogadores com informações inválidas.    |
| **Pós-condições:**   | Informar o usuário dos campos com informção inválida    |

|   **Identificador:**   |  TC-05 Calcular Pontos da Dupla  |
|     ---      |     ---      |
| **Prioridade:**   | Baixa    |
| **Descrição:**  | DESCRIÇÃO.    |
| **Pré-condições:**  | Deve-se ter cadastrado pelo menos um torneio e um circuito, estando esses com inscrições abertas.    |
| **Comportamento esperado:**   | COMPORTAMENTO ESPERADO    |
| **Pós-condições:**   | PÓS-CONDIÇÃO    |

|   **Identificador:**   |  TC-06 Geração de lista de incritos  |
|     ---      |     ---      |
| **Prioridade:**   | Média    |
| **Descrição:**  | DESCRIÇÃO.    |
| **Pré-condições:**  | PRÉ-CONDICAO.    |
| **Comportamento esperado:**   | COMPORTAMENTO ESPERADO    |
| **Pós-condições:**   | PÓS-CONDIÇÃO    |

|   **Identificador:**   |  TC-07 Geração de Chaves  |
|     ---      |     ---      |
| **Prioridade:**   | Média    |
| **Descrição:**  | DESCRIÇÃO.    |
| **Pré-condições:**  | PRÉ-CONDICAO.    |
| **Comportamento esperado:**   | COMPORTAMENTO ESPERADO    |
| **Pós-condições:**   | PÓS-CONDIÇÃO    |

|   **Identificador:**   |  TC-08 Geração de Chaves C/ Suplentes  |
|     ---      |     ---      |
| **Prioridade:**   | Média    |
| **Descrição:**  | DESCRIÇÃO.    |
| **Pré-condições:**  | PRÉ-CONDICAO.    |
| **Comportamento esperado:**   | COMPORTAMENTO ESPERADO    |
| **Pós-condições:**   | PÓS-CONDIÇÃO    |

|   **Identificador:**   |  TC-09 Cadastro de Torneio  |
|     ---      |     ---      |
| **Prioridade:**   | Média    |
| **Descrição:**  | DESCRIÇÃO.    |
| **Pré-condições:**  | PRÉ-CONDICAO.    |
| **Comportamento esperado:**   | COMPORTAMENTO ESPERADO    |
| **Pós-condições:**   | PÓS-CONDIÇÃO    |

|   **Identificador:**   |  TC-10 Cadastro de Circuito  |
|     ---      |     ---      |
| **Prioridade:**   | Média    |
| **Descrição:**  | DESCRIÇÃO.    |
| **Pré-condições:**  | PRÉ-CONDICAO.    |
| **Comportamento esperado:**   | COMPORTAMENTO ESPERADO    |
| **Pós-condições:**   | PÓS-CONDIÇÃO    |

|   **Identificador:**   |  TC-11 Cadastro de Jogos  |
|     ---      |     ---      |
| **Prioridade:**   | Média    |
| **Descrição:**  | DESCRIÇÃO.    |
| **Pré-condições:**  | PRÉ-CONDICAO.    |
| **Comportamento esperado:**   | COMPORTAMENTO ESPERADO    |
| **Pós-condições:**   | PÓS-CONDIÇÃO    |

|   **Identificador:**   |  TC-12 Ranking  |
|     ---      |     ---      |
| **Prioridade:**   | Média    |
| **Descrição:**  | DESCRIÇÃO.    |
| **Pré-condições:**  | PRÉ-CONDICAO.    |
| **Comportamento esperado:**   | COMPORTAMENTO ESPERADO    |
| **Pós-condições:**   | PÓS-CONDIÇÃO    |


### **4 Métricas dos Testes** ###
Nessa seção, serão documentadas as métricas em relação a severidade dos defeitos encontrados durante a execução dos testes.
A gravidade dos erros encontrados será classificada em:

| Grau    | Descrição                                                                          |
|---------|------------------------------------------------------------------------------------|
| Crítico | Deve ser resolvido imediatamente, alto impacto fazendo a aplicação ser finalizada. |
| Sério   | Alta prioridade porém a aplicação segue em execução.                               |
| Médio   | Resultado inesperado ou Operação inconsistente.                                    |
| Baixo   | Prioridade baixa, relacionado a design ou sugestão.                                |

A seguir, se encontra uma tabela com o identificador do defeito, sua descrição e o seu determinado grau de severidade.

| Identificador     | Descrição do Erro                                                                     | Grau de Severidade |
|-------------------|---------------------------------------------------------------------------------------|--------------------|
| E01 EXEMPLO       | O método EXEMPLO da classe EXEMPLO falhou no incremento da variável EXEMPLO.      |critico |
