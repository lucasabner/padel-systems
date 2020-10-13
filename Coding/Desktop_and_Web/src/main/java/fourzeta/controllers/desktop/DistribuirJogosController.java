package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import fourzeta.desktop_views.DistribuirJogos;
import fourzeta.desktop_views.GradeJogos;
import fourzeta.models.Jogo;
import fourzeta.models.Quadra;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.JogoResource;

public class DistribuirJogosController implements ActionListener {

	private DistribuirJogos tela;
	private GradeJogos grade;
	private Usuario usuario;
	private Torneio torneio;
	private JogoResource jr;
	private ActionListener arg0;

	public DistribuirJogosController(Usuario usuario, Torneio torneio, DistribuirJogos tela) {
		this.tela = tela;
		this.usuario = usuario;
		this.torneio = torneio;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (this.tela.getComboBoxCategoria().getSelectedItem().equals("SELECIONAR")) {
			this.tela.notifyInformarCategoria();
		} else if (this.tela.getComboBoxQuadra().getSelectedItem().equals("SELECIONAR")) {
			this.tela.notifyInformarQuadra();
		}
		Quadra quadra = null;
		configurarCorQuadra(quadra);

		jr = new JogoResource();
		List<Jogo> jogos = jr.listaJogos();
		configurarQuadras(jogos, quadra, this.tela.getComboBoxCategoria().getSelectedItem().toString());
		this.tela.notifySucesso();
	}

	public Quadra configurarCorQuadra(Quadra quadra) {
		switch (this.tela.getComboBoxQuadra().getSelectedItem().toString()) {
		case "LARANJA":
			quadra = new Quadra(1);
			break;
		case "AZUL":
			quadra = new Quadra(2);
			break;
		case "VERDE":
			quadra = new Quadra(3);
		}
		return quadra;
	}

	public void configurarQuadras(List<Jogo> jogos, Quadra quadra, String categoria) {
		jr = new JogoResource();
		for (Jogo j : jogos) {
			if (j.getCategoria().equalsIgnoreCase(categoria)) {
				j.setQuadra(quadra);
				jr.registraJogo(j);
			}
		}
	}

	public ActionListener actionPerformedVoltar() {
		try {
			grade = new GradeJogos(usuario, torneio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tela.setVisible(false);
		this.grade.setVisible(false);

		return null;

	}

}
