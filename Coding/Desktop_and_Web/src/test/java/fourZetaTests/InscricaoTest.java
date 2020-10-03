package fourZetaTests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fourzeta.models.Atleta;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.AtletaResource;
import fourzeta.resources.CircuitoResource;
import fourzeta.resources.DuplaResource;
import fourzeta.resources.TorneioResource;
import fourzeta.resources.UsuarioResource;

public class InscricaoTest {
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

	@Before
	public void setUp() throws Exception {
		u = new Usuario();
		u.setNickname("Testador");
		u.setNome("Testador1");
		u.setSenha("teste");
		uR = new UsuarioResource();
		uR.registraUsuario(u);

		c = new Circuito();
		c.setNome("Circuito para Teste");
		c.setDescricao("Testando");
		c.setUsuario(u);
		cR = new CircuitoResource();
		cR.registraCircuito(c);

		t = new Torneio();
		t.setCircuito(c);
		t.setNome("Torneio para Teste");
		t.setDescricao("Testando");
		tR = new TorneioResource();
		tR.registraTorneio(t);
		
		a1 = new Atleta();
		a1.setId(12345);
		a1.setSexo("MASCULINO");
		a1.setNome("Atleta para teste1");

		a2 = new Atleta();
		a2.setId(123456);
		a2.setSexo("MASCULINO");
		a2.setNome("Atleta para teste2");

		aR = new AtletaResource();
		aR.registraAtleta(a1);
		aR.registraAtleta(a2);

		dupla = new Dupla();
		dupla.setAtleta1(a1);
		dupla.setAtleta2(a2);
		dupla.setCategoria("INICIANTE");
		dupla.setTorneio(t);
		dR = new DuplaResource();
		
	

	}

	@Test
	public void testInscricaoDupla() throws Exception {
		/* ========== Execucao ========== */
		for (Atleta a : aR.listaAtletas()) {
			if (a.getId() == dupla.getAtleta1().getId()) {
				dupla.setAtleta1(a);

			} else if (a.getId() == dupla.getAtleta2().getId()) {
				dupla.setAtleta2(a);
			}
		}
			
		/* ========== Verificacoes ========== */
		Dupla d = dR.registraDupla(dupla);
		System.out.println(d.getTorneio().getId());
		t = d.getTorneio();
		c = d.getTorneio().getCircuito();
		u = d.getTorneio().getCircuito().getUsuario();
		
		assertEquals(dupla.getAtleta1(), d.getAtleta1());
		assertEquals(dupla.getAtleta2(), d.getAtleta2());
	}
	
	@After
	public void limpa() throws Exception {

		/* ========== Execucao ========== */
		
		/* ========== Verificacoes ========== */
		
		
	}
	
}
	
	


