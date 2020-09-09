# Especificação dos Casos de Teste #

### Histórico de Versões  ###
| Data       | Versão | Descrição                                                                                                                                       |     Autor     |
|:----------:|:------:|------------------------------------------------------------------------------------------------------------------------------------------------|:-------------:|]
| 13/08/2019 | 0.1    | Criação e modelação do documento. | Arthur Becker |

#**1 Introdução**
##1.1 Escopo
O objetivo deste documento é fornecer informações específicas dos casos de teste necessários para a execução dos  testes referentes ao projeto. Ao fornecer os casos de teste, esperamos reduzir a probabilidade de ignorar itens e melhorar a cobertura dos testes do sistema para gerenciar torneios de Padel.

##1.2 Glossário
As seguintes abreviaturas são utilizadas neste documento:

(CRnn) - é relacionada com os Conjuntos de Recursos contidos no documento de [_Especificação de Teste do Projeto_]()




#**2 Condições de Teste**
Neste capítulo, as condições de teste para cada conjunto de recursos são documentadas, seguindo a sequente estrutura:

- (nn): Número único que nunca deve ser alterado. Isso é usado para fins de rastreabilidade;
- Objetivo: Descreve a condição de teste, ou seja, o que pode ser testado;
- Prioridade: A prioridade usará a classificação da tabela abaixo;

| Prioridade | Descrição|
|:----------:|------------------------------------------------------------------------------------------------------------------------------------------------|]
| Alto | Testadas mais cedo, com grau de extensivamente alto/maior.                                                                            |
| Média |  Testadas com o decorrer do plano, com grau de extensivamente media.                                                                           |
| Baixa |  Testadas tardiamente, com grau de extensivamente baixo/menor.                                                                         |









# **3 Casos de Teste**
  
-----------------------

**Identificador:**  TC 01 EXEMPLO   

**Prioridade:** Alta 

**Descrição:** DESCRIÇÃO. 

**Pré-condições:** PRÉ-CONDICAO.  

**Comportamento esperado:** COMPORTAMENTO ESPERADO

**Pós-condições:** PÓS-CONDIÇÃO. 

**Rastreabilidade:** Esse caso de teste é referente ao (CR01) .

**Histórico de execuções:**


| Data |  Resultado  |  Autor  |
|:----:|:----------: |:-------:|
| 13/09/19 | Sucesso   | Arthur Becker |

-----------------------



#**4 Métricas dos Testes**
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
| E01 EXEMPLO        | O método EXEMPLO da classe EXEMPLO falhou no incremento da variável EXEMPLO.      |critico |