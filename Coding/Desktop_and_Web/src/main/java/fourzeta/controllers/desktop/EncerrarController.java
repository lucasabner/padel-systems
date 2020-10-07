package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import fourzeta.IElement;
import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.models.Chave;
import fourzeta.models.Dupla;
import fourzeta.models.Etapa;
import fourzeta.models.Impedimento;
import fourzeta.models.Jogo;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.ChaveResource;
import fourzeta.resources.DuplaResource;
import fourzeta.resources.JogoResource;
import fourzeta.resources.TorneioResource;

public class EncerrarController implements ActionListener {

	private GerenciarTorneio tela;
	private TorneioResource tr;
	private DuplaResource dr;
	private ChaveResource chaver;
	private JogoResource jr;
	private Usuario usuario;
	private Torneio torneio;
	private List<String> horariosQuinta = new ArrayList<String>();
	private List<String> horariosSexta = new ArrayList<String>();
	private List<String> horariosSabadoManha = new ArrayList<String>();
	private List<String> horariosSabadoTarde = new ArrayList<String>();
	private List<String> horariosSabadoNoite = new ArrayList<String>();
	private List<String> horariosDomingoManha = new ArrayList<String>();
	private List<String> horariosDomingoTarde = new ArrayList<String>();
	private List<String> horariosDomingoNoite = new ArrayList<String>();

	public EncerrarController(Usuario usuario, Torneio torneio, GerenciarTorneio tela) {
		this.tela = tela;
		this.usuario = usuario;
		this.torneio = torneio;
	}

