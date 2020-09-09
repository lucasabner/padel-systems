package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fourzeta.desktop_views.Creditos;
import fourzeta.desktop_views.Inicial;

public class MostrarCreditosController implements ActionListener {

	Inicial tela;
	Creditos telaCreditos;

	public MostrarCreditosController(Inicial tela) {
		this.tela = tela;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		telaCreditos = new Creditos();

		telaCreditos.setVisible(true);

	}

}
