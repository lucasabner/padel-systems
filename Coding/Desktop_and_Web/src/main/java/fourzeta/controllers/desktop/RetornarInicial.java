package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;

import fourzeta.desktop_views.Inicial;
import fourzeta.models.Usuario;

public class RetornarInicial implements ActionListener {
	private JFrame tela;
	private Usuario usuario;
	private Inicial telaInicial;

	public RetornarInicial(Usuario usuario, JFrame tela) {
		this.tela = tela;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			telaInicial = new Inicial(usuario);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tela.setVisible(false);
		telaInicial.setVisible(true);

	}

}
