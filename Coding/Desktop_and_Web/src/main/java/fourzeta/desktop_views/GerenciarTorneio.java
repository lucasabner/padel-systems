package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import fourzeta.controllers.desktop.EncerrarController;
import fourzeta.controllers.desktop.GradeJogosController;
import fourzeta.controllers.desktop.InicialInscricaoController;
import fourzeta.controllers.desktop.RetornarInicial;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.TorneioResource;

public class GerenciarTorneio extends JFrame {

	private final Dimension SIZE = new Dimension(600, 350);
	private InicialInscricaoController controllerInscricao;
	private EncerrarController controllerEncerrar;
	private GradeJogosController controllerGrade;
	private JButton btnInscricao;
	private JButton btnEncerrar;
	private JLabel lblIcon;
	private JLabel lblImagem;
	private JButton btnSair;
	private JLabel lblNomeUsuario;
	private JButton btnGradeDeJogos;
	private JLabel lblMenu;
	private Usuario usuario;
	private Torneio torneio;

	public GerenciarTorneio(Usuario usuario, Torneio torneio) throws ParseException, IOException {

		configFrame();
		this.getContentPane().add(configLblMenu());
		this.getContentPane().add(configLblImagem());
		this.getContentPane().add(configLblicon());
		this.getContentPane().add(configLblTitulo());
		this.getContentPane().add(configLblUsuario(usuario));
		this.getContentPane().add(configBtnInscreverDuplas());
		this.getContentPane().add(configBtnEncerrar());
		this.getContentPane().add(configBtnGradeJogos(usuario, torneio));
		this.getContentPane().add(configBtnVoltar(usuario));
	}

	private void configFrame() {
		setTitle("Sistema de Gerenciamento de Padel");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	// Setando Menu
	private JLabel configLblMenu() {
		lblMenu = new JLabel("MENU");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblMenu.setBounds(359, 43, 207, 30);
		getContentPane().add(lblMenu);
		return lblMenu;
	}

	// Setando Imagem
	private JLabel configLblImagem() throws IOException {
		lblImagem = new JLabel();
		lblImagem.setBounds(70, 27, 235, 213);

		BufferedImage img = ImageIO.read(new File("assets/FourZeta_Transparente.png"));
		img.getScaledInstance(10, 10, 10);

		ImageIcon imagemZeta = new ImageIcon(
				img.getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH));
		lblImagem.setIcon(imagemZeta);

		getContentPane().add(lblImagem);
		return lblImagem;
	}

	// Setando o Icon
	private JLabel configLblicon() {
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);
		return lblIcon;
	}

	// Setando Título
	private JLabel configLblTitulo() {
		JLabel Titulo = new JLabel("Four Zeta");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(40, 246, 290, 36);
		Titulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		getContentPane().add(Titulo);
		return Titulo;
	}

	// Setando label Usuário
	private JLabel configLblUsuario(Usuario usuario) {
		lblNomeUsuario = new JLabel("Usuario: " + usuario.getNome());
		lblNomeUsuario.setBounds(36, 280, 247, 30);
		lblNomeUsuario.setFont(new Font("Times New Roman", Font.BOLD, 16));
		getContentPane().add(lblNomeUsuario);

		return lblNomeUsuario;
	}

	private JButton configBtnInscreverDuplas() {
		btnInscricao = new JButton("Inscrever Duplas");
		controllerInscricao = new InicialInscricaoController(usuario, torneio, this);
		btnInscricao.addActionListener(controllerInscricao);
		btnInscricao.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnInscricao.setBounds(359, 112, 207, 23);
		getContentPane().add(btnInscricao);
		return btnInscricao;
	}

	private JButton configBtnEncerrar() {
		btnEncerrar = new JButton("Encerrar Inscrições");
		controllerEncerrar = new EncerrarController(usuario, torneio, this);
		btnEncerrar.addActionListener(controllerEncerrar);
		btnEncerrar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnEncerrar.setBounds(359, 147, 207, 23);
		getContentPane().add(btnEncerrar);
		return btnEncerrar;
	}

	private JButton configBtnGradeJogos(Usuario user, Torneio torneio) throws ParseException {
		btnGradeDeJogos = new JButton("Grade de Jogos");
		controllerGrade = new GradeJogosController(usuario, torneio, this);
		btnGradeDeJogos.addActionListener(controllerGrade);
		btnGradeDeJogos.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnGradeDeJogos.setBounds(359, 182, 207, 23);
		getContentPane().add(btnGradeDeJogos);
		return btnGradeDeJogos;
	}

	private JButton configBtnVoltar(Usuario usuario) {
		RetornarInicial inicial = new RetornarInicial(usuario, this);
		btnSair = new JButton("Voltar");
		btnSair.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSair.setBounds(359, 217, 207, 23);
		btnSair.addActionListener(inicial);
		return btnSair;
	}

	public JButton getBtnInscricao() {
		return btnInscricao;
	}

	public void setBtnInscricao(JButton btnInscricao) {
		this.btnInscricao = btnInscricao;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
		Usuario g = new Usuario();
		g.setNome("Teste");
		Torneio torneio = null;
		TorneioResource tr = new TorneioResource();
		for (Torneio t : tr.listaTorneios()) {
			if (t.getId() == 69) {
				torneio = t;
			}
		}
		GerenciarTorneio frame = new GerenciarTorneio(g, torneio);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);

	}
}
