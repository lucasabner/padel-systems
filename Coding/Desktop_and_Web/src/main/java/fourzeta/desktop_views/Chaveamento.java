package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import fourzeta.IElement;
import fourzeta.controllers.desktop.ChavesPdfController;
import fourzeta.controllers.desktop.RetornarInicial;
import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.table.ChavesTableModel;

public class Chaveamento extends JFrame {

	private static final Dimension SIZE = new Dimension(800, 550);
	private JLabel titulo;
	private ChavesPdfController controllerPdf;
	private RetornarInicial retorna;
	private JButton btnVoltar;
	private JButton btnPdf;
	private JTable duplasTable;
	private ChavesTableModel model;
	private JScrollPane sp;
	private JLabel lblIcon;
	private ImageIcon imgLogin;

	public Chaveamento(Usuario usuario, Circuito circuito) throws ParseException {

		criarTitulo();

		setIcone();

		criarPainel();

		botaoVoltar(usuario);

		botaoPdf(circuito);

	}

	public void botaoVoltar(Usuario usuario) {
		btnVoltar = new JButton("Voltar");
		retorna = new RetornarInicial(usuario, this);
		btnVoltar.addActionListener(retorna);
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVoltar.setBounds(44, 409, 106, 23);
		getContentPane().add(btnVoltar);

	}

	public void botaoPdf(Circuito circuito) {
		btnPdf = new JButton("Gerar PDF");
		btnPdf.setBounds(558, 409, 174, 23);
		controllerPdf = new ChavesPdfController(circuito, this);
		btnPdf.addActionListener(controllerPdf);
		getContentPane().add(btnPdf);
		btnPdf.setFont(new Font("Times New Roman", Font.BOLD, 16));
	}

	public void criarTitulo() {
		setTitle("Sistema de Gerenciamento de Padel");
		titulo = new JLabel("Chaveamento");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 784, 58);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 45));
		getContentPane().add(titulo);
	}

	public void setIcone() {
		imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);
	}

	public void criarPainel() {
		getContentPane().setLayout(null);
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		model = new ChavesTableModel();
		duplasTable = new JTable(model);
		duplasTable.setBounds(53, 100, 402, 150);
		sp = new JScrollPane(duplasTable);
		sp.setLocation(36, 140);
		sp.setBounds(44, 80, 688, 318);
		getContentPane().add(sp);
	}

	public void notifyPdfSucesso() {
		JOptionPane.showMessageDialog(this, "PDF gerado com sucesso!!");

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

	public void readTable(List<IElement> list) { // Método para ler FINDALL

		for (IElement element : list) {
			Chave chave = (Chave) element;
			model.addRow(chave);
		}

	}

	public static void main(String[] args) throws IOException, ParseException {

		Chaveamento frame = new Chaveamento(null, null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
