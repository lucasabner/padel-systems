package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import fourzeta.controllers.desktop.InscricaoController;
import fourzeta.models.Dupla;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;

public class InscricaoDuplas extends JFrame {

	private final Dimension SIZE = new Dimension(800, 700);
	private Dupla dupla;
	private InscricaoController controllerCadastrar;
	private JTextField txtNome1;
	private JTextField txtNome2;
	private JTextField txtCpf1;
	private JTextField txtCpf2;
	private JTextField txtTelefone1;
	private JTextField txtTelefone2;
	private JTextField txtEmail1;
		private JTextField txtEmail2;
	private JTextField txtDataNascimento1;
	private JTextField txtDataNascimento2;
	private ButtonGroup atletaSexo1;
	private ButtonGroup atletaSexo2;
	private JRadioButton cbxMasc1;
	private JRadioButton cbxMasc2;
	private JRadioButton cbxOutro1;
	private JRadioButton cbxOutro2;
	private JRadioButton cbxFem1;
	private JRadioButton cbxFem2;
	private JComboBox comboCategoria;
	private JComboBox comboImpedimento;
	private JButton btnInscricao;
	private JButton btnPdf;
	private JLabel lblIcon;

	public InscricaoDuplas(Usuario usuario, Torneio torneio) throws ParseException {
		dupla = new Dupla();
		setTitle("Sistema de Gerenciamento de Padel");
		this.setSize(SIZE);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		MaskFormatter formatoCpf = new MaskFormatter("###.###.###-##");
		MaskFormatter formatoDataNasc = new MaskFormatter("##/##/####");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// mudando o icon
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);

		JLabel Titulo = new JLabel("Inscrição");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(14, 25, 764, 58);
		Titulo.setFont(new Font("Times New Roman", Font.BOLD, 45));
		getContentPane().add(Titulo);

		JPanel jpanelPrincipal = new JPanel();
		jpanelPrincipal.setBounds(10, 146, 379, 380);
		getContentPane().add(jpanelPrincipal);
		jpanelPrincipal.setLayout(null);

		JLabel nomeCompleto = new JLabel("Nome Completo:");
		nomeCompleto.setFont(new Font("Times New Roman", Font.BOLD, 16));
		nomeCompleto.setBounds(10, 11, 181, 23);
		jpanelPrincipal.add(nomeCompleto);

		txtNome1 = new JTextField();
		txtNome1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtNome1.setBounds(10, 36, 359, 23);
		jpanelPrincipal.add(txtNome1);
		txtNome1.setColumns(10);

		JLabel cpf = new JLabel("CPF:");
		cpf.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cpf.setBounds(10, 67, 130, 14);
		jpanelPrincipal.add(cpf);

		txtCpf1 = new JFormattedTextField(formatoCpf);
		txtCpf1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtCpf1.setColumns(10);
		txtCpf1.setBounds(10, 92, 99, 23);
		jpanelPrincipal.add(txtCpf1);

		JLabel telefone = new JLabel("Telefone:");
		telefone.setFont(new Font("Times New Roman", Font.BOLD, 16));
		telefone.setBounds(10, 123, 130, 14);
		jpanelPrincipal.add(telefone);

		txtTelefone1 = new JTextField();
		txtTelefone1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTelefone1.setColumns(10);
		txtTelefone1.setBounds(10, 148, 181, 23);
		jpanelPrincipal.add(txtTelefone1);

		JLabel email = new JLabel("E-mail:");
		email.setFont(new Font("Times New Roman", Font.BOLD, 16));
		email.setBounds(10, 179, 130, 14);
		jpanelPrincipal.add(email);

		txtEmail1 = new JTextField();
		txtEmail1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtEmail1.setColumns(10);
		txtEmail1.setBounds(10, 204, 359, 23);
		jpanelPrincipal.add(txtEmail1);

		JLabel sexo = new JLabel("Sexo:");
		sexo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		sexo.setBounds(10, 240, 75, 14);
		jpanelPrincipal.add(sexo);

