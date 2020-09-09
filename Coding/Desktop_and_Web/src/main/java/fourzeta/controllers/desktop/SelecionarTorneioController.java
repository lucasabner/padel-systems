package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Usuario;
import fourzeta.models.Torneio;
import fourzeta.resources.TorneioResource;


public class SelecionarTorneioController implements ActionListener {

	private SelecionarTorneio tela;
	private GerenciarTorneio gerenciarTela;
	private TorneioResource tr;
	private Torneio torneio;
	private Usuario usuario;

	public SelecionarTorneioController(Usuario usuario, SelecionarTorneio tela) {
		this.tela = tela;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		tr = new TorneioResource();
		for (Torneio t : tr.listaTorneios()) {
			if (t.getNome().equalsIgnoreCase(tela.getComboTorneio().getSelectedItem().toString())) {
				torneio = t;
			}
		}
		try {
			gerenciarTela = new GerenciarTorneio(usuario, torneio);
			this.tela.setVisible(false);
			gerenciarTela.setVisible(true);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
