package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import fourzeta.controllers.desktop.PesquisarJogosController;
import fourzeta.models.Jogo;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.table.GradeJogosTableModel;

public class GradeJogos extends JFrame {

	private static final Dimension SIZE = new Dimension(1000, 720);
	private JLabel titulo;
	private JButton btnVoltar;
	private Torneio torneio;
	private JTable jogosTable;
	private GradeJogosTableModel model;
	private PesquisarJogosController controller;
	private JComboBox comboCategoria;
	private JComboBox comboQuadra;
	private JScrollPane sp;
	private JLabel lblIcon;

	public GradeJogos(Usuario usuario, Torneio torneio) throws ParseException {
		setTitle("Sistema de Gerenciamento de Padel");
		this.torneio = torneio;
		// mudando o icon
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);
		this.torneio = torneio;
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().setLayout(null);
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		model = new GradeJogosTableModel(torneio);
		jogosTable = new JTable(model);
		jogosTable.setBounds(53, 100, 402, 150);
		sp = new JScrollPane(jogosTable);
		sp.setLocation(36, 140);
		sp.setBounds(47, 212, 900, 420);
		getContentPane().add(sp);

		titulo = new JLabel("Grade de Jogos");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 38, 1000, 58);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 45));
		getContentPane().add(titulo);


		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() { // Implementando Voltar

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GerenciarTorneio tela = null;
				try {
					tela = new GerenciarTorneio(usuario, torneio);
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
				setVisible(false);
				tela.setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVoltar.setBounds(47, 644, 106, 23);
		getContentPane().add(btnVoltar);

		comboQuadra = new JComboBox();
		comboQuadra.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboQuadra.addItem("Selecionar");
		comboQuadra.addItem("LARANJA");
		comboQuadra.addItem("AZUL");
		comboQuadra.addItem("VERDE");

		comboQuadra.setBounds(138, 147, 124, 22);
		getContentPane().add(comboQuadra);
		

		JButton btnPesquisar = new JButton("Pesquisar");
		controller = new PesquisarJogosController(usuario, torneio, this);
		btnPesquisar.addActionListener(controller);
		btnPesquisar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnPesquisar.setBounds(274, 146, 157, 23);
		getContentPane().add(btnPesquisar);


		JLabel lblQuadras = new JLabel("Quadra:");
		lblQuadras.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblQuadras.setBounds(47, 148, 132, 18);
		getContentPane().add(lblQuadras);

		JLabel lblselecione = new JLabel("*Selecione a Quadra que deseja visualizar os Jogos.");
		lblselecione.setBounds(47, 172, 494, 58);
		getContentPane().add(lblselecione);
	}
	
	public void notifySelecioneQuadra() {
		JOptionPane.showMessageDialog(this, "Por favor, seleciona uma Quadra.");

	}

	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	public JTable getJogosTable() {
		return jogosTable;
	}

	public void setJogosTable(JTable jogosTable) {
		this.jogosTable = jogosTable;
	}

	public JButton getBtnInscricao() {
		return btnVoltar;
	}

	public void setBtnInscricao(JButton btnInscricao) {
		this.btnVoltar = btnInscricao;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

	public JComboBox getComboCategoria() {
		return comboCategoria;
	}

	public void setComboCategoria(JComboBox comboCategoria) {
		this.comboCategoria = comboCategoria;
	}

	public JComboBox getComboQuadra() {
		return comboQuadra;
	}

	public void setComboQuadra(JComboBox comboQuadra) {
		this.comboQuadra = comboQuadra;
	}

	public void readTable(List<Jogo> jogos) { // Método para ler FINDALL

		jogosTable.getModel();

		for (Jogo jogo : jogos) {
			model.addRow(jogo);

		}
	}
	
	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
		Usuario g = new Usuario();
		Torneio t = new Torneio();
		g.setNome("Teste");

		GradeJogos frame = new GradeJogos(g, t);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