		cbxMasc1 = new JRadioButton("Masculino");
		cbxMasc1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbxMasc1.setBounds(62, 236, 91, 23);
		jpanelPrincipal.add(cbxMasc1);

		cbxFem1 = new JRadioButton("Feminino");
		cbxFem1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbxFem1.setBounds(155, 236, 85, 23);
		jpanelPrincipal.add(cbxFem1);

		cbxOutro1 = new JRadioButton("Outro");
		cbxOutro1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbxOutro1.setBounds(248, 236, 71, 23);
		jpanelPrincipal.add(cbxOutro1);

		atletaSexo1 = new ButtonGroup();
		atletaSexo1.add(cbxMasc1);
		atletaSexo1.add(cbxFem1);
		atletaSexo1.add(cbxOutro1);

		JLabel dataNascimento = new JLabel("Data de Nascimento");
		dataNascimento.setFont(new Font("Times New Roman", Font.BOLD, 16));
		dataNascimento.setBounds(10, 271, 242, 14);
		jpanelPrincipal.add(dataNascimento);

		txtDataNascimento1 = new JFormattedTextField(formatoDataNasc);
		txtDataNascimento1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDataNascimento1.setBounds(10, 296, 75, 23);
		jpanelPrincipal.add(txtDataNascimento1);

		JLabel lblAt = new JLabel("Atleta 1");
		lblAt.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAt.setHorizontalAlignment(SwingConstants.CENTER);
		lblAt.setBounds(0, 111, 379, 23);
		getContentPane().add(lblAt);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(399, 148, 379, 378);
		getContentPane().add(panel);

		txtNome2 = new JTextField();
		txtNome2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtNome2.setColumns(10);
		txtNome2.setBounds(10, 36, 359, 23);
		panel.add(txtNome2);

		JLabel label_1 = new JLabel("CPF:");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_1.setBounds(10, 67, 130, 14);
		panel.add(label_1);

		txtCpf2 = new JFormattedTextField(formatoCpf);
		txtCpf2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtCpf2.setColumns(10);
		txtCpf2.setBounds(10, 92, 99, 23);
		panel.add(txtCpf2);

		JLabel label_2 = new JLabel("Telefone:");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_2.setBounds(10, 123, 130, 14);
		panel.add(label_2);

		txtTelefone2 = new JTextField();
		txtTelefone2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTelefone2.setColumns(10);
		txtTelefone2.setBounds(10, 148, 181, 23);
		panel.add(txtTelefone2);

		JLabel label_3 = new JLabel("E-mail:");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_3.setBounds(10, 170, 130, 23);
		panel.add(label_3);

		txtEmail2 = new JTextField();
		txtEmail2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtEmail2.setColumns(10);
		txtEmail2.setBounds(10, 204, 359, 23);
		panel.add(txtEmail2);

		JLabel label_4 = new JLabel("Sexo:");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_4.setBounds(10, 240, 46, 14);
		panel.add(label_4);

		cbxMasc2 = new JRadioButton("Masculino");
		cbxMasc2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbxMasc2.setBounds(62, 236, 91, 23);
		panel.add(cbxMasc2);

		cbxFem2 = new JRadioButton("Feminino");
		cbxFem2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbxFem2.setBounds(155, 236, 85, 23);
		panel.add(cbxFem2);

		cbxOutro2 = new JRadioButton("Outro");
		cbxOutro2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbxOutro2.setBounds(248, 236, 71, 23);
		panel.add(cbxOutro2);

		atletaSexo2 = new ButtonGroup();
		atletaSexo2.add(cbxMasc2);
		atletaSexo2.add(cbxFem2);
		atletaSexo2.add(cbxOutro2);

		JLabel label_5 = new JLabel("Data de Nascimento");
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_5.setBounds(10, 271, 207, 14);
		panel.add(label_5);

		txtDataNascimento2 = new JFormattedTextField(formatoDataNasc);
		txtDataNascimento2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDataNascimento2.setBounds(10, 296, 75, 23);
		panel.add(txtDataNascimento2);

		JLabel label = new JLabel("Nome Completo:");
		label.setBounds(0, 12, 191, 23);
		panel.add(label);
		label.setFont(new Font("Times New Roman", Font.BOLD, 16));

