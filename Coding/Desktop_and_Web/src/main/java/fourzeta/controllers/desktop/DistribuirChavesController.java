package fourzeta.controllers.desktop;

import java.util.ArrayList;
import java.util.List;

import fourzeta.desktop_views.DistribuirJogos;
import fourzeta.models.Chave;
import fourzeta.models.Dupla;
import fourzeta.models.OrderDuplasPontuacao;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.ChaveResource;

public class DistribuirChavesController {
	private Usuario usuario;
	private Torneio torneio;
	private Chave chave;
	private Dupla dupla;
	private DistribuirJogos tela;
	private List<Dupla> duplas;
	private List<Chave> chaves;


	public DistribuirChavesController(Usuario usuario, Torneio torneio, Chave chave, Dupla dupla,
			DistribuirJogos tela) {
		this.usuario = usuario;
		this.torneio = torneio;
		this.chave = chave;
		this.dupla = dupla;
		this.tela = tela;


	}

	public void montarChave() {
		retirarSuplentes();
		List<Dupla> primeira = new ArrayList<Dupla>();
		List<Dupla> segunda = new ArrayList<Dupla>();
		List<Dupla> terceira = new ArrayList<Dupla>();
		List<Dupla> quarta = new ArrayList<Dupla>();
		List<Dupla> quinta = new ArrayList<Dupla>();
		List<Dupla> sexta = new ArrayList<Dupla>();
		
		for (Dupla d : duplas ) {
			switch (d.getCategoria()) {
			case "PRIMEIRA":
				primeira.add(d);
				break;
			case "SEGUNDA":
				segunda.add(d);
				break;
			case "TERCEIRA":
				terceira.add(d);
				break;
			case "QUARTA":
				quarta.add(d);
				break;
			case "QUINTA":
				quinta.add(d);
				break;
			case "SEXTA":
				sexta.add(d);
				break;
			}
		}

		primeira.sort(new OrderDuplasPontuacao());
		segunda.sort(new OrderDuplasPontuacao());
		terceira.sort(new OrderDuplasPontuacao());
		quarta.sort(new OrderDuplasPontuacao());
		quinta.sort(new OrderDuplasPontuacao());
		sexta.sort(new OrderDuplasPontuacao());

		distribuirChaves(primeira, "PRIMEIRA");
		distribuirChaves(segunda, "SEGUNDA");
		distribuirChaves(terceira, "TERCEIRA");
		distribuirChaves(quarta, "QUARTA");
		distribuirChaves(quinta, "QUINTA");
		distribuirChaves(sexta, "SEXTA");

	}

	private void distribuirChaves(List<Dupla> duplas, String cat) {
		int numChaves = duplas.size() / 3;

		// Cria chaves
		for (int i = 0; i < numChaves; i++) {
			// criando objetos em loop
			Chave c = new Chave();
			c.setNome("Chave");
			c.setCategoria(cat);
			c.setTorneio(torneio);
			chaves.add(c);
		}

		// Primeiro linha
		int numDupla = 0;
		int aux = 0;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla1(duplas.get(numDupla));
			aux++;
			numDupla++;
		}

		// Segunda linha
		aux--;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla2(duplas.get(numDupla));
			aux--;
			numDupla++;
		}

		// Terceira linha
		aux++;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla3(duplas.get(numDupla));
			aux++;
			numDupla++;
		}
		ChaveResource chaver = new ChaveResource();
		for (Chave c : chaves) {
			chaver.registraChave(c);
		}

	}

	public void retirarSuplentes() {
		int numDuplasSemSuplentes = duplas.size() % 3;
		for (int i = 0; i < numDuplasSemSuplentes; i++) {
			duplas.remove(duplas.size() - 1); // nao salva no banco.
		}
	}
}
