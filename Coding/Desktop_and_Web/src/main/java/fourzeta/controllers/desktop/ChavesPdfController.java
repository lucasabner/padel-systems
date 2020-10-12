package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fourzeta.desktop_views.Chaveamento;
import fourzeta.models.Circuito;
import fourzeta.resources.ChaveResource;
import fourzeta.util.GeraPDF;

public class ChavesPdfController implements ActionListener {

	private ChaveResource cr;
	private Chaveamento tela;
	private GeraPDF geraPdf;
	private Circuito circuito;

	public ChavesPdfController(Circuito circuito, Chaveamento tela) {
		this.circuito = circuito;
		this.tela = tela;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String caminho = System.getProperty("user.home") + "/Desktop";
		geraPdf = new GeraPDF();
		cr = new ChaveResource();
		geraPdf.listaChavesPDF("Lista de Chaves - Circuito " + circuito.getNome(), caminho, cr.listaChaves());

		this.tela.notifyPdfSucesso();

	}

}
