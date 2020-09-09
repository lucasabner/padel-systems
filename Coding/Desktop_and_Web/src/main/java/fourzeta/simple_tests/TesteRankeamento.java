package fourzeta.simple_tests;

public class TesteRankeamento {
	public static void main(String[] args) {

//		// Forma o Rank com os dados disponiveis e salva
//
//		List<Ranking> pontuacoes = new ArrayList<Ranking>();
//		List<Circuito> circuitos = new ArrayList<Circuito>();
//		List<Torneio> torneios = new ArrayList<Torneio>();
//		List<Chave> chaves = new ArrayList<Chave>();
//
//		CircuitoDAO circuitoDAO = new CircuitoDAO();
//
//		System.out.println("TESTE METODO RANKEAMENTO CATEGORIA");
//		List<Ranking> pontuacoesIniciantes = new ArrayList<Ranking>();
//		Circuito circuito1 = new Circuito();
//		CircuitoDAO cDAO = new CircuitoDAO();
//		circuito1 = cDAO.findById(66);
//		pontuacoesIniciantes.addAll(circuito1.getRanksByCategoria("INICIANTE"));
//
//		for (Ranking ranking : pontuacoesIniciantes) {
//			System.out.println(ranking.getAtleta().getNome() + "|" + ranking.getAtleta().getCpf() + "|"
//					+ ranking.getPontos() + "|" + ranking.getId() + "|");
//		}
//
//		System.out.println("FIM TESTE RANKEAMENTO CATEGORIA");
//
//		circuitos = circuitoDAO.findAll();
//		Circuito circuito = new Circuito();
//		circuito = circuitos.get(0);
//
//		// Torneios do Circuito
//		torneios = circuito.getTorneios();
//		Torneio torneio = torneios.get(0);
//
//		// Chaves do Torneio
//		chaves = torneio.getChave();
//
//		System.out.println("Circuito:" + circuito.getNome());
//		System.out.println("Torneio:" + torneio.getNome());
//
//		for (Chave c : chaves) {
//			System.out.print("Nome da Chave: " + c.getNome() + "\n----Jogos----\n");
//			for (Jogo jogo : c.getJogos()) {
//				System.out.println(jogo.getId());
//			}
//		}
//
////		jogos = chave.getJogos();
////		Jogo = new
//
//		// Rankings do Circuito
//		pontuacoes = circuito.getPontuacoes();
//
//		List<Ranking> pontCat1 = new ArrayList<Ranking>();
//		List<Ranking> pontCat2 = new ArrayList<Ranking>();
//		List<Ranking> pontCat3 = new ArrayList<Ranking>();
//		List<Ranking> pontCat4 = new ArrayList<Ranking>();
//		List<Ranking> pontCat5 = new ArrayList<Ranking>();
//		List<Ranking> pontCat6 = new ArrayList<Ranking>();
//
//		for (Ranking pontuacao : pontuacoes) {
//			switch (pontuacao.getCategoria()) {
//			case PRIMEIRA:
//
//				pontCat1.add(pontuacao);
//				break;
//			case SEGUNDA:
//				pontCat2.add(pontuacao);
//				break;
//			case TERCEIRA:
//				pontCat3.add(pontuacao);
//				break;
//			case QUARTA:
//				pontCat4.add(pontuacao);
//				break;
//			case QUINTA:
//				pontCat5.add(pontuacao);
//			case INICIANTES:
//				pontCat6.add(pontuacao);
//				break;
//			default:
//				JOptionPane.showInputDialog("Erro: Atleta sem categoria definida!");
//				break;
//			}
//		}
//
//		pontCat1.sort(new OrderPontuacaoDecrescente());
//		pontCat2.sort(new OrderPontuacaoDecrescente());
//		pontCat3.sort(new OrderPontuacaoDecrescente());
//		pontCat4.sort(new OrderPontuacaoDecrescente());
//		pontCat5.sort(new OrderPontuacaoDecrescente());
//		pontCat6.sort(new OrderPontuacaoDecrescente());
//
////        Listando rank do circuito 1(index 0)
//		System.out.println("\nRanking do Circuito " + circuito.getNome());
//		System.out.println("Categoria 6");
//		for (Ranking ranking : pontCat6) {
//			System.out.println(ranking.getAtleta().getNome() + "|" + ranking.getAtleta().getCpf() + "|"
//					+ ranking.getPontos() + "|" + ranking.getId() + "|");
//		}
	}
}
