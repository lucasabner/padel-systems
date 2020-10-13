package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import fourzeta.desktop_views.DistribuirJogos;
import fourzeta.desktop_views.GradeJogos;
import fourzeta.models.Chave;
import fourzeta.models.Jogo;
import fourzeta.models.Quadra;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.ChaveResource;
import fourzeta.resources.JogoResource;

public class DistribuirJogosController implements ActionListener {

	private DistribuirJogos tela;
	private GradeJogos grade;
	private Usuario usuario;
	private Torneio torneio;
	private JogoResource jr;
	private ActionListener arg0;

	public DistribuirJogosController(Usuario usuario,  DistribuirJogos tela) {
		this.tela = tela;
		this.usuario = usuario;
	}
	
	public DistribuirJogosController(Usuario usuario, Torneio torneio) {
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
	public void montarJogos() {
		EncerrarController eC = new EncerrarController(usuario,torneio,tela);
		List<Jogo> jogos = new ArrayList<Jogo>();
		for (Chave chave : torneio.getChaves()) { // Jogos 1 VS 2
			Jogo jo1 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla2() != null) {
				jo1.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
				jo1.setDupla1(chave.getDupla1());
				jo1.setDupla2(chave.getDupla2());
				jo1.setCategoria(chave.getCategoria());
				jo1.setChave(chave);
			}
			jogos.add(eC.distribuirHorarios(chave, chave.getDupla1(), chave.getDupla2(), jo1));
			chave.setJogos(jogos);
			JogoResource jr = new JogoResource();
			jr.registraJogo(jo1);
			ChaveResource chaver = new ChaveResource();
			chaver.registraChave(chave);

		}
		for (Chave chave : torneio.getChaves()) { // Jogos 1 VS 3
			Jogo jo2 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla3() != null) {
				jo2.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla3().toString());
				jo2.setDupla1(chave.getDupla1());
				jo2.setDupla2(chave.getDupla3());
				jo2.setCategoria(chave.getCategoria());
				jo2.setChave(chave);
			}
			jogos.add(eC.distribuirHorarios(chave, chave.getDupla1(), chave.getDupla3(), jo2));
			chave.setJogos(jogos);
			JogoResource jr = new JogoResource();
			jr.registraJogo(jo2);
			ChaveResource chaver = new ChaveResource();
			chaver.registraChave(chave);
		}
		for (Chave chave : torneio.getChaves()) { // Jogos 2 VS 3
			Jogo jo3 = new Jogo();
			if (chave.getDupla2() != null && chave.getDupla3() != null) {
				jo3.setPartida(chave.getDupla2().toString() + "     X     " + chave.getDupla3().toString());
				jo3.setDupla1(chave.getDupla2());
				jo3.setDupla2(chave.getDupla3());
				jo3.setCategoria(chave.getCategoria());
				jo3.setChave(chave);
			}
			jogos.add(eC.distribuirHorarios(chave, chave.getDupla2(), chave.getDupla3(), jo3));
			chave.setJogos(jogos);
			JogoResource jr = new JogoResource();
			jr.registraJogo(jo3);

			ChaveResource chaver = new ChaveResource();
			chaver.registraChave(chave);
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
