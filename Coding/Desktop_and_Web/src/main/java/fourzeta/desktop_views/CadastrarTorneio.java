package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import fourzeta.controllers.desktop.CadastrarCircuitoController;
import fourzeta.controllers.desktop.CadastrarTorneioController;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;

public class CadastrarTorneio extends JFrame {

	private final Dimension SIZE = new Dimension(350, 600);
	private JTextField textNomeTorneio;
	private JLabel lblCadastrar;
	private JLabel lblNome;
	private JLabel lblDescrio;
	private JButton btnVoltar;
	CadastrarTorneioController cadTorneio;
	private CadastrarCircuitoController ctrlCircuito;
	private JButton btnCadastrar;
	private JLabel lblInicio;
	private JLabel lblFim;
	private JTextField textDataInicio;
	private JTextField textDataFim;
	private JComboBox comboBoxCircuito;
	private TextField textDescricaoTorneio;
	private JLabel lblDistribirJogos;
	private JLabel lblCircuito;
	private JComboBox comboBoxQuadra1;
	private JComboBox comboBoxQuadra2;
	private JComboBox comboBoxQuadra3;
	private JComboBox comboBoxQuadra4;
	private JComboBox comboBoxQuadra5;
	private JComboBox comboBoxQuadra6;
	private MaskFormatter formatoData = new MaskFormatter("##/##/####");
	private JLabel lblQuadra;
	private JLabel lblCategoria_1;
	private JLabel lblCategoria_2;
	private JLabel lblCategoria_3;
	private JLabel lblCategoria_4;
	private JLabel lblCategoria_5;
	private JLabel lblselecioneACategoria;
	private JLabel lblIniciantes;
	private final String SELECIONAR_QUADRA = "SELECIONAR";
	private final String QUADRA_1 = "LARANJA";
	private final String QUADRA_2 = "AZUL";
	private final String QUADRA_3 = "VERDE";

	public CadastrarTorneio(Usuario usuario) throws ParseException, IOException, NotBoundException {
		cadTorneio = new CadastrarTorneioController(usuario, this);
		ctrlCircuito = new CadastrarCircuitoController();
		configFrame();
		connfigLblCadastrar();
		configLbl();
		configTxt();
		configComboBox();
	}

	private void configComboBox() {
		configComboBoxCircuito();
		configComboBoxQuadra1();
		configComboBoxQuadra2();
		configComboBoxQuadra3();
		configComboBoxQuadra4();
		configComboBoxQuadra5();
		configComboBoxQuadra6();
	}

	private void configTxt() {
		configTxtDataInicio();
		configTxtDataFim();
		configTxtNomeTorneio();
		configTxtDescricaoTorneio();
	}

	private void configLbl() {
		configLblNome();
		configLblFim();
		configLblCircuito();
		configLbldescricao();
		configBtnVoltar();
		configBtnCadastrar();
		configlblInicio();
		configLblDistribuirJogos();
		configLblSelecioneACategoria();
		configLblCategoria_1();
		configLblQuadra();
		configLblCategoria_2();
		configLblCategoria_3();
		configLblCategoria_4();
		configLblCategoria_5();
		configLblIniciantes();
	}

	private void configComboBoxCircuito() {
		comboBoxCircuito = new JComboBox();
		comboBoxCircuito.addItem("Selecionar");
		for (Circuito circuito : ctrlCircuito.listarCircuitos()) {
			comboBoxCircuito.addItem(circuito.getNome());
		}
		comboBoxCircuito.setBounds(22, 195, 300, 24);
		getContentPane().add(comboBoxCircuito);
	}

	private void configLblSelecioneACategoria() {
		lblselecioneACategoria = new JLabel("*Distribuir jogos por categoria na quadra desejada");
		lblselecioneACategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblselecioneACategoria.setBounds(22, 503, 311, 43);
		getContentPane().add(lblselecioneACategoria);
	}

