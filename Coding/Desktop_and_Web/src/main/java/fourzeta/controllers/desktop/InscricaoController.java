package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	private Dupla dupla;
	private InscricaoDuplas tela;
	private Torneio torneio;
	private CircuitoResource cr;
	private AtletaResource ar;
	private RankingResource rr;
	private DuplaResource dr;

	public InscricaoController(Torneio torneio, Dupla dupla, InscricaoDuplas tela) {
		this.dupla = dupla;
		this.tela = tela;
		this.torneio = torneio;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			if (this.bindDupla(tela) != null)
				this.tela.notifyCriacaoSucesso();
		} catch (ParseException | RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean verificarCpf(Dupla dupla) throws MalformedURLException, RemoteException, NotBoundException {
		cr = new CircuitoResource();
		Ranking rank1 = new Ranking();
		Ranking rank2 = new Ranking();
		Circuito circuito = new Circuito();

		for (Circuito c : cr.listaCircuitos()) {
			for (Torneio t : c.getTorneios()) {
				if (torneio.getNome().equalsIgnoreCase(t.getNome())) {
					circuito = c;
				}
			}
		}
//
//		rank1.setCategoria(tela.getComboCategoria().getSelectedItem().toString());
//		rank1.setCircuito(circuito);
//		rank1.setPontos(0);
//
//		rank2.setCategoria(tela.getComboCategoria().getSelectedItem().toString());
//		rank2.setCircuito(circuito);
//		rank2.setPontos(0);

		boolean encontrado1 = false, encontrado2 = false;

		ar = new AtletaResource();
		for (Atleta a : ar.listaAtletas()) {
			if (a.getId() == dupla.getAtleta1().getId()) {
//				dupla.getAtleta1().getRankings().add(this.getRankingAtleta(a));
				dupla.setAtleta1(a);
				encontrado1 = true;

			} else if (a.getId() == dupla.getAtleta2().getId()) {
				dupla.setAtleta2(a);
//				dupla.getAtleta2().getRankings().add(this.getRankingAtleta(a));
				encontrado2 = true;
			}
		}
//		rr = new RankingResource();
//		if (encontrado1 == false && encontrado2 == false) {
//			dupla.getAtleta1().getRankings().add(rank1);
//			rank1.setAtleta(dupla.getAtleta1());
//
//			dupla.getAtleta2().getRankings().add(rank2);
//			rank2.setAtleta(dupla.getAtleta2());
//			dupla.setPonTotal(rank1.getPontos() + rank2.getPontos());
//			rr.registraRanking(rank1);
//			rr.registraRanking(rank2);
//
//		} else if (encontrado1 == false && encontrado2 == true) {
//			dupla.getAtleta1().getRankings().add(rank1);
//			rank1.setAtleta(dupla.getAtleta1());
//			dupla.setPonTotal(rank1.getPontos() + rank2.getPontos());
//			rr.registraRanking(rank1);
//			ar.registraAtleta(dupla.getAtleta1());
//		} else if (encontrado1 == true && encontrado2 == false) {
//			dupla.getAtleta2().getRankings().add(rank2);
//			rank2.setAtleta(dupla.getAtleta2());
//			dupla.setPonTotal(rank1.getPontos() + rank2.getPontos());
//			rr.registraRanking(rank2);
//			ar.registraAtleta(dupla.getAtleta2());
//		}

		return true;
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

	public Dupla bindDupla(InscricaoDuplas tela)
			throws ParseException, RemoteException, MalformedURLException, NotBoundException {

		Atleta atleta1 = new Atleta();
		Atleta atleta2 = new Atleta();

		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
		atleta1.setNome(tela.getTxtNome1().getText());
		String cpfTxt = tela.getTxtCpf1().getText().replaceAll(".", "");
		cpfTxt = tela.getTxtCpf1().getText().replace("-", "");
		atleta1.setId(Long.parseLong(cpfTxt));
		atleta1.setTel(tela.getTxtTelefone1().getText());
		atleta1.setEmail(tela.getTxtEmail1().getText());
		if (tela.getCbxMasc1().isSelected()) {
			atleta1.setSexo("MASCULINO");
		} else if (tela.getCbxFem1().isSelected()) {
			atleta1.setSexo("FEMININO");
		} else {
			atleta1.setSexo("OUTRO");
		}

		atleta1.setDataNascimento(tela.getTxtDataNascimento1().getText());

		atleta2.setNome(tela.getTxtNome2().getText());
		atleta2.setId(Long.parseLong(tela.getTxtCpf2().getText()));
		atleta2.setTel(tela.getTxtTelefone2().getText());
		atleta2.setEmail(tela.getTxtEmail2().getText());
		if (tela.getCbxMasc2().isSelected()) {
			atleta2.setSexo("MASCULINO");
		} else if (tela.getCbxFem2().isSelected()) {
			atleta2.setSexo("FEMININO");
		} else {
			atleta2.setSexo("OUTRO");

		}
		dupla.setImpedimento(this.setarImpedimento(tela.getComboImpedimento().getSelectedItem().toString()));
		dupla.setCategoria(tela.getComboCategoria().getSelectedItem().toString());
		atleta2.setDataNascimento(tela.getTxtDataNascimento2().getText());
		dupla.setAtleta1(atleta1);
		dupla.setAtleta2(atleta2);
		dupla.setTorneio(torneio);
		this.verificarCpf(dupla);
		ar = new AtletaResource();
		ar.registraAtleta(dupla.getAtleta1());
		ar.registraAtleta(dupla.getAtleta2());
		dr = new DuplaResource();
		dr.registraDupla(dupla);

		return dupla;
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
