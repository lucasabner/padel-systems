package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import connection.IElement;
import model.Chave;
import model.Dupla;
import model.Etapa;
import model.Gerente;
import model.Impedimento;
import model.Jogo;
import model.Quadra;
import model.Torneio;
import resource.ChaveResource;
import resource.DuplaResource;
import resource.JogoResource;
import view.GerenciarTorneio;

public class EncerrarController implements ActionListener {

	private GerenciarTorneio tela;
	private DuplaResource dr;
	private ChaveResource chaver;
	private JogoResource jr;
	private Gerente gerente;
	private Torneio torneio;
	private List<String> horariosQuinta = new ArrayList<String>();
	private List<String> horariosSexta = new ArrayList<String>();
	private List<String> horariosSabadoManha = new ArrayList<String>();
	private List<String> horariosSabadoTarde = new ArrayList<String>();
	private List<String> horariosSabadoNoite = new ArrayList<String>();
	private List<String> horariosDomingoManha = new ArrayList<String>();
	private List<String> horariosDomingoTarde = new ArrayList<String>();
	private List<String> horariosDomingoNoite = new ArrayList<String>();

	public EncerrarController(Gerente gerente, Torneio torneio, GerenciarTorneio tela) {
		this.tela = tela;
		this.gerente = gerente;
		this.torneio = torneio;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			organizarJogos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.tela.notifyEncerramentoSucesso();
	}

	public void organizarJogos() throws RemoteException {

		List<Jogo> jogos = new ArrayList<Jogo>();

		for (Chave chave : torneio.getChaves()) { // Jogos 1 VS 2
			Jogo jo1 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla2() != null) {

				jo1.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
			}
			setarQuadra(jo1, chave.getCategoria());
			jogos.add(this.distribuirHorarios(chave, chave.getDupla1(), chave.getDupla2(), jo1));
			chave.setJogos(jogos);
			jr = new JogoResource();
			jr.registraJogo(jo1);
			chaver = new ChaveResource();
			chaver.registraChave(chave);

		}
		for (Chave chave : torneio.getChaves()) { // Jogos 1 VS 3
			Jogo jo2 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla3() != null) {

				jo2.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
			}
			setarQuadra(jo2, chave.getCategoria());
			jogos.add(this.distribuirHorarios(chave, chave.getDupla1(), chave.getDupla3(), jo2));
			chave.setJogos(jogos);
			jr = new JogoResource();
			jr.registraJogo(jo2);
			chaver = new ChaveResource();
			chaver.registraChave(chave);
		}
		for (Chave chave : torneio.getChaves()) { // Jogos 2 VS 3
			Jogo jo3 = new Jogo();
			if (chave.getDupla2() != null && chave.getDupla2() != null) {

				jo3.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
			}
			setarQuadra(jo3, chave.getCategoria());
			jogos.add(this.distribuirHorarios(chave, chave.getDupla2(), chave.getDupla3(), jo3));
			chave.setJogos(jogos);
			jr = new JogoResource();
			jr.registraJogo(jo3);

			chaver = new ChaveResource();
			chaver.registraChave(chave);
		}

		// Pegar o numero de duplas e pegar 16 para as oitavas, depois 8 para as
		// quartas, 4 para semi e duas para a final

		// Calculo para o minimo de 16 duplas que irão formar uma oitava de final
		// for(int i = 0; i < 16 ; i++) { // 16 duplas em

		int i = 1;
		for (int j = 0; j < 6; j++) {// 6 categorias
			i = 1;
			for (int k = 0; k < 8; k++) { // 8 chaves
				Chave chave = new Chave();
				switch (j) { // 32 duplas cadastratdas
				case 0:
					chave.setNome("Chave Oitavas (1)Cat-id: " + (k + 1));
					chave.setCategoria("PRIMEIRA");
					break;
				case 1:
					chave.setNome("Chave Oitavas (2)Cat-id: " + (k + 1));
					chave.setCategoria("SEFGUNDA");
					break;
				case 2:
					chave.setNome("Chave Oitavas (3)Cat-id: " + (k + 1));
					chave.setCategoria("TERCEIRA");
					break;
				case 3:
					chave.setNome("Chave Oitavas (4)Cat-id: " + (k + 1));
					chave.setCategoria("QUARTA");
					break;
				case 4:
					chave.setNome("Chave Oitavas (5)Cat-id: " + (k + 1));
					chave.setCategoria("QUINTA");
					break;
				case 5:
					chave.setNome("Chave Oitavas (6)Cat-id: " + (k + 1));
					chave.setCategoria("INICIANTES");
					break;
				default:
					System.out.println("Falha!!");
					break;
				}
				Jogo jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.OITAVA.ordinal());
				jogo1.setPartida("Vencedor Chave(" + (1) + ") X Vencedor Chave(" + (i + 1) + ")");
				Jogo jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo1.setPartida("Segunda Chave(" + (1) + ") X Segunda Chave(" + (i + 1) + ")");
				jogo2.setEtapa(Etapa.OITAVA.ordinal());
				chave.setJogos(List.of(jogo1, jogo2));
				torneio.getChaves().add(chave); // add uma chave cada uma com 2 jogos e 4 duplas
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
			}
		}

		for (int j = 0; j < 6; j++) {// 6 categorias
			i = 1;
			for (int k = 0; k < 4; k++) { // 4 chaves
				Chave chave = new Chave();
				switch (j) { // 32 duplas cadastratdas
				case 0:
					chave.setNome("Chave Quartas (1)Cat-id: " + (k + 1));
					chave.setCategoria("PRIMEIRA");
					break;
				case 1:
					chave.setNome("Chave Quartas (2)Cat-id: " + (k + 1));
					chave.setCategoria("SEGUNDA");
					break;
				case 2:
					chave.setNome("Chave Quartas (3)Cat-id: " + (k + 1));
					chave.setCategoria("TERCEIRA");
					break;
				case 3:
					chave.setNome("Chave Quartas (4)Cat-id: " + (k + 1));
					chave.setCategoria("QUARTA");
					break;
				case 4:
					chave.setNome("Chave Quartas (5)Cat-id: " + (k + 1));
					chave.setCategoria("QUINTA");
					break;
				case 5:
					chave.setNome("Chave Quartas (6)Cat-id: " + (k + 1));
					chave.setCategoria("INICIANTE");
					break;
				default:
					System.out.println("Falha!!");
					break;
				}
				Jogo jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.QUARTA.ordinal());
				jogo1.setPartida("Vencedor Oitavas(" + (1) + ") X Vencedor Chave(" + (i + 1) + ")");
				Jogo jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo1.setPartida("Segundo Oitavas(" + (1) + ") X Segundo Oitavas(" + (i + 1) + ")");
				jogo2.setEtapa(Etapa.QUARTA.ordinal());
				chave.setJogos(List.of(jogo1, jogo2));
				torneio.getChaves().add(chave); // add uma chave cada uma com 2 jogos e 4 duplas
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
			}
		}
