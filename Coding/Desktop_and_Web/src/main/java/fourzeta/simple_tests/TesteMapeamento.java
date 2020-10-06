package fourzeta.simple_tests;

import java.util.ArrayList;
import java.util.List;

import fourzeta.models.Atleta;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Ranking;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.AtletaResource;
import fourzeta.resources.CircuitoResource;
import fourzeta.resources.DuplaResource;
import fourzeta.resources.RankingResource;
import fourzeta.resources.TorneioResource;
import fourzeta.resources.UsuarioResource;

// Circuito precisa de de Usuario, Ranking precisa de Circuito e Atleta,
// Dupla precisa de Atleta e Torneio, Torneio precisa de Circuito
public class TesteMapeamento {
	public static void main(String[] args) {
		UsuarioResource ur = new UsuarioResource();
		CircuitoResource cr = new CircuitoResource();
		TorneioResource tr = new TorneioResource();
		AtletaResource ar = new AtletaResource();
		RankingResource rr = new RankingResource();
		DuplaResource dr = new DuplaResource();

//        Usuario
		Usuario u = new Usuario();
		u.setNome("User");
		u.setSenha("123");
		u.setNickname("user");

//        Circuito
		Circuito c = new Circuito();
		c.setDescricao("para mapeamento");
		c.setNome("Circuito de Mapeamento");
		c.setUsuario(u);

//        Torneio
		Torneio t = new Torneio();
		t.setCircuito(c);
		t.setNome("Torneio para Mapeamento");

//        Atletas	
		List<Atleta> atletas = new ArrayList<Atleta>();
		ArrayList<Ranking> pontuacoes = new ArrayList<Ranking>();

		for (int i = 0; i < 24; i++) {
			Atleta atleta = new Atleta();
			Ranking pts = new Ranking();
			pts.setCategoria("INICIANTES");
			pts.setPontos(i + 5);
			pts.setCircuito(c);
			pts.setAtleta(atleta);

			atleta.setNome("Nome: " + (i + 1));
			atleta.setCpf("CPF: " + (i + 1123456));
			atleta.setRankings(List.of(pts));

			pontuacoes.add(pts);

			atletas.add(atleta);
		}
		
		List<Dupla> duplas = new ArrayList<Dupla>();
		for (int j = 0; j < atletas.size(); j++) {
			Dupla dupla = new Dupla();
			dupla.setAtleta1(atletas.get(j));
			dupla.setAtleta2(atletas.get(j+1));
			dupla.setTorneio(t);
			duplas.add(dupla);
			j++;
		}
		
		ur.registraUsuario(u);
		cr.registraCircuito(c);
		tr.registraTorneio(t);
		
		for(Atleta a : atletas) {
			ar.registraAtleta(a);
		}
		
		
		for(Ranking r : pontuacoes) {
			rr.registraRanking(r);
		}
	
		for(Dupla d : duplas) {
			dr.registraDupla(d);
		}
	
		
	
		
	

		
		
		// Consulta de todas as pessoas - FindALl
		for (Circuito a : cr.listaCircuitos()) {
			System.out.println("Nome: " + a.getNome());
		}

		for (Ranking a : rr.listaRankings()) {
			System.out.println("Nome: " + a.getAtleta().getNome());
		}

	}

}
