package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import fourzeta.controllers.desktop.EncerrarController;
import fourzeta.controllers.desktop.GradeJogosController;
import fourzeta.controllers.desktop.InicialInscricaoController;
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
	private JLabel lblImage;
	private JButton btnSair;
	private JLabel lblNomeUsuario;
	private JButton btnGradeDeJogos;
	private JLabel lblMenu;
	private Usuario usuario;
	private Torneio torneio;

	public GerenciarTorneio(Usuario usuario, Torneio torneio) throws ParseException, IOException {
		this.usuario = usuario;
		this.torneio = torneio;
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

		btnInscricao = new JButton("Inscrever Duplas");
		controllerInscricao = new InicialInscricaoController(usuario, torneio, this);
		btnInscricao.addActionListener(controllerInscricao);
		btnInscricao.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnInscricao.setBounds(359, 112, 207, 23);
		getContentPane().add(btnInscricao);

		btnEncerrar = new JButton("Encerrar Inscrições");
		controllerEncerrar = new EncerrarController(usuario, torneio, this);
		btnEncerrar.addActionListener(controllerEncerrar);
		btnEncerrar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnEncerrar.setBounds(359, 147, 207, 23);
		getContentPane().add(btnEncerrar);

		lblMenu = new JLabel("MENU");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 25));
		lblMenu.setBounds(359, 43, 207, 30);
		getContentPane().add(lblMenu);
		
		btnGradeDeJogos = new JButton("Grade de Jogos");
		controllerGrade = new GradeJogosController(usuario, torneio, this);
		btnGradeDeJogos.addActionListener(controllerGrade);
		btnGradeDeJogos.setFont(new Font("Dialog", Font.BOLD, 16));
		btnGradeDeJogos.setBounds(359, 182, 207, 23);
		getContentPane().add(btnGradeDeJogos);

		btnSair = new JButton("Voltar");
		btnSair.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSair.setBounds(359, 217, 207, 23);

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Inicial tela = new Inicial(usuario);
					setVisible(false);
					tela.setVisible(true);
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(btnSair);

		lblNomeUsuario = new JLabel("Usuario: " + usuario.getNome());
		lblNomeUsuario.setBounds(36, 280, 247, 30);
		getContentPane().add(lblNomeUsuario);

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

	public void notifyEncerramentoSucesso() {
		JOptionPane.showMessageDialog(this, "Inscrições encerradas com sucesso!!");

	}
	
	public void notifyInscricoesJaEncerradas() {
		JOptionPane.showMessageDialog(this, "As Inscrições já foram encerradas.");

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
