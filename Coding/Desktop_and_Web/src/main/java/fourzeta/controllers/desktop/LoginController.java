package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;

import fourzeta.desktop_views.Inicial;
import fourzeta.desktop_views.Login;
import fourzeta.models.Usuario;
import fourzeta.resources.UsuarioResource;


public class LoginController implements ActionListener {
	private Login tela;
	private UsuarioResource ur;

	public LoginController(Login tela) {
		this.tela = tela;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (this.bindUsuario(tela).getNickname().isEmpty() || this.bindUsuario(tela).getSenha().isEmpty()) {
				tela.notifyCampoVazio();
			} else {
				this.autenticarUsuario(this.bindUsuario(tela).getNickname(), this.bindUsuario(tela).getSenha());
			}
		} catch (ParseException | RemoteException | MalformedURLException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public boolean autenticarUsuario(String user, String senha)
			throws RemoteException, MalformedURLException, NotBoundException {
		ur = new UsuarioResource();
		Usuario usuario = null;

		for (Usuario g : ur.listaUsuarios()) {
			if (g.getNickname().equals(user) && g.getSenha().equals(senha)) {
				usuario = g;
				try {
					Inicial inicial = new Inicial(usuario);
					tela.setVisible(false);
					inicial.setVisible(true);

					return true;
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
			}
			tela.notifyUsuarioIncorreto();

		}
		return false;
	}

	public Usuario bindUsuario(Login tela) throws ParseException {

		Usuario log = new Usuario();
		log.setNickname(tela.getTxtUsuario().getText());
		log.setSenha(tela.getTxtSenha().getText());

		return log;

	}
}