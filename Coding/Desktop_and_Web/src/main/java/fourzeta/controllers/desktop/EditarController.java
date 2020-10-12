package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;

import javax.swing.JButton;

import fourzeta.desktop_views.Editar;
import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Circuito;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;

public class EditarController implements ActionListener {

	private Editar tela;
	private CircuitoResource cr;
	private Circuito circuito;
	private Torneio torneio;
	private Usuario usuario;
	private String nomeEdit;

	public EditarController(Usuario usuario, String nomeEdit, Editar tela) {
		this.tela = tela;
		this.usuario = usuario;
		this.nomeEdit = nomeEdit;
		if(this.nomeEdit == "Circuito") {
			this.circuito = bindCircuito(tela);
		}else {
			this.torneio = bindTorneio(tela);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source = (JButton) arg0.getSource();
		
		if(source.getName() == "btnVoltar") {
			actionVoltar();
		}else if(source.getName() == "btnEditar" && this.nomeEdit == "Circuito"){
			if (bindCircuito(tela).getNome().isEmpty() || bindCircuito(tela).getDescricao().isEmpty()) {
				tela.notifyCampoIncompleto();
			} else {
				actionEditarCircuito();
			}
		}else if(source.getName() == "btnEditar" && this.nomeEdit == "Torneio"){
			if (bindTorneio(tela).getNome().isEmpty() || bindTorneio(tela).getDescricao().isEmpty()) {
				tela.notifyCampoIncompleto();
			} else {
				actionEditarTorneio();
			}
		}
	}
	
	private void actionEditarCircuito() {
		try {
			cr = new CircuitoResource();
			cr.registraCircuito(circuito);
			tela.notifyEdicaoRealizada();
			tela.setVisible(false);
			SelecionarTorneio gerenciar = new SelecionarTorneio(usuario);
			gerenciar.setVisible(true);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void actionEditarTorneio() { //IMPLEMENTAR
		
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
	
	public Circuito bindCircuito(Editar tela) { //IMPLEMENTAR

		Circuito circuito = new Circuito();
		//circuito.setNome(tela.getTextNomeCircuito().getText());
		///circuito.setDescricao(tela.getTextDescricaoCircuito().getText());

		return circuito;

	}

	public Torneio bindTorneio(Editar tela) {//IMPLEMENTAR

		Torneio torneio = new Torneio();
		

		return torneio;

	}
	
}
