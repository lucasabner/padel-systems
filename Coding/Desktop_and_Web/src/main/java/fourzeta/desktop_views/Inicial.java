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
import javax.swing.SwingConstants;

import fourzeta.controllers.desktop.InicialGerenciarController;
import fourzeta.controllers.desktop.MostrarCreditosController;
import fourzeta.controllers.desktop.MostrarRankingController;
import fourzeta.models.Usuario;

public class Inicial extends JFrame {

	private final Dimension SIZE = new Dimension(600, 350);
	private MostrarRankingController controllerRanking;
	private MostrarCreditosController controllerCreditos;
	private InicialGerenciarController controllerGerenciar;
	private JButton btnCreditos;
	private JButton btnRanking;
	private JButton btnGerenciar;
	private JLabel lblIcon;
	private JLabel lblImage;
	private JButton btnSair;
	private JLabel lblNomeUsuario;
	private JLabel lblMenu;
	private JLabel lblUserNull = null;
	private Usuario usuario;

	public Inicial(Usuario usuario) throws ParseException, IOException {
		this.usuario = usuario;
		setTitle("Sistema de Gerenciamento de Padel");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		lblImage = new JLabel();
		lblImage.setBounds(70, 27, 235, 213);

		BufferedImage img = ImageIO.read(new File("assets/FourZeta_Transparente.png"));
		img.getScaledInstance(10, 10, 10);

		ImageIcon imagemZeta = new ImageIcon(
				img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
		lblImage.setIcon(imagemZeta);

		getContentPane().add(lblImage);

		// mudando o icon
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);

		JLabel Titulo = new JLabel("Four Zeta");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(40, 246, 290, 36);
		Titulo.setFont(new Font("Dialog", Font.BOLD, 30));
		getContentPane().add(Titulo);

		btnRanking = new JButton("Ver Ranking");
		controllerRanking = new MostrarRankingController(usuario, this);
		btnRanking.addActionListener(controllerRanking);
		btnRanking.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnRanking.setBounds(359, 177, 207, 23);
		getContentPane().add(btnRanking);

		btnGerenciar = new JButton("Gerenciar Circuitos");
		controllerGerenciar = new InicialGerenciarController(usuario, this);
		btnGerenciar.addActionListener(controllerGerenciar);
		btnGerenciar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnGerenciar.setBounds(359, 136, 207, 23);
		getContentPane().add(btnGerenciar);

		btnCreditos = new JButton("Creditos");
		controllerCreditos = new MostrarCreditosController(this);
		btnCreditos.addActionListener(controllerCreditos);
		btnCreditos.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnCreditos.setBounds(359, 217, 207, 23);
		getContentPane().add(btnCreditos);

		lblMenu = new JLabel("MENU");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 25));
		lblMenu.setBounds(393, 15, 153, 30);
		getContentPane().add(lblMenu);

		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSair.setBounds(359, 257, 207, 23);

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);

			}
		});
		getContentPane().add(btnSair);

		lblNomeUsuario = new JLabel("Usuario: " + usuario.getNome());
		lblNomeUsuario.setBounds(36, 280, 247, 30);
		getContentPane().add(lblNomeUsuario);

		lblUserNull = new JLabel("");
		lblUserNull.setBounds(131, 295, 66, 15);
		getContentPane().add(lblUserNull);

	}

	public JLabel getLblUserNull() {
		return lblUserNull;
	}

	public void setLblUserNull(JLabel lblUserNull) {
		this.lblUserNull = lblUserNull;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

	public static void main(String[] args) throws IOException, ParseException {
		Usuario g = new Usuario();
		g.setNome("Teste");
		Inicial frame = new Inicial(g);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);

	}
}
