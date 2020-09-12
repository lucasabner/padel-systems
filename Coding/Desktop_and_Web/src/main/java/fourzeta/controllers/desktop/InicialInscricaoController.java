package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.desktop_views.InscricaoDuplas;
import fourzeta.models.Usuario;
import fourzeta.models.Torneio;

public class InicialInscricaoController implements ActionListener {

	private GerenciarTorneio tela;
	private Torneio torneio;
	private Usuario usuario;
	private InscricaoDuplas telaInsc;

	public InicialInscricaoController(Usuario usuario, Torneio torneio, GerenciarTorneio tela) {
		this.tela = tela;
		this.torneio = torneio;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			telaInsc = new InscricaoDuplas(usuario, torneio);
			this.tela.setVisible(false);
			telaInsc.setVisible(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
