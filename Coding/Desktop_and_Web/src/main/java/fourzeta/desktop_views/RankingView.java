package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
import fourzeta.controllers.desktop.PesquisarRankingController;
import fourzeta.models.Categoria;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.models.Ranking;
import fourzeta.resources.CircuitoResource;
import fourzeta.table.RankingTableModel;

public class RankingView extends JFrame {

	private static final Dimension SIZE = new Dimension(800, 550);
	private final String FONTE = "Times New Roman";
	private ImageIcon imgLogin;
	private JLabel lblTitulo;
	private JLabel lblCircuito;
	private JLabel lblCategoria;
	private PesquisarRankingController pesquisarController;
	private JButton btnVoltar;
	private JButton btnPesquisar;
	private JTable rankingsTable;
	private RankingTableModel model;
	private JScrollPane sp;
	private JComboBox comboCircuito;
	private JComboBox comboCategoria;
	private JLabel lblIcon;

	public RankingView(Usuario usuario)	throws ParseException, RemoteException, MalformedURLException, NotBoundException {
		this.pesquisarController = new PesquisarRankingController(usuario, this);
		this.configFrame();
		this.getContentPane().add(configTblRanking());
		this.getContentPane().add(configIcon());
		this.getContentPane().add(configLblTitulo());
		this.getContentPane().add(configBtnVoltar(usuario));
		this.getContentPane().add(configComboCategoria());
		this.getContentPane().add(configComboCircuito());
		this.getContentPane().add(configLblCircuito());
		this.getContentPane().add(configLblCategoria());
		this.getContentPane().add(configBtnPesquisar());
	}
	
	private void configFrame() {
		this.getContentPane().setLayout(null);
		this.setTitle("Sistema de Gerenciamento de Padel");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
	}
	
	private JScrollPane configTblRanking() {
		this.model = new RankingTableModel();
		this.rankingsTable = new JTable(model);
		this.rankingsTable.setBounds(53, 100, 402, 150);
		this.sp = new JScrollPane(rankingsTable);
		this.sp.setLocation(36, 140);
		this.sp.setBounds(56, 83, 688, 318);
		return this.sp;
	}
	
	private JLabel configIcon() {
		this.imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(this.imgLogin.getImage());
		this.lblIcon = new JLabel(this.imgLogin);
		return this.lblIcon;
	}
	
	private JLabel configLblTitulo() {
		this.lblTitulo = new JLabel("Ranking");
		this.lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTitulo.setBounds(0, 11, 784, 58);
		this.lblTitulo.setFont(new Font(this.FONTE, Font.BOLD, 45));
		return this.lblTitulo;
	}
	
	private JButton configBtnVoltar(Usuario usuario) {
		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.setName("btnVoltar");
		this.btnVoltar.addActionListener(this.pesquisarController);
		this.btnVoltar.setFont(new Font(this.FONTE, Font.BOLD, 16));
		this.btnVoltar.setBounds(56, 429, 106, 23);
		return this.btnVoltar;
	}
	
	private JComboBox configComboCategoria() {
		this.comboCategoria = new JComboBox();
		this.comboCategoria.setFont(new Font(this.FONTE, Font.PLAIN, 14));
		this.comboCategoria.addItem("Selecionar");
		Categoria[] catEnums = Categoria.values();
		for(int i = 0; i < catEnums.length; i++) {
			this.comboCategoria.addItem(catEnums[i]);
		}
		this.comboCategoria.setBounds(490, 429, 124, 22);
		return this.comboCategoria;
	}
	
	private JComboBox configComboCircuito() {
		CircuitoResource cr = new CircuitoResource();
		this.comboCircuito = new JComboBox();
		this.comboCircuito.setBounds(315, 429, 165, 22);
		this.comboCircuito.setFont(new Font(this.FONTE, Font.PLAIN, 14));
		this.comboCircuito.addItem("Selecionar");
		for (Circuito circuito : cr.listaCircuitos()) {
			this.comboCircuito.addItem(circuito.getNome());
		}
		return this.comboCircuito;
	}
	
	private JLabel configLblCircuito() {
		this.lblCircuito = new JLabel("Circuito");
		this.lblCircuito.setFont(new Font(this.FONTE, Font.BOLD, 16));
		this.lblCircuito.setBounds(315, 412, 144, 15);
		return this.lblCircuito;
	}
	
	private JLabel configLblCategoria() {
		this.lblCategoria = new JLabel("Categoria");
		this.lblCategoria.setFont(new Font(this.FONTE, Font.BOLD, 16));
		this.lblCategoria.setBounds(490, 410, 132, 18);
		return this.lblCategoria;
	}
	
	private JButton configBtnPesquisar() {
		this.btnPesquisar = new JButton("Pesquisar");
		this.btnPesquisar.setName("btnPesquisar");
		this.btnPesquisar.addActionListener(this.pesquisarController);
		this.btnPesquisar.setFont(new Font(this.FONTE, Font.BOLD, 16));
		this.btnPesquisar.setBounds(624, 430, 106, 23);
		return this.btnPesquisar;
	}	

	public void notifyCriacaoSucesso() {
		JOptionPane.showMessageDialog(this, ":)");
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

	public JComboBox getComboCircuito() {
		return comboCircuito;
	}

	public void setComboCircuito(JComboBox comboCircuito) {
		this.comboCircuito = comboCircuito;
	}

	public JComboBox getComboCategoria() {
		return comboCategoria;
	}

	public void setComboCategoria(JComboBox comboCategoria) {
		this.comboCategoria = comboCategoria;
	}

	public JTable getRankingsTable() {
		return rankingsTable;
	}

	public void setRankingsTable(JTable rankingsTable) {
		this.rankingsTable = rankingsTable;
	}

	public void readTable(List<Ranking> rankings) { // Método para ler FINDALL
		RankingTableModel rankingsModel = new RankingTableModel();
		this.rankingsTable.setModel(rankingsModel);

		for (Ranking ranking : rankings) {
			rankingsModel.addRow(ranking);
		}

	}
	
	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
		Usuario g = new Usuario();
		g.setNome("Teste");
		RankingView frame = new RankingView(g);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
