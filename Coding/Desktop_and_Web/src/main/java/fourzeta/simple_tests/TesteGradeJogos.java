package fourzeta.simple_tests;

import java.text.ParseException;
import fourzeta.desktop_views.GradeJogos;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.TorneioResource;

public class TesteGradeJogos {

	public static void main(String[] args) throws ParseException {
		TorneioResource tr = new TorneioResource();
		Torneio torneio = null;
	    for(Torneio t : tr.listaTorneios()) {
	    	if(t.getId() == 69) {
	    		torneio = t;
	    	}
	    }
		Usuario usuario = new Usuario();
		usuario.setNome("FOURZETA");
		GradeJogos tela = new GradeJogos(usuario, torneio);
		tela.setVisible(true);
	}

}