	public EncerrarController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!this.torneio.isInscEncerradas()) {
			tr = new TorneioResource();
			this.torneio.setInscEncerradas(true);
			tr.registraTorneio(this.torneio);
			try {
				organizarJogos();
				this.tela.notifyEncerramentoSucesso();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.tela.notifyInscricoesJaEncerradas();
		}

	}

	public void organizarJogos() throws RemoteException {
		//ordenar por horairo
		//verifica e retira os suplentes da lista e depois ordena por ponto
		//montaChaves e monta jogos
       // torneio.montarChave(duplas2)
		torneio.montarChave();
		torneio.distribuirJogos();
//		int cat1 = 0, cat2 = 0, cat3 = 0, cat4 = 0, cat5 = 0, cat6 = 0;
//		for (Chave chave : torneio.getChaves()) {
//			switch (chave.getCategoria()) {
//			case "PRIMEIRA":
//				cat1++;
//				break;
//			case "SEGUNDA":
//				cat2++;
//				break;
//			case "TERCEIRA":
//				cat3++;
//				break;
//			case "QUARTA":
//				cat4++;
//				break;
//			case "QUINTA":
//				cat5++;
//				break;
//			case "SEXTA":
//				cat6++;
//				break;
//			default:
//				break;
//			}
//		}
//		iniciarMataMata(cat1, "PRIMEIRA");
//		iniciarMataMata(cat2, "SEGUNDA");
//		iniciarMataMata(cat3, "TERCEIRA");
//		iniciarMataMata(cat4, "QUARTA");
//		iniciarMataMata(cat5, "QUINTA");
//		iniciarMataMata(cat6, "INICIANTE");

	}


	private void registraJogoChave(Jogo jogo, Chave chave) {
		chave.getJogos().addAll((List.of(jogo)));
		torneio.getChaves().add(chave);
		jr = new JogoResource();
		jr.registraJogo(jogo);
		chaver = new ChaveResource();
		chaver.registraChave(chave);
	}

	private void iniciarMataMata(int numChave, String cat) {
		if (numChave != 0) {
			Chave chave = new Chave();
			Jogo jogo = new Jogo();
			TorneioResource tr = new TorneioResource();
			switch (numChave) {
			case 6:
				chave = new Chave();
				chave.setNome("Semi(1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.SEMI.name());
				jogo.setPartida("* X *");

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setPartida("* X *");
				jogo.setEtapa(Etapa.SEMI.name());

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Final");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.FINAL.name());
				jogo.setPartida("* X *");

				registraJogoChave(jogo, chave);

				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 9:
				chave = new Chave();
				chave.setNome("Quartas(Q1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.SEMI.name());
				jogo.setPartida("* X *");

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setPartida("* X *");
				jogo.setEtapa(Etapa.SEMI.name());

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Final");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.FINAL.name());
				jogo.setPartida("S1 X S2");

				registraJogoChave(jogo, chave);

				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 12:
				// Verifica de acorco com o doc disponibilizado pelo professor Sam
				chave = new Chave();
				chave.setNome("Quartas(Q1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q3)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q4)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.SEMI.name());
				jogo.setPartida("Q1 X Q2");

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setPartida("Q3 X Q4");
				jogo.setEtapa(Etapa.SEMI.name());

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Final");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.FINAL.name());
				jogo.setPartida("S1 X S2");

				registraJogoChave(jogo, chave);

				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 15:
				// Verifica de acorco com o doc disponibilizado pelo professor Sam
				chave = new Chave();
				chave.setNome("Oitavas(O1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q3)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q4)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.SEMI.name());
				jogo.setPartida("Q1 X Q2");

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setPartida("Q3 X Q4");
				jogo.setEtapa(Etapa.SEMI.name());

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Final");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.FINAL.name());
				jogo.setPartida("S1 X S2");

				registraJogoChave(jogo, chave);

				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 18:
				// Verifica de acorco com o doc disponibilizado pelo professor Sam
				chave = new Chave();
				chave.setNome("Oitavas(O1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O3)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O4)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q3)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q4)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("* X *");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.SEMI.name());
				jogo.setPartida("Q1 X Q2");

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setPartida("Q3 X Q4");
				jogo.setEtapa(Etapa.SEMI.name());

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Final");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.FINAL.name());
				jogo.setPartida("S1 X S2");

				registraJogoChave(jogo, chave);

				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 21:
				// Verifica de acorco com o doc disponibilizado pel o professor Sam 
				chave = new Chave();
				chave.setNome("Oitavas(O1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("2F X 2G");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("2C X 1E");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O3)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1D X 2B");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O4)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1C X 2A");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O5)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1F X 2D");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O6)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("2E X 1G");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("1A X O1");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("O2 X O3");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q3)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("O4 X O5");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q4)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("1B X O6");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.SEMI.name());
				jogo.setPartida("Q1 X Q2");

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setPartida("Q3 X Q4");
				jogo.setEtapa(Etapa.SEMI.name());

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Final");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.FINAL.name());
				jogo.setPartida("S1 X S2");

				registraJogoChave(jogo, chave);

				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 24:
				// Verifica de acorco com o doc disponibilizado pelo professor Sam
				chave = new Chave();
				chave.setNome("Oitavas(O1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1A X 2B");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1H X 2G");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O3)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1E X 2F");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O4)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1D X 2C");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O5)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1C X 2D");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O6)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1F X 2E");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O7)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1G X 2H");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Oitavas(O8)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.OITAVA.name());
				jogo.setPartida("1B X 2A");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("O1 X O2");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("O3 X O4");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q3)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("O5 X O6");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Quartas(Q4)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.QUARTA.name());
				jogo.setPartida("O7 X O8");
				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S1)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.SEMI.name());
				jogo.setPartida("Q1 X Q2");

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Semi(S2)");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setPartida("Q3 X Q4");
				jogo.setEtapa(Etapa.SEMI.name());

				registraJogoChave(jogo, chave);

				chave = new Chave();
				chave.setNome("Final");
				chave.setCategoria(cat);
				jogo = new Jogo();
				jogo.setChave(chave);
				jogo.setEtapa(Etapa.FINAL.name());
				jogo.setPartida("S1 X S2");

				registraJogoChave(jogo, chave);

				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			default:
				break;
			}
		}
	}

//	public List<Chave> gerarListaInscritos()
//			throws ParseException, RemoteException, MalformedURLException, NotBoundException {
//		dr = new DuplaResource();
//		List<Dupla> duplas = (List<Dupla>) dr.listaDuplas();
//		Torneio torneio = new Torneio();
//
//		while (duplas.size() % 3 != 0) {
//			duplas.remove(duplas.size() - 1);
//		}
//		List<Dupla> listaChaveamento = new ArrayList<Dupla>();
//		for (IElement element : duplas) {
//			Dupla d = (Dupla) element;
//			listaChaveamento.add(d);
//		}
//
//		return torneio.montarChave(listaChaveamento);
//
//	}

	public Jogo distribuirHorarios(Chave chave, Dupla d1, Dupla d2, Jogo jogo) {
		List<List<String>> hrs = this.getHorarios();
		if (jogo.getEtapa() == Etapa.CHAVEAMENTO.name()) {
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

}
