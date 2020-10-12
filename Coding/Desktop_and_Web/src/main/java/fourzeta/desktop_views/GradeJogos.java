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
	private final String FONTE = "Times New Roman";
	private JLabel lblTitulo;
	private JButton btnVoltar;
	private JButton btnPesquisar;
	private JTable jogosTable;
	private GradeJogosTableModel model;
	private PesquisarJogosController controller;
	private JComboBox comboCategoria;
	private JComboBox comboQuadra;
	private JScrollPane sp;
	private ImageIcon imgLogin;
	private JLabel lblIcon;
	private JLabel lblQuadras;
	private JLabel lblselecione;

	public GradeJogos(Usuario usuario, Torneio torneio) throws ParseException {		
		this.controller = new PesquisarJogosController(usuario, torneio, this);
		this.getContentPane().add(configIcon());		
		this.configFrame();
		this.getContentPane().add(configTblJogos(torneio));
		this.getContentPane().add(configLblTitulo());
		this.getContentPane().add(configBtnVoltar(usuario, torneio));
		this.getContentPane().add(configComboQuadra());
		this.getContentPane().add(configBtnPesquisar());
		this.getContentPane().add(configLblQuadras());
		this.getContentPane().add(configLblSelecione());
	}
	
	private void configFrame() {
		setTitle("Sistema de Gerenciamento de Padel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
	}
	
	private JLabel configIcon() {
		this.imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(this.imgLogin.getImage());
		this.lblIcon = new JLabel(this.imgLogin);
		return this.lblIcon;
	}
	
	private JLabel configLblTitulo() {
		this.lblTitulo = new JLabel("Grade de Jogos");
		this.lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTitulo.setBounds(0, 38, 1000, 58);
		this.lblTitulo.setFont(new Font(this.FONTE, Font.BOLD, 45));
		return this.lblTitulo;
	}
	
	private JScrollPane configTblJogos(Torneio torneio) {
		this.model = new GradeJogosTableModel(torneio);
		this.jogosTable = new JTable(model);
		this.jogosTable.setBounds(53, 100, 402, 150);
		this.sp = new JScrollPane(jogosTable);
		this.sp.setLocation(36, 140);
		this.sp.setBounds(47, 212, 900, 420);
		return this.sp;
	}
	
	private JButton configBtnVoltar(Usuario usuario, Torneio torneio) {
		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.setName("btnVoltar");
		this.btnVoltar.addActionListener(this.controller);
		this.btnVoltar.setFont(new Font(this.FONTE, Font.BOLD, 16));
		this.btnVoltar.setBounds(47, 644, 106, 23);
		return this.btnVoltar;
	}
	
	private JComboBox configComboQuadra() {
		this.comboQuadra = new JComboBox();
		this.comboQuadra.setFont(new Font(this.FONTE, Font.PLAIN, 14));
		this.comboQuadra.addItem("Selecionar");
		this.comboQuadra.addItem("LARANJA");
		this.comboQuadra.addItem("AZUL");
		this.comboQuadra.addItem("VERDE");
		this.comboQuadra.setBounds(138, 147, 124, 22);
		return this.comboQuadra;
	}
	
	private JButton configBtnPesquisar() {
		this.btnPesquisar = new JButton("Pesquisar");
		this.btnPesquisar.setName("btnPesquisar");
		this.btnPesquisar.addActionListener(this.controller);
		this.btnPesquisar.setFont(new Font(this.FONTE, Font.BOLD, 16));
		this.btnPesquisar.setBounds(274, 146, 157, 23);
		return this.btnPesquisar;
	}
	
	private JLabel configLblQuadras() {
		this.lblQuadras = new JLabel("Quadra:");
		this.lblQuadras.setFont(new Font(this.FONTE, Font.BOLD, 16));
		this.lblQuadras.setBounds(47, 148, 132, 18);
		return this.lblQuadras;
	}
	
	private JLabel configLblSelecione() {
		this.lblselecione = new JLabel("*Selecione a Quadra que deseja visualizar os Jogos.");
		this.lblselecione.setBounds(47, 172, 494, 58);
		return this.lblselecione;
	}
	
	public void notifySelecioneQuadra() {
		JOptionPane.showMessageDialog(this, "Por favor, seleciona uma Quadra.");

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

	public void readTable(List<Jogo> jogos) {
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