//
//		for (int j = 0; j < 6; j++) {// 6 categorias
//			i = 1;
//			for (int k = 0; k < 2; k++) { // 2 chaves
//				Chave chave = new Chave();
//				switch (j) { // 32 duplas cadastratdas
//				case 0:
//					chave.setNome("Chave Semi (1)Cat-id: " + (k + 1));
//					chave.setCategoria(Categoria.PRIMEIRA.name());
//					break;
//				case 1:
//					chave.setNome("Chave Semi (2)Cat-id: " + (k + 1));
//					chave.setCategoria(Categoria.SEGUNDA.name());
//					break;
//				case 2:
//					chave.setNome("Chave Semi (3)Cat-id: " + (k + 1));
//					chave.setCategoria(Categoria.TERCEIRA.name());
//					break;
//				case 3:
//					chave.setNome("Chave Semi (4)Cat-id: " + (k + 1));
//					chave.setCategoria(Categoria.QUARTA.name());
//					break;
//				case 4:
//					chave.setNome("Chave Semi (5)Cat-id: " + (k + 1));
//					chave.setCategoria(Categoria.QUINTA.name());
//					break;
//				case 5:
//					chave.setNome("Chave Semi (6)Cat-id: " + (k + 1));
//					chave.setCategoria(Categoria.INICIANTES.name());
//					break;
//				default:
//					System.out.println("Falha!!");
//					break;
//				}
//				Jogo jogo1 = new Jogo();
//				jogo1.setChave(chave);
//				jogo1.setEtapa(Etapa.SEMI.ordinal());
//				jogo1.setPartida("Vencedor Quartas(" + (1) + ") X Vencedor Quartas(" + (i + 1) + ")");
//				Jogo jogo2 = new Jogo();
//				jogo2.setChave(chave);
//				jogo1.setPartida("Segundo Quartas(" + (1) + ") X Segundo Quartas(" + (i + 1) + ")");
//				jogo2.setEtapa(Etapa.SEMI.ordinal());
//				chave.setJogos(List.of(jogo1, jogo2));
//				torneio.getChaves().add(chave); // add uma chave cada uma com 2 jogos e 4 duplas
//				jr = new JogoResource();
//				jr.registraJogo(jogo1);
//				jr.registraJogo(jogo2);
//				chaver = new ChaveResource();
//				chaver.registraChave(chave);
//			}
//		}
//
//		for (int j = 0; j < 6; j++) {// 6 categorias
//			i = 1;
//			Chave chave = new Chave();
//			switch (j) { // 32 duplas cadastratdas
//			case 0:
//				chave.setNome("Chave Final");
//				chave.setCategoria(Categoria.PRIMEIRA.name());
//				break;
//			case 1:
//				chave.setNome("Chave Final");
//				chave.setCategoria(Categoria.SEGUNDA.name());
//				break;
//			case 2:
//				chave.setNome("Chave Final");
//				chave.setCategoria(Categoria.TERCEIRA.name());
//				break;
//			case 3:
//				chave.setNome("Chave Final");
//				chave.setCategoria(Categoria.QUARTA.name());
//				break;
//			case 4:
//				chave.setNome("Chave Final");
//				chave.setCategoria(Categoria.QUINTA.name());
//				break;
//			case 5:
//				chave.setNome("Chave Final");
//				chave.setCategoria(Categoria.INICIANTES.name());
//				break;
//			default:
//				System.out.println("Falha!!");
//				break;
//			}
//			Jogo jogo1 = new Jogo();
//			jogo1.setChave(chave);
//			jogo1.setEtapa(Etapa.FINAL.ordinal());
//			jogo1.setPartida("Vencedor Semi(" + (1) + ") X Vencedor Semi(" + (i + 1) + ")");
//			chave.setJogos(List.of(jogo1));
//			torneio.getChaves().add(chave); // add uma chave cada uma com 2 jogos e 4 duplas
//			jr = new JogoResource();
//			jr.registraJogo(jogo1);
//			chaver = new ChaveResource();
//			chaver.registraChave(chave);
//		}
//		TorneioResource tr = new TorneioResource();
//		tr.registraTorneio(torneio);
	}

	public List<Chave> gerarListaInscritos()
			throws ParseException, RemoteException, MalformedURLException, NotBoundException {
		dr = new DuplaResource();
		List<Dupla> duplas = (List<Dupla>) dr.listaDuplas();
		Torneio torneio = new Torneio();

		while (duplas.size() % 3 != 0) {
			duplas.remove(duplas.size() - 1);
		}
		List<Dupla> listaChaveamento = new ArrayList<Dupla>();
		for (IElement element : duplas) {
			Dupla d = (Dupla) element;
			listaChaveamento.add(d);
		}

		return torneio.montarChave(listaChaveamento);

	}

	public Jogo distribuirHorarios(Chave chave, Dupla d1, Dupla d2, Jogo jogo) {
		List<List<String>> hrs = this.getHorarios();
		if (jogo.getEtapa() == Etapa.MATA_MATA.ordinal()) {
			if (!d1.getImpedimento().equals(Impedimento.NENHUM.name())) {
				switch (d1.getImpedimento().toString()) {
				case "QUINTA":
					hrs.remove(horariosQuinta);
					break;
				case "SEXTA":
					hrs.remove(horariosSexta);
					break;
				case "SABADO":
					hrs.remove(horariosSabadoManha);
					break;
				}
			}

			if (!d2.getImpedimento().equals(Impedimento.NENHUM.name())) {
				switch (d2.getImpedimento().toString()) {
				case "QUINTA":
					hrs.remove(horariosQuinta);
					break;
				case "SEXTA":
					hrs.remove(horariosSexta);
					break;
				case "SABADO":
					hrs.remove(horariosSabadoManha);
					break;
				}
			}
		}

		jogo.setData(hrs.get(0).remove(0));

		return jogo;
	}

	public List<List<String>> getHorarios() {
		List<List<String>> horarios = new ArrayList<List<String>>();
		horariosQuinta.add("QUINTA-FEIRA 18:00");
		horariosQuinta.add("QUINTA-FEIRA 18:50");
		horariosQuinta.add("QUINTA-FEIRA 19:40");
		horariosQuinta.add("QUINTA-FEIRA 20:30");
		horariosQuinta.add("QUINTA-FEIRA 21:20");
		horariosQuinta.add("QUINTA-FEIRA 22:10");
		horariosQuinta.add("QUINTA-FEIRA 23:00");
		horariosSexta.add("SEXTA-FEIRA 18:00");
		horariosSexta.add("SEXTA-FEIRA 18:50");
		horariosSexta.add("SEXTA-FEIRA 19:40");
		horariosSexta.add("SEXTA-FEIRA 20:30");
		horariosSexta.add("SEXTA-FEIRA 21:20");
		horariosSexta.add("SEXTA-FEIRA 22:10");
		horariosSexta.add("SEXTA-FEIRA 23:00");
		horariosSabadoManha.add("SÁBADO 08:00");
		horariosSabadoManha.add("SÁBADO 08:50");
		horariosSabadoManha.add("SÁBADO 09:40");
		horariosSabadoManha.add("SÁBADO 10:30");
		horariosSabadoManha.add("SÁBADO 11:20");
		horariosSabadoTarde.add("SÁBADO 13:50");
		horariosSabadoTarde.add("SÁBADO 14:40");
		horariosSabadoTarde.add("SÁBADO 15:30");
		horariosSabadoTarde.add("SÁBADO 16:20");
		horariosSabadoTarde.add("SÁBADO 17:10");
		horariosSabadoNoite.add("SÁBADO 18:00");
		horariosSabadoNoite.add("SÁBADO 18:50");
		horariosSabadoNoite.add("SÁBADO 19:40");
		horariosSabadoNoite.add("SÁBADO 20:30");
		horariosSabadoNoite.add("SÁBADO 21:20");
		horariosSabadoNoite.add("SÁBADO 22:10");
		horariosSabadoNoite.add("SÁBADO 23:00");
		horariosDomingoManha.add("DOMINGO 08:00");
		horariosDomingoManha.add("DOMINGO 08:50");
		horariosDomingoManha.add("DOMINGO 09:40");
		horariosDomingoManha.add("DOMINGO 10:30");
		horariosDomingoManha.add("DOMINGO 11:20");
		horariosDomingoTarde.add("DOMINGO 13:50");
		horariosDomingoTarde.add("DOMINGO 14:40");
		horariosDomingoTarde.add("DOMINGO 15:30");
		horariosDomingoTarde.add("DOMINGO 16:20");
		horariosDomingoTarde.add("DOMINGO 17:10");
		horariosDomingoNoite.add("DOMINGO 18:00");
		horariosDomingoNoite.add("DOMINGO 18:50");
		horariosDomingoNoite.add("DOMINGO 19:40");
		horariosDomingoNoite.add("DOMINGO 20:30");
		horariosDomingoNoite.add("DOMINGO 21:20");
		horariosDomingoNoite.add("DOMINGO 22:10");
		horariosDomingoNoite.add("DOMINGO 23:00");
		horarios.add(horariosQuinta);
		horarios.add(horariosSexta);
		horarios.add(horariosSabadoManha);
		horarios.add(horariosSabadoTarde);
		horarios.add(horariosSabadoNoite);
		horarios.add(horariosDomingoManha);
		horarios.add(horariosDomingoTarde);
		horarios.add(horariosDomingoNoite);
		return horarios;
	}

	public Jogo setarQuadra(Jogo jogo, String categoria) {
		switch (categoria) {
		case "PRIMEIRA":
			if (this.torneio.getDistribuicaoJogos()[0] == 1) {
				jogo.setQuadra(new Quadra(1));
			} else if (this.torneio.getDistribuicaoJogos()[0] == 2) {
				jogo.setQuadra(new Quadra(2));
			} else if (this.torneio.getDistribuicaoJogos()[0] == 3) {
				jogo.setQuadra(new Quadra(3));
			}
			break;
		case "SEGUNDA":
			if (this.torneio.getDistribuicaoJogos()[1] == 1) {
				jogo.setQuadra(new Quadra(1));
			} else if (this.torneio.getDistribuicaoJogos()[1] == 2) {
				jogo.setQuadra(new Quadra(2));
			} else if (this.torneio.getDistribuicaoJogos()[1] == 3) {
				jogo.setQuadra(new Quadra(3));
			}
			break;
		case "TERCEIRA":
			if (this.torneio.getDistribuicaoJogos()[2] == 1) {
				jogo.setQuadra(new Quadra(1));
			} else if (this.torneio.getDistribuicaoJogos()[2] == 2) {
				jogo.setQuadra(new Quadra(2));
			} else if (this.torneio.getDistribuicaoJogos()[2] == 3) {
				jogo.setQuadra(new Quadra(3));
			}
			break;
		case "QUARTA":
			if (this.torneio.getDistribuicaoJogos()[3] == 1) {
				jogo.setQuadra(new Quadra(1));
			} else if (this.torneio.getDistribuicaoJogos()[3] == 2) {
				jogo.setQuadra(new Quadra(2));
			} else if (this.torneio.getDistribuicaoJogos()[3] == 3) {
				jogo.setQuadra(new Quadra(3));
			}
			break;
		case "QUINTA":
			if (this.torneio.getDistribuicaoJogos()[4] == 1) {
				jogo.setQuadra(new Quadra(1));
			} else if (this.torneio.getDistribuicaoJogos()[4] == 2) {
				jogo.setQuadra(new Quadra(2));
			} else if (this.torneio.getDistribuicaoJogos()[4] == 3) {
				jogo.setQuadra(new Quadra(3));
			}
			break;
		case "INICIANTES":
			if (this.torneio.getDistribuicaoJogos()[5] == 1) {
				jogo.setQuadra(new Quadra(1));
			} else if (this.torneio.getDistribuicaoJogos()[5] == 2) {
				jogo.setQuadra(new Quadra(2));
			} else if (this.torneio.getDistribuicaoJogos()[5] == 3) {
				jogo.setQuadra(new Quadra(3));
			}
		}
		return jogo;
	}

}
