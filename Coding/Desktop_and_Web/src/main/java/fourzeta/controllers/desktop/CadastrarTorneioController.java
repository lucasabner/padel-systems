package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import fourzeta.desktop_views.CadastrarTorneio;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Circuito;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;
import fourzeta.resources.TorneioResource;

public class CadastrarTorneioController implements ActionListener {

	private CadastrarTorneio tela;
	private CircuitoResource cr;
	private TorneioResource tr;
	private Torneio torneio = new Torneio();
	private Circuito circuito;
	private Usuario usuario;

	public CadastrarTorneioController(Usuario usuario, CadastrarTorneio tela) {
		this.tela = tela;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cr = new CircuitoResource();
		JButton source = (JButton) arg0.getSource();
		if (source.getName() == "btnCadastrar") {
			for (Circuito c : cr.listaCircuitos()) {
				if (c.getNome().equalsIgnoreCase(tela.getComboBoxCircuito().getSelectedItem().toString())) {
					circuito = c;
				}
			}

			if (torneio.bindTorneio(tela).getNome().isEmpty() || torneio.bindTorneio(tela).getDescricao().isEmpty()
					|| torneio.bindTorneio(tela).getDatIniJogos().isEmpty()
					|| torneio.bindTorneio(tela).getDatFimJogos().isEmpty()) {
				tela.notifyCampoIncompleto();
			} else {
				realizarCadastro();
			}
		}

		if (this.bindTorneio(tela).getNome().isEmpty() || this.bindTorneio(tela).getDescricao().isEmpty()
				|| this.bindTorneio(tela).getDatIniJogos().isEmpty()
				|| this.bindTorneio(tela).getDatFimJogos().isEmpty()) {
			tela.notifyCampoIncompleto();
		} else {
			actionVoltar();
		}

	}

	public void realizarCadastro() {
		 tr = new TorneioResource();
		List<Torneio> torneios = new ArrayList<Torneio>();
		torneio = this.bindTorneio(tela);
		torneios.add(torneio);
		torneio.setCircuito(circuito);
		circuito.setTorneios(torneios);
		tr.registraTorneio(torneio);
		cr = new CircuitoResource();
		cr.registraCircuito(circuito);

		tela.notifyCadastroRealizado();
		tela.setVisible(false);
		SelecionarTorneio gerenciar = null;
		try {
			gerenciar = new SelecionarTorneio(usuario);
		} catch (ParseException | IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gerenciar.setVisible(true);
	}

	public Torneio bindTorneio(CadastrarTorneio tela) {

		Torneio torneio = new Torneio();
		torneio.setNome(tela.getTextNomeTorneio().getText());
		torneio.setDescricao(tela.getTextDescricaoTorneio().getText());
		torneio.setDatIniJogos(tela.getTextDataInicio().getText());
		torneio.setDatFimJogos(tela.getTextDataFim().getText());
		if (tela.getComboBoxQuadra1().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[0] = converterQuadra(tela.getComboBoxQuadra1().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra2().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[1] = converterQuadra(tela.getComboBoxQuadra2().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra3().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[2] = converterQuadra(tela.getComboBoxQuadra3().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra4().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[3] = converterQuadra(tela.getComboBoxQuadra4().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra5().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[4] = converterQuadra(tela.getComboBoxQuadra5().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra6().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[5] = converterQuadra(tela.getComboBoxQuadra6().getSelectedItem().toString());
		}

		return torneio;

	}

	public int converterQuadra(String itemSelecionado) {
		switch (itemSelecionado) {
		case "LARANJA":
			return 1;
		case "AZUL":
			return 2;
		case "VERDE":
			return 3;
		}
		return -1;
	}
}
