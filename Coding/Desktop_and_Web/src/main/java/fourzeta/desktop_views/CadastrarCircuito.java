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
	private final String FONTE = "Dialog";
	private JTextField textNomeCircuito;
	private JLabel lblCadastrar;
	private JLabel lblNome;
	private JLabel lblDescricao;
	private JButton btnVoltar;
	private JButton btnCadastrar;
	private TextField textDescricaoCircuito;

	public CadastrarCircuito(Usuario usuario) throws ParseException, IOException {
		this.configFrame();
		this.getContentPane().add(configLblCadastrar());
		this.getContentPane().add(configLblNome());
		this.getContentPane().add(configTxtNome());	
		this.getContentPane().add(configLblDescricao());
		this.getContentPane().add(configBtnVoltar(usuario));
		this.getContentPane().add(configBtnCadastrar(usuario));
		this.getContentPane().add(configTxtDescricao());
	}

	private void configFrame() {
		this.setTitle("Cadastrar Circuito");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
	}
	
	private JLabel configLblCadastrar() {
		this.lblCadastrar = new JLabel("Cadastrar Circuito");
		this.lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCadastrar.setFont(new Font(this.FONTE, Font.BOLD, 30));
		this.lblCadastrar.setBounds(12, 12, 321, 26);
		return this.lblCadastrar;
	}
	
	private JLabel configLblNome() {
		this.lblNome = new JLabel("Nome:");
		this.lblNome.setFont(new Font(this.FONTE, Font.BOLD, 14));
		this.lblNome.setBounds(22, 50, 66, 15);
		return this.lblNome;
	}
	
	private JTextField configTxtNome() {
		this.textNomeCircuito = new JTextField();
		this.textNomeCircuito.setBounds(22, 74, 300, 19);
		this.textNomeCircuito.setColumns(10);
		return this.textNomeCircuito;
	}
	
	private JLabel configLblDescricao() {
		this.lblDescricao = new JLabel("Descrição:");
		this.lblDescricao.setFont(new Font(this.FONTE, Font.BOLD, 14));
		this.lblDescricao.setBounds(22, 104, 90, 15);
		return this.lblDescricao;
	}
	
	private TextField configTxtDescricao() {
		this.textDescricaoCircuito = new TextField();
		this.textDescricaoCircuito.setBounds(22, 128, 300, 115);
		this.textDescricaoCircuito.setColumns(10);
		return this.textDescricaoCircuito;
	}
	
	private JButton configBtnVoltar(Usuario usuario) {
		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.addActionListener(new ActionListener() {
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
		this.btnVoltar.setBounds(22, 275, 114, 25);
		return this.btnVoltar;
	}
	
	private JButton configBtnCadastrar(Usuario usuario) {
		this.btnCadastrar = new JButton("Cadastrar");
		CadastrarCircuitoController cadCircuito = new CadastrarCircuitoController(usuario, this);
		this.btnCadastrar.addActionListener(cadCircuito);
		this.btnCadastrar.setBounds(208, 275, 114, 25);
		return this.btnCadastrar;
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
		return lblDescricao;
	}

	public void setLblDescrio(JLabel lblDescrio) {
		this.lblDescricao = lblDescrio;
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