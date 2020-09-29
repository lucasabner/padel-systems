package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JButton btnCadastrar;
	private JLabel lblInicio;
	private JLabel lblFim;
	private JTextField textDataInicio;
	private JTextField textDataFim;
	private JComboBox comboBoxCircuito;
	private TextField textDescricaoTorneio;
	private JLabel lblDistribirJogos;
	private JComboBox comboBoxQuadra1;
	private JComboBox comboBoxQuadra2;
	private JComboBox comboBoxQuadra3;
	private JComboBox comboBoxQuadra4;
	private JComboBox comboBoxQuadra5;
	private JComboBox comboBoxQuadra6;
	private MaskFormatter formatoData = new MaskFormatter("##/##/####");
	private JLabel lblQuadra;
	private JLabel lblCategoria;
	private JLabel lblCategoria_2;
	private JLabel lblCategoria_3;
	private JLabel lblCategoria_4;
	private JLabel lblIniciantes;

	public CadastrarTorneio(Usuario usuario) throws ParseException, IOException, NotBoundException {

		setTitle("Cadastrar Torneio");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblCadastrar = new JLabel("Cadastrar Torneio");
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setFont(new Font("Dialog", Font.BOLD, 30));
		lblCadastrar.setBounds(12, 12, 321, 26);
		getContentPane().add(lblCadastrar);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNome.setBounds(22, 50, 66, 15);
		getContentPane().add(lblNome);

		textNomeTorneio = new JTextField();
		textNomeTorneio.setBounds(22, 74, 300, 19);
		getContentPane().add(textNomeTorneio);
		textNomeTorneio.setColumns(10);

		lblDescrio = new JLabel("Descrição:");
		lblDescrio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDescrio.setBounds(22, 104, 90, 15);
		getContentPane().add(lblDescrio);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelecionarTorneio inicio = null;
				try {
					inicio = new SelecionarTorneio(usuario);
				} catch (ParseException | IOException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicio.setVisible(true);
			}
		});
		btnVoltar.setBounds(22, 538, 114, 25);
		getContentPane().add(btnVoltar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(208, 538, 114, 25);
		CadastrarTorneioController cadTorneio = new CadastrarTorneioController(usuario, this);
		btnCadastrar.addActionListener(cadTorneio);
		getContentPane().add(btnCadastrar);

		lblInicio = new JLabel("Início:");
		lblInicio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInicio.setBounds(22, 227, 66, 15);
		getContentPane().add(lblInicio);

		textDataInicio = new JFormattedTextField(formatoData);
		textDataInicio.setColumns(10);
		textDataInicio.setBounds(22, 251, 114, 19);
		getContentPane().add(textDataInicio);

		textDataFim = new JFormattedTextField(formatoData);
		textDataFim.setColumns(10);
		textDataFim.setBounds(208, 251, 114, 19);
		getContentPane().add(textDataFim);

		JLabel lblFim = new JLabel("Fim:");
		lblFim.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFim.setBounds(208, 227, 66, 15);
		getContentPane().add(lblFim);

		JLabel lblCircuito = new JLabel("Circuito:");
		lblCircuito.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCircuito.setBounds(22, 175, 70, 15);
		getContentPane().add(lblCircuito);

		CircuitoResource cr = new CircuitoResource();
		comboBoxCircuito = new JComboBox();
		comboBoxCircuito.addItem("Selecionar");
		for (Circuito circuito : cr.listaCircuitos()) {
			comboBoxCircuito.addItem(circuito.getNome());
		}
		comboBoxCircuito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Circuito circuito : cr.listaCircuitos()) {
					if (circuito.getNome().equalsIgnoreCase(comboBoxCircuito.getSelectedItem().toString())) {
						while (circuito.getNome().isEmpty()) {
							comboBoxCircuito.addItem(circuito.getNome());
						}
					}
				}
			}
		});
		getContentPane().add(comboBoxCircuito);
		comboBoxCircuito.setBounds(22, 195, 300, 24);
		getContentPane().add(comboBoxCircuito);

		textDescricaoTorneio = new TextField();
		textDescricaoTorneio.setBounds(22, 124, 300, 40);
		getContentPane().add(textDescricaoTorneio);
		
		lblDistribirJogos = new JLabel("Distribuir Jogos");
		lblDistribirJogos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistribirJogos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDistribirJogos.setBounds(12, 282, 321, 35);
		getContentPane().add(lblDistribirJogos);

		comboBoxQuadra1 = new JComboBox();
		comboBoxQuadra1.addItem("SELECIONAR");	
		comboBoxQuadra1.addItem("LARANJA");
		comboBoxQuadra1.addItem("AZUL");
		comboBoxQuadra1.addItem("VERDE");

		comboBoxQuadra1.setBounds(174, 344, 114, 24);
	
		getContentPane().add(comboBoxQuadra1);
		
		comboBoxQuadra2 = new JComboBox();
		comboBoxQuadra2.addItem("SELECIONAR");	
		comboBoxQuadra2.addItem("LARANJA");
		comboBoxQuadra2.addItem("AZUL");
		comboBoxQuadra2.addItem("VERDE");

		comboBoxQuadra2.setBounds(174, 372, 114, 24);
	
		getContentPane().add(comboBoxQuadra2);
		
		comboBoxQuadra3 = new JComboBox();
		comboBoxQuadra3.addItem("SELECIONAR");	
		comboBoxQuadra3.addItem("LARANJA");
		comboBoxQuadra3.addItem("AZUL");
		comboBoxQuadra3.addItem("VERDE");

		comboBoxQuadra3.setBounds(174, 399, 114, 24);
	
		getContentPane().add(comboBoxQuadra3);
		
		comboBoxQuadra4 = new JComboBox();
		comboBoxQuadra4.addItem("SELECIONAR");	
		comboBoxQuadra4.addItem("LARANJA");
		comboBoxQuadra4.addItem("AZUL");
		comboBoxQuadra4.addItem("VERDE");

		comboBoxQuadra4.setBounds(174, 426, 114, 24);
	
		getContentPane().add(comboBoxQuadra4);
		
		comboBoxQuadra5 = new JComboBox();
		comboBoxQuadra5.addItem("SELECIONAR");	
		comboBoxQuadra5.addItem("LARANJA");
		comboBoxQuadra5.addItem("AZUL");
		comboBoxQuadra5.addItem("VERDE");

		comboBoxQuadra5.setBounds(174, 452, 114, 24);
	
		getContentPane().add(comboBoxQuadra5);
		
		comboBoxQuadra6 = new JComboBox();
		comboBoxQuadra6.addItem("SELECIONAR");	
		comboBoxQuadra6.addItem("LARANJA");
		comboBoxQuadra6.addItem("AZUL");
		comboBoxQuadra6.addItem("VERDE");

		comboBoxQuadra6.setBounds(174, 477, 114, 24);
	
		getContentPane().add(comboBoxQuadra6);
		
		JLabel lblselecioneACategoria = new JLabel("*Distribuir jogos por categoria na quadra desejada");
		lblselecioneACategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblselecioneACategoria.setBounds(22, 503, 311, 43);
		getContentPane().add(lblselecioneACategoria);
		
		JLabel lblCategoria_1 = new JLabel("1ª Categoria");
		lblCategoria_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_1.setBounds(56, 349, 100, 15);
		getContentPane().add(lblCategoria_1);
		
		lblQuadra = new JLabel("QUADRA");
		lblQuadra.setFont(new Font("Dialog", Font.BOLD, 14));
		lblQuadra.setBounds(195, 328, 114, 15);
		getContentPane().add(lblQuadra);
		
		lblCategoria = new JLabel("2ª Categoria");
		lblCategoria.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria.setBounds(56, 377, 100, 15);
		getContentPane().add(lblCategoria);
		
		lblCategoria_2 = new JLabel("3ª Categoria");
		lblCategoria_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_2.setBounds(56, 404, 100, 15);
		getContentPane().add(lblCategoria_2);
		
		lblCategoria_3 = new JLabel("4ª Categoria");
		lblCategoria_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_3.setBounds(56, 431, 100, 15);
		getContentPane().add(lblCategoria_3);
		
		lblCategoria_4 = new JLabel("5ª Categoria");
		lblCategoria_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCategoria_4.setBounds(56, 457, 100, 15);
		getContentPane().add(lblCategoria_4);
		
		lblIniciantes = new JLabel("Iniciantes");
		lblIniciantes.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIniciantes.setBounds(66, 482, 100, 15);
		getContentPane().add(lblIniciantes);
		
		

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