		btnInscricao = new JButton("Inscrever");
		btnInscricao.setBounds(419, 640, 144, 23);
		getContentPane().add(btnInscricao);
		controllerCadastrar = new InscricaoController(torneio, dupla, this);
		btnInscricao.addActionListener(controllerCadastrar);
		btnInscricao.setFont(new Font("Times New Roman", Font.BOLD, 16));

		btnPdf = new JButton("Gerar PDF");
		btnPdf.setBounds(595, 640, 174, 23);
		getContentPane().add(btnPdf);
		btnPdf.setFont(new Font("Times New Roman", Font.BOLD, 16));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(28, 640, 89, 23);
		getContentPane().add(btnVoltar);

		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));

		JLabel lblDadosDaSua = new JLabel("Atleta 2");
		lblDadosDaSua.setBounds(399, 111, 379, 23);
		getContentPane().add(lblDadosDaSua);
		lblDadosDaSua.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosDaSua.setFont(new Font("Times New Roman", Font.BOLD, 20));

		comboCategoria = new JComboBox();
		comboCategoria.setBounds(24, 562, 124, 22);
		getContentPane().add(comboCategoria);
		comboCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboCategoria.addItem("Selecionar");
		comboCategoria.addItem("PRIMEIRA");
		comboCategoria.addItem("SEGUNDA");
		comboCategoria.addItem("TERCEIRA");
		comboCategoria.addItem("QUARTA");
		comboCategoria.addItem("QUINTA");
		comboCategoria.addItem("INICIANTE");

		JLabel categorial = new JLabel("Categoria");
		categorial.setBounds(24, 538, 144, 23);
		getContentPane().add(categorial);
		categorial.setFont(new Font("Times New Roman", Font.BOLD, 16));

		comboImpedimento = new JComboBox();
		comboImpedimento.setBounds(205, 562, 181, 22);
		getContentPane().add(comboImpedimento);
		comboImpedimento.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboImpedimento.addItem("Nenhum");
		comboImpedimento.addItem("Quinta-Feira pela Noite");
		comboImpedimento.addItem("Sexta-Feira pela Noite");
		comboImpedimento.addItem("Sábado pela Manhã");

		JLabel lblImpedimento = new JLabel("Impedimento de Horário");
		lblImpedimento.setBounds(205, 538, 130, 23);
		getContentPane().add(lblImpedimento);
		lblImpedimento.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVoltar.addActionListener(new ActionListener() { // Implementando Voltar

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GerenciarTorneio tela = null;
				try {
					tela = new GerenciarTorneio(usuario, torneio);
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				tela.setVisible(true);
			}
		});

	}

	public void notifyCriacaoSucesso() {
		JOptionPane.showMessageDialog(this, "Dupla inscrita com sucesso!!");

	}

	public void notifyPdfSucesso() {
		JOptionPane.showMessageDialog(this, "PDF gerado com sucesso!!");

	}

	public JTextField getTxtNome1() {
		return txtNome1;
	}

	public void setTxtNome1(JTextField txtNome1) {
		this.txtNome1 = txtNome1;
	}

	public JTextField getTxtCpf1() {
		return txtCpf1;
	}

	public void setTxtCpf1(JTextField txtCpf1) {
		this.txtCpf1 = txtCpf1;
	}

	public JTextField getTxtTelefone1() {
		return txtTelefone1;
	}

	public void setTxtTelefone1(JTextField txtTelefone1) {
		this.txtTelefone1 = txtTelefone1;
	}

	public JTextField getTxtEmail1() {
		return txtEmail1;
	}

	public void setTxtEmail1(JTextField txtEmail1) {
		this.txtEmail1 = txtEmail1;
	}

	public JTextField getTxtNome2() {
		return txtNome2;
	}

	public void setTxtNome2(JTextField txtNome2) {
		this.txtNome2 = txtNome2;
	}

	public JTextField getTextTelefone2() {
		return txtTelefone2;
	}

	public void setTextTelefone2(JTextField textTelefone2) {
		this.txtTelefone2 = textTelefone2;
	}

	public JTextField getTxtEmail2() {
		return txtEmail2;
	}

	public void setTxtEmail2(JTextField txtEmail2) {
		this.txtEmail2 = txtEmail2;
	}

	public JTextField getTxtTelefone2() {
		return txtTelefone2;
	}

	public void setTxtTelefone2(JTextField txtTelefone2) {
		this.txtTelefone2 = txtTelefone2;
	}

	public JTextField getTxtCpf2() {
		return txtCpf2;
	}

	public void setTxtCpf2(JTextField txtCpf2) {
		this.txtCpf2 = txtCpf2;
	}

	public JTextField getTxtDataNascimento1() {
		return txtDataNascimento1;
	}

	public void setTxtDataNascimento1(JTextField txtDataNascimento1) {
		this.txtDataNascimento1 = txtDataNascimento1;
	}

	public JTextField getTxtDataNascimento2() {
		return txtDataNascimento2;
	}

	public void setTxtDataNascimento2(JTextField txtDataNascimento2) {
		this.txtDataNascimento2 = txtDataNascimento2;
	}

	public JRadioButton getCbxMasc1() {
		return cbxMasc1;
	}

	public void setCbxMasc1(JRadioButton cbxMasc1) {
		this.cbxMasc1 = cbxMasc1;
	}

	public JRadioButton getCbxMasc2() {
		return cbxMasc2;
	}

	public void setCbxMasc2(JRadioButton cbxMasc2) {
		this.cbxMasc2 = cbxMasc2;
	}

	public JRadioButton getCbxOutro1() {
		return cbxOutro1;
	}

	public void setCbxOutro1(JRadioButton cbxOutro1) {
		this.cbxOutro1 = cbxOutro1;
	}

	public JRadioButton getCbxOutro2() {
		return cbxOutro2;
	}

	public void setCbxOutro2(JRadioButton cbxOutro2) {
		this.cbxOutro2 = cbxOutro2;
	}

	public JRadioButton getCbxFem1() {
		return cbxFem1;
	}

	public void setCbxFem1(JRadioButton cbxFem1) {
		this.cbxFem1 = cbxFem1;
	}

	public JRadioButton getCbxFem2() {
		return cbxFem2;
	}

	public void setCbxFem2(JRadioButton cbxFem2) {
		this.cbxFem2 = cbxFem2;
	}

	public JComboBox getComboCategoria() {
		return comboCategoria;
	}

	public void setComboCategoria(JComboBox comboCategoria) {
		this.comboCategoria = comboCategoria;
	}

	public JButton getBtnInscricao() {
		return btnInscricao;
	}

	public void setBtnInscricao(JButton btnInscricao) {
		this.btnInscricao = btnInscricao;
	}

	public Dupla getDupla() {
		return dupla;
	}

	public void setDupla(Dupla dupla) {
		this.dupla = dupla;
	}

	public InscricaoController getControllerCadastrar() {
		return controllerCadastrar;
	}

	public void setControllerCadastrar(InscricaoController controllerCadastrar) {
		this.controllerCadastrar = controllerCadastrar;
	}


	public ButtonGroup getAtletaSexo1() {
		return atletaSexo1;
	}

	public void setAtletaSexo1(ButtonGroup atletaSexo1) {
		this.atletaSexo1 = atletaSexo1;
	}

	public ButtonGroup getAtletaSexo2() {
		return atletaSexo2;
	}

	public void setAtletaSexo2(ButtonGroup atletaSexo2) {
		this.atletaSexo2 = atletaSexo2;
	}

	public JButton getBtnPdf() {
		return btnPdf;
	}

	public void setBtnPdf(JButton btnPdf) {
		this.btnPdf = btnPdf;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

	public JComboBox getComboImpedimento() {
		return comboImpedimento;
	}

	public void setComboImpedimento(JComboBox comboImpedimento) {
		this.comboImpedimento = comboImpedimento;
	}

	public static void main(String[] args) throws IOException, ParseException {

		InscricaoDuplas frame = new InscricaoDuplas(null, null);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
		

	}
}
