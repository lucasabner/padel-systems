package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import fourzeta.desktop_views.CadastrarCircuito;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;

public class CadastrarCircuitoController implements ActionListener {

	private CadastrarCircuito tela;
	private CircuitoResource cr;
	private Circuito circuito;
	private Usuario usuario;

	public CadastrarCircuitoController(Usuario usuario, CadastrarCircuito tela) {
		this.tela = tela;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (circuito.bindCircuito(tela).getNome().isEmpty() || circuito.bindCircuito(tela).getDescricao().isEmpty()) {
			tela.notifyCampoIncompleto();
		} else {
			try {
				cr = new CircuitoResource();
				cr.registraCircuito(circuito.bindCircuito(tela));
				tela.notifyCadastroRealizado();
				tela.setVisible(false);
				SelecionarTorneio gerenciar = new SelecionarTorneio(usuario);
				gerenciar.setVisible(true);
			} catch (IOException | ParseException | NotBoundException e) {
				e.printStackTrace();
			}

		}
	}

}
