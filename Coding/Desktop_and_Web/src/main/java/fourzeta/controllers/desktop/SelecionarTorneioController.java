package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;

import javax.swing.JButton;

import fourzeta.desktop_views.CadastrarCircuito;
import fourzeta.desktop_views.CadastrarTorneio;
import fourzeta.desktop_views.Editar;
import fourzeta.desktop_views.ExcluirCircuito;
import fourzeta.desktop_views.ExcluirTorneio;
import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.desktop_views.Inicial;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Usuario;
import fourzeta.models.Circuito;
import fourzeta.models.Torneio;
import fourzeta.resources.TorneioResource;

public class SelecionarTorneioController implements ActionListener {

	private SelecionarTorneio tela;
	private GerenciarTorneio gerenciarTela;
	private CadastrarCircuitoController ctrlCircuito;
	private TorneioResource tr;
	private Torneio torneio;
	private Usuario usuario;

	public SelecionarTorneioController(Usuario usuario, SelecionarTorneio tela) {
		this.tela = tela;
		this.usuario = usuario;
		ctrlCircuito = new CadastrarCircuitoController();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		tr = new TorneioResource();
		JButton source = (JButton) arg0.getSource();
		switch (source.getName()) {
		case "btnSelecionar":
			openTorneio();
			break;
		case "btnVoltar":
			actionVoltar();
			break;
		case "btnNovoCircuito":
			novoCircuito();
			break;
		case "btnNovoTorneio":
			novoTorneio();
			break;
		case "btnEditarCircuito":
			editarCircuito();
			break;
		case "btnExcluirCircuito":
			excluirCircuito();
			break;
		case "btnEditarTorneio":
			editarTorneio();
			break;
		case "btnExcluirTorneio":
			excluirTorneio();
			break;
		default:
			opcoesTorneio();
		}
	}

	private void excluirTorneio() {
		ExcluirTorneio eT = null;
		try {
			eT = new ExcluirTorneio(usuario);
		} catch (ParseException | IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		eT.setVisible(true);
	}

	private void editarTorneio() {
		Editar ed = null;
		try {
			ed = new Editar(usuario, "Torneio");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		ed.setVisible(true);
	}

	private void excluirCircuito() {
		ExcluirCircuito eC = null;
		try {
			eC = new ExcluirCircuito(usuario);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		eC.setVisible(true);
	}

	private void editarCircuito() {
		Editar ed = null;
		try {
			ed = new Editar(usuario, "Circuito");
		} catch (ParseException | IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		ed.setVisible(true);
	}

	private void novoTorneio() {
		CadastrarTorneio cT = null;
		try {
			cT = new CadastrarTorneio(usuario);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		cT.setVisible(true);
	}

	private void novoCircuito() {
		CadastrarCircuito cC = null;
		try {
			cC = new CadastrarCircuito(usuario);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		cC.setVisible(true);
	}

	private void opcoesTorneio() {
		tela.getComboTorneio().removeAllItems();
		tela.getComboTorneio().addItem("Selecionar");
		for (Circuito circuito : ctrlCircuito.listarCircuitos()) {
			if (circuito.getNome().equalsIgnoreCase(tela.getComboCircuito().getSelectedItem().toString())) {
				for (int i = 0; i < circuito.getTorneios().size(); i++) {
					tela.getComboTorneio().addItem(circuito.getTorneios().get(i).getNome());
				}
			}
		}
	}

	private void openTorneio() {
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

	private void actionVoltar() {
		Inicial inicial = null;
		try {
			inicial = new Inicial(usuario);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		inicial.setVisible(true);
	}

}
