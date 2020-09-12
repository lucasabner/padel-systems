package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.desktop_views.GradeJogos;
import fourzeta.models.Usuario;
import fourzeta.models.Torneio;

public class GradeJogosController implements ActionListener {

	private GerenciarTorneio tela;
	private Torneio torneio;
	private GradeJogos grade;
	private Usuario usuario;

	public GradeJogosController(Usuario usuario, Torneio torneio, GerenciarTorneio tela) throws ParseException {
		this.tela = tela;
		this.torneio = torneio;
		this.usuario = usuario;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.grade = new GradeJogos(usuario, torneio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tela.setVisible(false);
		this.grade.setVisible(true);

	}

}
