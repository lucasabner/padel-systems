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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import fourzeta.controllers.desktop.CadastrarCircuitoController;
import fourzeta.models.Usuario;

public class CadastrarCircuito extends JFrame {

	private final Dimension SIZE = new Dimension(350, 350);
	private JTextField textNomeCircuito;
	private JLabel lblCadastrar;
	private JLabel lblNome;
	private JLabel lblDescrio;
	private JButton btnVoltar;
	private JButton btnCadastrar;
	private TextField textDescricaoCircuito;

	public CadastrarCircuito(Usuario usuario) throws ParseException, IOException {

		setTitle("Cadastrar Circuito");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblCadastrar = new JLabel("Cadastrar Circuito");
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setFont(new Font("Dialog", Font.BOLD, 30));
		lblCadastrar.setBounds(12, 12, 321, 26);
		getContentPane().add(lblCadastrar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNome.setBounds(22, 50, 66, 15);
		getContentPane().add(lblNome);

		textNomeCircuito = new JTextField();
		textNomeCircuito.setBounds(22, 74, 300, 19);
		getContentPane().add(textNomeCircuito);
		textNomeCircuito.setColumns(10);

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
		btnVoltar.setBounds(22, 275, 114, 25);
		getContentPane().add(btnVoltar);

		btnCadastrar = new JButton("Cadastrar");
		CadastrarCircuitoController cadCircuito = new CadastrarCircuitoController(usuario, this);
		btnCadastrar.addActionListener(cadCircuito);
		btnCadastrar.setBounds(208, 275, 114, 25);
		getContentPane().add(btnCadastrar);

		textDescricaoCircuito = new TextField();
		textDescricaoCircuito.setBounds(22, 128, 300, 115);
		getContentPane().add(textDescricaoCircuito);
		textDescricaoCircuito.setColumns(10);
	}

	public JTextField getTextNomeCircuito() {
		return textNomeCircuito;
	}

	public void setTextNomeCircuito(JTextField textNomeCircuito) {
		this.textNomeCircuito = textNomeCircuito;
	}

	public TextField getTextDescricaoCircuito() {
		return textDescricaoCircuito;
	}

	public void setTextDescricaoCircuito(TextField textDescricaoCircuito) {
		this.textDescricaoCircuito = textDescricaoCircuito;
	}

	public JLabel getLblCadastrar() {
		return lblCadastrar;
	}

	public void setLblCadastrar(JLabel lblCadastrar) {
		this.lblCadastrar = lblCadastrar;
	}

	public JLabel getLblNome() {
		return lblNome;
	}

	public void setLblNome(JLabel lblNome) {
		this.lblNome = lblNome;
	}

	public JLabel getLblDescrio() {
		return lblDescrio;
	}

	public void setLblDescrio(JLabel lblDescrio) {
		this.lblDescrio = lblDescrio;
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

	public Dimension getSIZE() {
		return SIZE;
	}

	public void notifyCampoIncompleto() {
		JOptionPane.showMessageDialog(null, "Você não preencheu todos os Campos!");
	}

	public void notifyCadastroRealizado() {
		JOptionPane.showMessageDialog(null, "Circuito Cadastrado com Sucesso!");
	}

	public static void main(String[] args) throws IOException, ParseException {
		Usuario g = new Usuario();
		g.setNome("Teste");
		CadastrarCircuito frame = new CadastrarCircuito(g);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}
}