	private void configLblQuadra() {
		lblQuadra = new JLabel("QUADRA");
		lblQuadra.setFont(new Font("Dialog", Font.BOLD, 14));
		lblQuadra.setBounds(195, 328, 114, 15);
		getContentPane().add(lblQuadra);
	}

	private void configLblCategoria_1() {
		lblCategoria_1 = new JLabel("1ª Categoria");
		lblCategoria_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_1.setBounds(56, 349, 100, 15);
		getContentPane().add(lblCategoria_1);
	}

	private void configLblCategoria_2() {
		lblCategoria_2 = new JLabel("2ª Categoria");
		lblCategoria_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_2.setBounds(56, 377, 100, 15);
		getContentPane().add(lblCategoria_2);
	}

	private void configLblCategoria_3() {
		lblCategoria_3 = new JLabel("3ª Categoria");
		lblCategoria_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_3.setBounds(56, 404, 100, 15);
		getContentPane().add(lblCategoria_3);
	}

	private void configLblCategoria_4() {
		lblCategoria_4 = new JLabel("4ª Categoria");
		lblCategoria_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_4.setBounds(56, 431, 100, 15);
		getContentPane().add(lblCategoria_4);
	}

	private void configLblCategoria_5() {
		lblCategoria_5 = new JLabel("5ª Categoria");
		lblCategoria_5.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_5.setBounds(56, 457, 100, 15);
		getContentPane().add(lblCategoria_5);
	}

	private void configLblIniciantes() {
		lblIniciantes = new JLabel("Iniciantes");
		lblIniciantes.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIniciantes.setBounds(66, 482, 100, 15);
		getContentPane().add(lblIniciantes);
	}

	private void configComboBoxQuadra6() {
		comboBoxQuadra6 = new JComboBox();
		comboBoxQuadra6.addItem(SELECIONAR_QUADRA);
		comboBoxQuadra6.addItem(QUADRA_1);
		comboBoxQuadra6.addItem(QUADRA_2);
		comboBoxQuadra6.addItem(QUADRA_3);
		comboBoxQuadra6.setEnabled(false);
		comboBoxQuadra6.setBounds(174, 477, 114, 24);
		getContentPane().add(comboBoxQuadra6);
	}

	private void configComboBoxQuadra5() {
		comboBoxQuadra5 = new JComboBox();
		comboBoxQuadra5.addItem(SELECIONAR_QUADRA);
		comboBoxQuadra5.addItem(QUADRA_1);
		comboBoxQuadra5.addItem(QUADRA_2);
		comboBoxQuadra5.addItem(QUADRA_3);
		comboBoxQuadra5.setEnabled(false);
		comboBoxQuadra5.setBounds(174, 452, 114, 24);
		getContentPane().add(comboBoxQuadra5);
	}

	private void configComboBoxQuadra4() {
		comboBoxQuadra4 = new JComboBox();
		comboBoxQuadra4.addItem(SELECIONAR_QUADRA);
		comboBoxQuadra4.addItem(QUADRA_1);
		comboBoxQuadra4.addItem(QUADRA_2);
		comboBoxQuadra4.addItem(QUADRA_3);
		comboBoxQuadra4.setEnabled(false);
		comboBoxQuadra4.setBounds(174, 426, 114, 24);
		getContentPane().add(comboBoxQuadra4);
	}

	private void configComboBoxQuadra3() {
		comboBoxQuadra3 = new JComboBox();
		comboBoxQuadra3.addItem(SELECIONAR_QUADRA);
		comboBoxQuadra3.addItem(QUADRA_1);
		comboBoxQuadra3.addItem(QUADRA_2);
		comboBoxQuadra3.addItem(QUADRA_3);
		comboBoxQuadra3.setEnabled(false);
		comboBoxQuadra3.setBounds(174, 399, 114, 24);
		getContentPane().add(comboBoxQuadra3);
	}

