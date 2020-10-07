package fourZetaTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fourzeta.controllers.desktop.EncerrarController;
import fourzeta.models.Atleta;
import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Jogo;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.AtletaResource;
import fourzeta.resources.ChaveResource;
import fourzeta.resources.CircuitoResource;
import fourzeta.resources.DuplaResource;
import fourzeta.resources.JogoResource;
import fourzeta.resources.TorneioResource;
import fourzeta.resources.UsuarioResource;

public class GerarChavesTest {
	Usuario u;
	UsuarioResource uR;
	Circuito c;
	CircuitoResource cR;
	Torneio t;
	TorneioResource tR;
	Atleta a1;
	Atleta a2;
	AtletaResource aR;
	Dupla dupla;
	DuplaResource dR;
	EncerrarController ec;
	Chave ch;
	Torneio torneio = new Torneio();

	@Before
	public void setUp() throws Exception {
		torneio = new Torneio();
		tR = new TorneioResource();
		tR.listaTorneios();

		for (Torneio t : tR.listaTorneios()) {
			if (t.getId() == 2) {
				torneio = t;
			}
		}
	}

	@Test
	public void testGerarChaves() throws Exception {
		/* ========== Execucao ========== */
			torneio.montarChave();
			/* ========== Verificacoes ========== */
			assertNotEquals(torneio.getChaves(), null);
			assertEquals(torneio.getChaves().size(), 2);
			
		}
	
	@Test
	public void testGerarJogos() throws Exception {
		torneio.montarChave();
		/* ========== Execucao ========== */
			torneio.distribuirJogos();
			JogoResource jR =  new JogoResource();
			List<Jogo> jogos = new ArrayList<>();
			jogos = jR.listaJogos();
			Jogo jChave1 = new Jogo();
			
			for (Jogo jogo : jogos) {
				if(jogo.getChave().getId() == torneio.getChaves().get(0).getId()) {
					jChave1  = jogo;
				}
			}
			
			
			/* ========== Verificacoes ========== */
			assertNotEquals(jChave1.getDupla1(), null);
			
		}
	

	@After
	public void limpa() throws Exception {
		JogoResource jR =  new JogoResource();
		for (Jogo j :  torneio.getChaves().get(0).getJogos()) {
			jR.deletaJogo(j);
		}
		
		for (Jogo j :  torneio.getChaves().get(1).getJogos()) {
			jR.deletaJogo(j);
		}
		
		
		ChaveResource cR = new ChaveResource();
		for (Chave c : torneio.getChaves()) {
			cR.deletaChave(c);
		}
		/* ========== Execucao ========== */

		/* ========== Verificacoes ========== */

	}

}
