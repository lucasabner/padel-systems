package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;

import javax.swing.JButton;

import antlr.debug.Event;
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
		JButton source = (JButton) arg0.getSource();
		if(source.getName() == "btnCadastrar") {
			if (this.bindCircuito(tela).getNome().isEmpty() || this.bindCircuito(tela).getDescricao().isEmpty()) {
				tela.notifyCampoIncompleto();
			} else {
				actionCadastrarCircuito();
			}
		}else {
			actionVoltar();
		}
	}

	private void actionCadastrarCircuito() {
		try {
			cr = new CircuitoResource();
			cr.registraCircuito(this.bindCircuito(tela));
			tela.notifyCadastroRealizado();
			tela.setVisible(false);
			SelecionarTorneio gerenciar = new SelecionarTorneio(usuario);
			gerenciar.setVisible(true);
		} catch (IOException | ParseException | NotBoundException e) {
			e.printStackTrace();
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
	
	public Circuito bindCircuito(CadastrarCircuito tela) {

		Circuito circuito = new Circuito();
		circuito.setNome(tela.getTextNomeCircuito().getText());
		circuito.setDescricao(tela.getTextDescricaoCircuito().getText());

		return circuito;

	}
}