	private void configComboBoxQuadra2() {
		comboBoxQuadra2 = new JComboBox();
		comboBoxQuadra2.addItem(SELECIONAR_QUADRA);
		comboBoxQuadra2.addItem(QUADRA_1);
		comboBoxQuadra2.addItem(QUADRA_2);
		comboBoxQuadra2.addItem(QUADRA_3);
		comboBoxQuadra2.setEnabled(false);
		comboBoxQuadra2.setBounds(174, 372, 114, 24);
		getContentPane().add(comboBoxQuadra2);
	}

	private void configComboBoxQuadra1() {
		comboBoxQuadra1 = new JComboBox();
		comboBoxQuadra1.addItem(SELECIONAR_QUADRA);
		comboBoxQuadra1.addItem(QUADRA_1);
		comboBoxQuadra1.addItem(QUADRA_2);
		comboBoxQuadra1.addItem(QUADRA_3);
		comboBoxQuadra1.setEnabled(false);
		comboBoxQuadra1.setBounds(174, 344, 114, 24);
		getContentPane().add(comboBoxQuadra1);
	}

	private void configLblDistribuirJogos() {
		lblDistribirJogos = new JLabel("Distribuir Jogos");
		lblDistribirJogos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistribirJogos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDistribirJogos.setBounds(12, 282, 321, 35);
		getContentPane().add(lblDistribirJogos);
	}

	private void configTxtDescricaoTorneio() {
		textDescricaoTorneio = new TextField();
		textDescricaoTorneio.setBounds(22, 124, 300, 40);
		getContentPane().add(textDescricaoTorneio);
	}

	private void configLblCircuito() {
		lblCircuito = new JLabel("Circuito:");
		lblCircuito.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCircuito.setBounds(22, 175, 70, 15);
		getContentPane().add(lblCircuito);
	}

	private void configLblFim() {
		lblFim = new JLabel("Fim:");
		lblFim.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFim.setBounds(208, 227, 66, 15);
		getContentPane().add(lblFim);
	}

	private void configTxtDataFim() {
		textDataFim = new JFormattedTextField(formatoData);
		textDataFim.setColumns(10);
		textDataFim.setBounds(208, 251, 114, 19);
		getContentPane().add(textDataFim);
	}

	private void configTxtDataInicio() {
		textDataInicio = new JFormattedTextField(formatoData);
		textDataInicio.setColumns(10);
		textDataInicio.setBounds(22, 251, 114, 19);
		getContentPane().add(textDataInicio);
	}

	private void configlblInicio() {
		lblInicio = new JLabel("Início:");
		lblInicio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInicio.setBounds(22, 227, 66, 15);
		getContentPane().add(lblInicio);
	}

	private void configBtnCadastrar() {
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(208, 538, 114, 25);
		btnCadastrar.addActionListener(this.cadTorneio);
		getContentPane().add(btnCadastrar);
	}

	private void configBtnVoltar() {
		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.setName("btnVoltar");
		this.btnVoltar.addActionListener(this.cadTorneio);
		this.btnVoltar.setBounds(22, 538, 114, 25);
		getContentPane().add(btnVoltar);
	}

	private void configLbldescricao() {
		lblDescrio = new JLabel("Descrição:");
		lblDescrio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDescrio.setBounds(22, 104, 90, 15);
		getContentPane().add(lblDescrio);
	}

	private void configTxtNomeTorneio() {
		textNomeTorneio = new JTextField();
		textNomeTorneio.setBounds(22, 74, 300, 19);
		getContentPane().add(textNomeTorneio);
		textNomeTorneio.setColumns(10);
	}

	private void configLblNome() {
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNome.setBounds(22, 50, 66, 15);
		getContentPane().add(lblNome);
	}

