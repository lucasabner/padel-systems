package fourzeta.desktop_views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Creditos extends JFrame {

	private final Dimension SIZE = new Dimension(800, 550);
	private JPanel painelPrincipal;
	private JPanel painelMBotton;
	private JLabel lblText;
	private JButton btnOk;
	private JLabel lblIcone;
	private JPanel painelFundo;
	private JTable tabela;
	private JScrollPane barraRolagem;

	Object[][] dados = { { "Arthur Malmann Becker", "arthmalbeck@gmail.com" },
			{ "Lucas Abner Leal Silva", "lucasabner98@outlook.com " },
			{ "Lucas Augusto Guterres Garais", "lucasgarais@hotmail.com" },
			{ "Wagner Inácio de Oliveira", "wagner.inacio16@outlook.com" } };

	String[] colunas = { "Nome", "Email" };

	public Creditos() {
		super("Contatos");

		// Mudando o icone da aplicacao
		ImageIcon imgLogin = new ImageIcon("assets\\icone2.png");
		setIconImage(imgLogin.getImage());
		lblIcone = new JLabel(imgLogin);

		setTitle("Sistema de Gerenciamento de Padel");
		setSize(SIZE);
		this.setLocationRelativeTo(null);
		painelPrincipal = new JPanel();
		setContentPane(painelPrincipal);

		painelPrincipal.setLayout(new BorderLayout());

		// add img
		JPanel jpImg = new JPanel();
		jpImg.add(lblIcone);
		jpImg.setBackground(null);
		painelPrincipal.add(jpImg, BorderLayout.NORTH);

		// label
		lblText = new JLabel("Desenvolvedores");
		lblText.setFont(new Font("Times New Roman", Font.BOLD, 45));
		lblText.setSize(500, 20);
		lblText.setLocation(90, 30);
		jpImg.add(lblText, BorderLayout.CENTER);

		painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(1, 1));
		tabela = new JTable(dados, colunas);
		barraRolagem = new JScrollPane(tabela);
		painelFundo.add(barraRolagem);
		painelPrincipal.add(painelFundo, BorderLayout.CENTER);

		btnOk = new JButton("Voltar");
		btnOk.setLocation(250, 0);
		btnOk.setSize(100, 20);
//	  	btnOk.setBackground(Color.decode("#20B2AA"));
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Creditos.this.setVisible(false);
				Creditos.this.dispose();
			}
		});
		JLabel lblUniversidade = new JLabel("Engenharia de Software - UNIPAMPA - Grupo 4 RPIV 2019-02");
		painelMBotton = new JPanel();
		painelMBotton.setLayout(new FlowLayout());
		painelMBotton.add(btnOk);
		painelMBotton.add(lblUniversidade);
		painelMBotton.setBackground(null);
		painelMBotton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		painelPrincipal.add(painelMBotton, BorderLayout.SOUTH);

	}
}
