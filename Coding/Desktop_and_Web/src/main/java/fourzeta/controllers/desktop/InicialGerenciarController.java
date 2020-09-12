package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;
import fourzeta.desktop_views.Inicial;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Usuario;

public class InicialGerenciarController implements ActionListener {
	private Inicial tela;
	private Usuario usuario;
	private SelecionarTorneio telaGerenciar;

	public InicialGerenciarController(Usuario usuario, Inicial tela) {
		this.tela = tela;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			telaGerenciar = new SelecionarTorneio(usuario);
		} catch (ParseException | IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tela.setVisible(false);
		telaGerenciar.setVisible(true);

	}
}