	private void connfigLblCadastrar() {
		lblCadastrar = new JLabel("Cadastrar Torneio");
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setFont(new Font("Dialog", Font.BOLD, 30));
		lblCadastrar.setBounds(12, 12, 321, 26);
		getContentPane().add(lblCadastrar);
	}

	private void configFrame() {
		setTitle("Cadastrar Torneio");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	}

	public JComboBox getComboBoxCircuito() {
		return comboBoxCircuito;
	}

	public void setComboBoxCircuito(JComboBox comboBoxCircuito) {
		this.comboBoxCircuito = comboBoxCircuito;
	}

	public JTextField getTextNomeTorneio() {
		return textNomeTorneio;
	}

	public void setTextNomeTorneio(JTextField textNomeTorneio) {
		this.textNomeTorneio = textNomeTorneio;
	}

	public TextField getTextDescricaoTorneio() {
		return textDescricaoTorneio;
	}

	public void setTextDescricaoTorneio(TextField textDescricaoTorneio) {
		this.textDescricaoTorneio = textDescricaoTorneio;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	public void setBtnVoltar(JButton btnVoltar) {
		this.btnVoltar = btnVoltar;
	}

	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}

	public void setBtnCadastrar(JButton btnCadastrar) {
		this.btnCadastrar = btnCadastrar;
	}

	public JTextField getTextDataInicio() {
		return textDataInicio;
	}

	public void setTextDataInicio(JTextField textDataInicio) {
		this.textDataInicio = textDataInicio;
	}

	public JTextField getTextDataFim() {
		return textDataFim;
	}

	public void setTextDataFim(JTextField textDataFim) {
		this.textDataFim = textDataFim;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

	public void notifyCampoIncompleto() {
		JOptionPane.showMessageDialog(null, "Você não preencheu todos os Campos!");
	}

	public void notifyCadastroRealizado() {
		JOptionPane.showMessageDialog(null, "Torneio Cadastrado com Sucesso!");
	}

	public void notifyInformarQuadra() {
		JOptionPane.showMessageDialog(this, "Informe uma Quadra.");

	}

	public void notifyInformarCategoria() {
		JOptionPane.showMessageDialog(this, "Informe uma Categoria.");

	}

	public void notifySucesso() {
		JOptionPane.showMessageDialog(this, "Jogos distribuidos com Sucesso!!");

	}

	public JComboBox getComboBoxQuadra1() {
		return comboBoxQuadra1;
	}

	public void setComboBoxQuadra1(JComboBox comboBoxQuadra1) {
		this.comboBoxQuadra1 = comboBoxQuadra1;
	}

	public JComboBox getComboBoxQuadra2() {
		return comboBoxQuadra2;
	}

	public void setComboBoxQuadra2(JComboBox comboBoxQuadra2) {
		this.comboBoxQuadra2 = comboBoxQuadra2;
	}

	public JComboBox getComboBoxQuadra3() {
		return comboBoxQuadra3;
	}

	public void setComboBoxQuadra3(JComboBox comboBoxQuadra3) {
		this.comboBoxQuadra3 = comboBoxQuadra3;
	}

	public JComboBox getComboBoxQuadra4() {
		return comboBoxQuadra4;
	}

	public void setComboBoxQuadra4(JComboBox comboBoxQuadra4) {
		this.comboBoxQuadra4 = comboBoxQuadra4;
	}

	public JComboBox getComboBoxQuadra5() {
		return comboBoxQuadra5;
	}

	public void setComboBoxQuadra5(JComboBox comboBoxQuadra5) {
		this.comboBoxQuadra5 = comboBoxQuadra5;
	}

	public JComboBox getComboBoxQuadra6() {
		return comboBoxQuadra6;
	}

	public void setComboBoxQuadra6(JComboBox comboBoxQuadra6) {
		this.comboBoxQuadra6 = comboBoxQuadra6;
	}

	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
		Usuario g = new Usuario();
		g.setNome("Teste");

		CadastrarTorneio frame = new CadastrarTorneio(g);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}
}
