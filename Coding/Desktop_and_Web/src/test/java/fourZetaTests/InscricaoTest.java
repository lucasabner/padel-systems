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
		a1.setCpf("12345");
		a1.setSexo("MASCULINO");
		a1.setNome("Atleta para teste1");

		a2 = new Atleta();
		a2.setCpf("123456");
		a2.setSexo("MASCULINO");
		a2.setNome("Atleta para teste1");

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

		/* ========== Verificacoes ========== */
		assertEquals(dupla, dR.registraDupla(dupla));
//		assertEquals(dupla, dR.deletaDupla(dupla));
//		assertEquals(a2, dR.registraDupla(dupla).getAtleta2());
//		dR.deletaDupla(dupla);
//		aR.deletaAtleta(a1);
//		aR.deletaAtleta(a2);
	}
	

	@Test
	public void testDelecaoDupla() throws Exception {

		/* ========== Execucao ========== */

		/* ========== Verificacoes ========== */
		assertEquals(dupla, dR.deletaDupla(dupla));
	}
	
	// Se quiser fazer algo depois dos testes, a ideia era deletar todos os registros de teste
	// mas o delete n ta funfando, logo, esta criando novos registros a cada teste
	 @After
		public void limparBanco() throws Exception {
		 uR.deletaUsuario(u);
		 cR.deletaCircuito(c);
		 tR.deletaTorneio(t);
	 }
}
	
	


