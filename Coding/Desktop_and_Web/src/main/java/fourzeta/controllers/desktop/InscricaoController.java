package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import fourzeta.IElement;
import fourzeta.desktop_views.InscricaoDuplas;
import fourzeta.models.Atleta;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Ranking;
import fourzeta.models.Torneio;
import fourzeta.resources.AtletaResource;
import fourzeta.resources.CircuitoResource;
import fourzeta.resources.DuplaResource;
import fourzeta.resources.RankingResource;

public class InscricaoController implements ActionListener {

	private InscricaoDuplas tela;
	public AtletaResource ar;
	private RankingResource rr;
	public DuplaResource dr;
	public Dupla dupla;
	private CircuitoResource cr;
	public Torneio torneio;

	public InscricaoController(Torneio torneio, Dupla dupla, InscricaoDuplas tela) {
		this.dupla = dupla;
		this.tela = tela;
		this.torneio = torneio;
	}

	public InscricaoController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			if (this.dupla.bindDupla(this, tela) != null)
				this.tela.notifyCriacaoSucesso();
		} catch (ParseException | RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	public Ranking getRankingAtleta(Atleta a) throws RemoteException, MalformedURLException, NotBoundException {
		List<Ranking> pontuacoes = new ArrayList<Ranking>();
		rr = new RankingResource();
		pontuacoes.addAll((Collection<? extends Ranking>) rr.listaRankings()); // buscando os pontos dos atletas da
																				// dupla
		for (IElement element : pontuacoes) {
			Ranking rank = (Ranking) element;
			if (rank.getAtleta().getId() == dupla.getAtleta1().getId()) {
				return rank;
			}
			if (rank.getAtleta().getId() == dupla.getAtleta2().getId()) {
				return rank;
			}
		}
		return null;
	}

	public String setarImpedimento(String text) {
		switch (text) {
		case "Nenhum":
			return null;
		case "Quinta-Feira pela Noite":
			return "QUINTA";
		case "Sexta-Feira pela Noite":
			return "SEXTA";
		case "Sábado pela Manhã":
			return "SABADO";
		default:
			System.out.println("Erro!");
			return null;
		}
	}
}
