package fourzeta.controllers.desktop;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
				this.notifyCampoVazio();
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
			this.notifyUsuarioIncorreto();

		}
		return false;
	}

	public JButton btnSair(JButton sair) {
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		sair.setBounds(332, 154, 89, 25);
		sair.setFont(new Font("Times New Roman", Font.BOLD, 12));
		return sair;
	}

	public Usuario bindUsuario(Login tela) throws ParseException {

		Usuario log = new Usuario();
		log.setNickname(tela.getTxtUsuario().getText());
		log.setSenha(tela.getTxtSenha().getText());

		return log;

	}

	public void notifyCampoVazio() {
		JOptionPane.showMessageDialog(null, "Preencha todos Campos para Entrar!");

	}

	public void notifyUsuarioIncorreto() {
		JOptionPane.showMessageDialog(null, "Usuário e(ou) Senha Incorreto! \nDigite novamente!");

	}

}