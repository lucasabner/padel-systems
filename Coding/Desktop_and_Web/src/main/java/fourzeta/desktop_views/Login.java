package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import fourzeta.controllers.desktop.LoginController;


public class Login extends JFrame {

	private final Dimension SIZE = new Dimension(480, 250);
	LoginController loginController = new LoginController(this);
	private JLabel lblIcon;
	private JLabel lblImagem;
	private JLabel lblSenha;
	private JLabel lblUsurio;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	private JButton btnEntrar;
	private JButton btnSair;

	public Login() throws ParseException, IOException {
		configFrame();
		this.getContentPane().add(configLblImagem());
		this.getContentPane().add(configLblIcon());
		this.getContentPane().add(confiLblUsuario());
		this.getContentPane().add(configTxtUsuario());
		this.getContentPane().add(confiLblSenha());
		this.getContentPane().add(configTxSenha());
		this.getContentPane().add(configBtnEntrar());
		this.getContentPane().add(configBtnSair());

	}

	public void configFrame() {
		setTitle("Login");
		this.setSize(SIZE);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// Setando Imagem
	private JLabel configLblImagem() throws IOException {
		lblImagem = new JLabel("");
		lblImagem.setBounds(43, 33, 154, 146);
		getContentPane().add(lblImagem);

		BufferedImage img = ImageIO.read(new File("assets/FourZeta_Transparente.png"));
		img.getScaledInstance(10, 10, 10);

		ImageIcon imagemZeta = new ImageIcon(
				img.getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH));
		lblImagem.setIcon(imagemZeta);
		return lblImagem;

	}

	// Setando Icon
	public JLabel configLblIcon() {
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);
		return lblIcon;
	}

	private JLabel confiLblUsuario() {
		lblUsurio = new JLabel("Usuário:");
		lblUsurio.setBounds(221, 33, 66, 15);
		lblUsurio.setFont(new Font("Times New Roman", Font.BOLD, 14));
		getContentPane().add(lblUsurio);
		return lblUsurio;
	}

	public JTextField configTxtUsuario() {
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(221, 54, 200, 19);
		txtUsuario.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().add(txtUsuario);
		return txtUsuario;
	}

	public JLabel confiLblSenha() {
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(221, 85, 66, 15);
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 14));
		getContentPane().add(lblSenha);
		return lblSenha;

	}

	public JTextField configTxSenha() {
		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(221, 106, 200, 19);
		txtSenha.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().add(txtSenha);
		return txtSenha;

	}

	private JButton configBtnEntrar() {
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(221, 154, 89, 25);
		btnEntrar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		getContentPane().add(btnEntrar);
		btnEntrar.addActionListener(loginController);
		return btnEntrar;
	}

	private JButton configBtnSair() {
		btnSair = new JButton("Sair");
		getContentPane().add(loginController.btnSair(btnSair));

		return btnSair;
	}

	public static void main(String[] args) throws IOException, ParseException {
		Login frame = new Login();
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
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
