package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fourzeta.controllers.desktop.LoginController;

public class Login extends JFrame {

	private final Dimension SIZE = new Dimension(480, 250);
	private JLabel lblIcon;
	private JLabel lblImagem;
	private JLabel lblSenha;
	private JLabel lblUsurio;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	private JButton btnEntrar;
	private JButton btnSair;

	public Login() throws ParseException, IOException {

		setTitle("Login");
		this.setSize(SIZE);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		lblImagem = new JLabel("");
		lblImagem.setBounds(43, 33, 154, 146);
		getContentPane().add(lblImagem);

		BufferedImage img = ImageIO.read(new File("assets/FourZeta_Transparente.png"));
		img.getScaledInstance(10, 10, 10);

		ImageIcon imagemZeta = new ImageIcon(
				img.getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH));
		lblImagem.setIcon(imagemZeta);

		// mudando o icon
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);

		lblUsurio = new JLabel("Usuário:");
		lblUsurio.setBounds(221, 33, 66, 15);
		lblUsurio.setFont(new Font("Times New Roman", Font.BOLD, 14));
		getContentPane().add(lblUsurio);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(221, 54, 200, 19);
		txtUsuario.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().add(txtUsuario);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(221, 85, 66, 15);
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 14));
		getContentPane().add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(221, 106, 200, 19);
		txtSenha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().add(txtSenha);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(221, 154, 89, 25);
		btnEntrar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		getContentPane().add(btnEntrar);
		LoginController loginController = new LoginController(this);
		btnEntrar.addActionListener(loginController);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBounds(332, 154, 89, 25);
		btnSair.setFont(new Font("Times New Roman", Font.BOLD, 12));
		getContentPane().add(btnSair);

	}

	public static void main(String[] args) throws IOException, ParseException {
		Login frame = new Login();
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}

	public void notifyCampoVazio() {
		JOptionPane.showMessageDialog(null, "Preencha todos Campos para Entrar!");

	}

	public void notifyUsuarioIncorreto() {
		JOptionPane.showMessageDialog(null, "Usuário e(ou) Senha Incorreto! \nDigite novamente!");

	}

	public JLabel getLblIcon() {
		return lblIcon;
	}

	public void setLblIcon(JLabel lblIcon) {
		this.lblIcon = lblIcon;
	}

	public JLabel getLblImagem() {
		return lblImagem;
	}

	public void setLblImagem(JLabel lblImagem) {
		this.lblImagem = lblImagem;
	}

	public JLabel getLblSenha() {
		return lblSenha;
	}

	public void setLblSenha(JLabel lblSenha) {
		this.lblSenha = lblSenha;
	}

	public JLabel getLblUsurio() {
		return lblUsurio;
	}

	public void setLblUsurio(JLabel lblUsurio) {
		this.lblUsurio = lblUsurio;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JTextField getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(JTextField txtSenha) {
		this.txtSenha = txtSenha;
	}

	public JButton getBtnEntrar() {
		return btnEntrar;
	}

	public void setBtnEntrar(JButton btnEntrar) {
		this.btnEntrar = btnEntrar;
	}

	public JButton getBtnSair() {
		return btnSair;
	}

	public void setBtnSair(JButton btnSair) {
		this.btnSair = btnSair;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

}
