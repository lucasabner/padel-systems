package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;

import javax.swing.JButton;

import fourzeta.desktop_views.ExcluirCircuito;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;

public class ExcluirCircuitoController implements ActionListener {

	private ExcluirCircuito tela;
	private CircuitoResource cr;
	private Usuario usuario;

	public ExcluirCircuitoController(Usuario usuario, ExcluirCircuito tela) {
		this.tela = tela;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source = (JButton) arg0.getSource();
		if (source.getName() == "btnExcluir") {
			try {
				cr = new CircuitoResource();
				for (Circuito circuito : cr.listaCircuitos()) {
					if (circuito.getNome().equalsIgnoreCase(tela.getComboBoxCircuito().getSelectedItem().toString())) {
						cr.deletaCircuito(circuito);
						tela.getComboBoxCircuito().removeItem(circuito.getId());
						tela.notifyExluirCircuito();
						tela.setVisible(false);
						ExcluirCircuito refresh = new ExcluirCircuito(usuario);
						refresh.setVisible(true);
					}
				}

			} catch (ParseException | IOException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			actionVoltar();
		}
	}

	private void actionVoltar() {
		SelecionarTorneio inicio = null;
		try {
			inicio = new SelecionarTorneio(usuario);
		} catch (ParseException | IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		inicio.setVisible(true);
	}

}