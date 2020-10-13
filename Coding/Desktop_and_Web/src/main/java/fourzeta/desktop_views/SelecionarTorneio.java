package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import fourzeta.controllers.desktop.CadastrarCircuitoController;
import fourzeta.controllers.desktop.SelecionarTorneioController;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;

public class SelecionarTorneio extends JFrame {

	private final Dimension SIZE = new Dimension(600, 355);
	private SelecionarTorneioController controller;
	private JButton btnSelecionar;
	private JComboBox comboCircuito;
	private JComboBox comboTorneio;
	private JLabel lblIcon;
	private CadastrarCircuitoController ctrlCircuito;
	private JButton btnNovoCircuto;
	private JButton btnNovoTorneio;
	private JButton btnEditarCircuito;
	private JButton btnEditarTorneio;
	private JButton btnExcluirTorneio;
	private JButton btnExcluirCircuito;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JLabel lblselecioneOCircuito;
	private JButton btnVoltar;
	private JLabel lblTorneio;
	private JLabel lblCircuito;
	private JLabel titulo;
	private ImageIcon imgLogin;
	
	public SelecionarTorneio(Usuario usuario) throws ParseException, IOException, NotBoundException {
		controller = new SelecionarTorneioController(usuario, this);
		ctrlCircuito = new CadastrarCircuitoController();
		configFrame();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		configImgLogin();
		configTitulo();
		configBtnSelecionar();
		configBtnVoltar();
		configCircuito();
		configTorneio();
		configScroolPane();
		configScroolPane_1();
		configScroolPane_2();
	}

	private void configTorneio() {
		configComboTorneio();
		configLblTorneio();
		configBtnNovoTorneio();
		configBtnEditarTorneio();
		configBtnExcluirTorneio();
	}

	private void configCircuito() {
		configComboCircuito();
		configLblSelecioneOCircuito();
		configLblCircuito();
		configBtnNovoCircuito();
		configBtnEditarCircuito();
		configBtnExcluirCircuito();
	}

	private void configBtnExcluirTorneio() {
		btnExcluirTorneio = new JButton("Excluir Torneio");
		btnExcluirTorneio.setName("btnExcluirTorneio");
		btnExcluirTorneio.addActionListener(controller);
		btnExcluirTorneio.setBounds(221, 198, 138, 25);
		getContentPane().add(btnExcluirTorneio);
	}

	private void configBtnEditarTorneio() {
		btnEditarTorneio = new JButton("Editar Torneio");
		btnEditarTorneio.setName("btnEditarTorneio");
		btnEditarTorneio.addActionListener(controller);
		btnEditarTorneio.setBounds(221, 149, 138, 25);
		getContentPane().add(btnEditarTorneio);
	}

	private void configBtnExcluirCircuito() {
		btnExcluirCircuito = new JButton("Excluir Circuito");
		btnExcluirCircuito.setName("btnExcluirCircuito");
		btnExcluirCircuito.addActionListener(controller);
		btnExcluirCircuito.setBounds(26, 198, 138, 25);
		getContentPane().add(btnExcluirCircuito);
	}

	private void configBtnEditarCircuito() {
		btnEditarCircuito = new JButton("Editar Circuito");
		btnEditarCircuito.setName("btnEditarCircuito");
		btnEditarCircuito.addActionListener(controller);
		btnEditarCircuito.setBounds(26, 149, 138, 25);
		getContentPane().add(btnEditarCircuito);
	}

	private void configBtnNovoTorneio() {
		btnNovoTorneio = new JButton("Novo Torneio");
		btnNovoTorneio.setName("btnNovoTorneio");
		btnNovoTorneio.addActionListener(controller);
		btnNovoTorneio.setBounds(221, 98, 138, 25);
		getContentPane().add(btnNovoTorneio);
	}

	private void configBtnNovoCircuito() {
		btnNovoCircuto = new JButton("Novo Circuito");
		btnNovoCircuto.setName("btnNovoCircuito");
		btnNovoCircuto.addActionListener(controller);
		btnNovoCircuto.setBounds(26, 98, 138, 25);
		getContentPane().add(btnNovoCircuto);
	}

	private void configLblSelecioneOCircuito() {
		lblselecioneOCircuito = new JLabel("*Selecione o Circuito e o Torneio que deseja gerenciar.");
		lblselecioneOCircuito.setBounds(12, 269, 494, 58);
		getContentPane().add(lblselecioneOCircuito);
	}

	private void configBtnVoltar() {
		btnVoltar = new JButton("Voltar");
		btnVoltar.setName("btnVoltar");
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVoltar.addActionListener(controller);
		btnVoltar.setBounds(480, 286, 89, 23);
		getContentPane().add(btnVoltar);
	}

	private void configLblTorneio() {
		lblTorneio = new JLabel("Torneio");
		lblTorneio.setBounds(404, 133, 66, 15);
		getContentPane().add(lblTorneio);
	}

	private void configLblCircuito() {
		lblCircuito = new JLabel("Circuito");
		lblCircuito.setBounds(404, 82, 66, 15);
		getContentPane().add(lblCircuito);
	}

	private void configBtnSelecionar() {
		btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setName("btnSelecionar");
		btnSelecionar.addActionListener(controller);
		btnSelecionar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSelecionar.setBounds(404, 198, 165, 23);
		getContentPane().add(btnSelecionar);
	}

	private void configComboTorneio() {
		comboTorneio = new JComboBox();
		comboTorneio.setBounds(404, 150, 165, 22);
		comboTorneio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		getContentPane().add(comboTorneio);
	}

	private void configComboCircuito() {
		comboCircuito = new JComboBox();
		comboCircuito.setBounds(404, 99, 165, 22);
		comboCircuito.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboCircuito.addItem("Selecionar");
		for (Circuito circuito : ctrlCircuito.listarCircuitos()) {
			comboCircuito.addItem(circuito.getNome());
		}
		comboCircuito.addActionListener(controller);
		getContentPane().add(comboCircuito);
	}

	private void configScroolPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 79, 148, 167);
		getContentPane().add(scrollPane);
	}

	private void configScroolPane_1() {
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(217, 79, 148, 167);
		getContentPane().add(scrollPane_1);
	}

	private void configScroolPane_2() {
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(400, 79, 175, 167);
		getContentPane().add(scrollPane_2);
	}

	private void configTitulo() {
		titulo = new JLabel("Gerenciar");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 12, 600, 58);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 45));
		getContentPane().add(titulo);
	}

	private void configImgLogin() {
		imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);
	}

	private void configFrame() {
		setTitle("Sistema de Gerenciamento de Padel");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	}

	public void notifyEncerrarInscricaoCircuito() {
		JOptionPane.showMessageDialog(this, "Selecione um Circuito para encerrar inscrições.");

	}

	public void notifyEncerrarInscricaoTorneio() {
		JOptionPane.showMessageDialog(this, "Selecione um Torneio para encerrar inscrições.");

	}

	public void notifyEncerramentoSucesso() {
		JOptionPane.showMessageDialog(this, "Inscrições encerradas com sucesso!!");

	}

	public JComboBox getComboCircuito() {
		return comboCircuito;
	}

	public void setComboCircuito(JComboBox comboCircuito) {
		this.comboCircuito = comboCircuito;
	}

	public JComboBox getComboTorneio() {
		return comboTorneio;
	}

	public void setComboTorneio(JComboBox comboTorneio) {
		this.comboTorneio = comboTorneio;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

	public static void main(String[] args) throws IOException, ParseException, NotBoundException {

		SelecionarTorneio frame = new SelecionarTorneio(null);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);

	}
}
