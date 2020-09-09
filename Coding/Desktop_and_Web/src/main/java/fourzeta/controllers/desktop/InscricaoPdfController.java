package fourzeta.controllers.desktop;

//public class InscricaoPdfController implements ActionListener {
//
//	private DuplaResource dp;
//	private InscricaoDuplas tela;
//	private fourzeta.util.GeraPDF geraPdf;
//	private Torneio torneio;
//
//	public InscricaoPdfController(Torneio torneio, InscricaoDuplas tela) {
//		this.tela = tela;
//		this.torneio = torneio;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		String caminho = System.getProperty("user.home") + "/Desktop";
//		geraPdf = new GeraPDF();
//		dp = new DuplaResource();
//		geraPdf.listaInscritosPDF("Lista de Duplas Inscritas - Torneio " + this.torneio.getNome(), caminho,
//				(List<Dupla>) dp.listaDuplas());
//		this.tela.notifyPdfSucesso();
//
//	}
//}
