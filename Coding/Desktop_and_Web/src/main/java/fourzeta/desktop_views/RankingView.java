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
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.models.Ranking;
import fourzeta.resources.CircuitoResource;
import fourzeta.table.RankingTableModel;

public class RankingView extends JFrame {

	private static final Dimension SIZE = new Dimension(800, 550);
	private JLabel titulo;
	private PesquisarRankingController pesquisarController;
	private JButton btnVoltar;
	private JTable rankingsTable;
	private RankingTableModel model;
	private JScrollPane sp;
	private JComboBox comboCircuito;
	private JComboBox comboCategoria;
	private JLabel lblIcon;

	public RankingView(Usuario usuario)
			throws ParseException, RemoteException, MalformedURLException, NotBoundException {
		getContentPane().setLayout(null);
		setTitle("Sistema de Gerenciamento de Padel");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		model = new RankingTableModel();
		rankingsTable = new JTable(model);
		rankingsTable.setBounds(53, 100, 402, 150);
		sp = new JScrollPane(rankingsTable);
		sp.setLocation(36, 140);
		sp.setBounds(56, 83, 688, 318);
		getContentPane().add(sp);

		// mudando o icon
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);

		titulo = new JLabel("Ranking");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 784, 58);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 45));
		getContentPane().add(titulo);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() { // Implementando Voltar

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Inicial inicial = null;
				try {
					inicial = new Inicial(usuario);
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVoltar.setBounds(56, 429, 106, 23);
		getContentPane().add(btnVoltar);

		comboCategoria = new JComboBox();
		comboCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboCategoria.addItem("Selecionar");
		comboCategoria.addItem("PRIMEIRA");
		comboCategoria.addItem("SEGUNDA");
		comboCategoria.addItem("TERCEIRA");
		comboCategoria.addItem("QUARTA");
		comboCategoria.addItem("QUINTA");
		comboCategoria.addItem("INICIANTE");

		comboCategoria.setBounds(490, 429, 124, 22);
		getContentPane().add(comboCategoria);

		CircuitoResource cr = new CircuitoResource();
		comboCircuito = new JComboBox();
		comboCircuito.setBounds(315, 429, 165, 22);
		comboCircuito.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboCircuito.addItem("Selecionar");
		for (Circuito circuito : cr.listaCircuitos()) {

			comboCircuito.addItem(circuito.getNome());
		}

		getContentPane().add(comboCircuito);
		JLabel circuitoLbl = new JLabel("Circuito");
		circuitoLbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		circuitoLbl.setBounds(315, 412, 144, 15);
		getContentPane().add(circuitoLbl);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCategoria.setBounds(490, 410, 132, 18);
		getContentPane().add(lblCategoria);

		JButton btnPesquisar = new JButton("Pesquisar");
		pesquisarController = new PesquisarRankingController(this);
		btnPesquisar.addActionListener(pesquisarController);
		btnPesquisar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnPesquisar.setBounds(624, 430, 106, 23);
		getContentPane().add(btnPesquisar);

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
}
