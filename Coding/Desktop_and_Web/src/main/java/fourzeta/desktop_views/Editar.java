package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import fourzeta.models.Circuito;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;
import fourzeta.resources.TorneioResource;

public class Editar extends JFrame {

	private final Dimension SIZE = new Dimension(350, 200);
	private final String FONTE = "Dialog";
	private JLabel lblCadastrar;
	private JLabel lblNome;
	private JButton btnVoltar;
	private JButton btnEditar;
	private JComboBox<String> comboBox;

	public Editar(Usuario usuario, String nomeEdit) throws ParseException, IOException, NotBoundException {
		this.setTitle(nomeEdit);
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(configLblCadastrar(nomeEdit));
		this.getContentPane().add(configLblNome(nomeEdit));
		this.getContentPane().add(configBtnVoltar(usuario));
		this.getContentPane().add(configBtnEditar());
		this.getContentPane().add(configCombobox(nomeEdit));
	}

	private JLabel configLblCadastrar(String nomeEdit) {
		this.lblCadastrar = new JLabel("Editar " + nomeEdit);
		this.lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCadastrar.setFont(new Font(this.FONTE, Font.BOLD, 30));
		this.lblCadastrar.setBounds(12, 12, 321, 26);
		return this.lblCadastrar;		
	}
	
	private JLabel configLblNome(String nomeEdit) {
		this.lblNome = new JLabel("Selecione um " + nomeEdit + ": ");
		this.lblNome.setFont(new Font(this.FONTE, Font.BOLD, 14));
		this.lblNome.setBounds(22, 66, 300, 15);
		return this.lblNome;
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
		this.btnVoltar.setBounds(22, 133, 114, 25);
		return this.btnVoltar;
	}
	
	private JButton configBtnEditar() {
		this.btnEditar = new JButton("Editar");
		this.btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		this.btnEditar.setBounds(212, 133, 114, 25);
		return this.btnEditar;
	}
	
	private JComboBox configCombobox(String nomeEdit) {
		this.comboBox = new JComboBox<String>();
		this.comboBox.setBounds(22, 86, 301, 24);
		this.comboBox.addItem("Selecionar");
		if(nomeEdit == "Circuito") {
			CircuitoResource cr = new CircuitoResource();
			for (Circuito circuito : cr.listaCircuitos()) {
				this.comboBox.addItem(circuito.getNome());
			}
		}else {
			TorneioResource tr = new TorneioResource();
			for (Torneio torneio : tr.listaTorneios()) {
				this.comboBox.addItem(torneio.getNome());
			}
		}
		return this.comboBox;
	}
	
	public void notifyCampoIncompleto() {
		JOptionPane.showMessageDialog(null, "Você não preencheu todos os Campos!");
	}

	public void notifyEdicaoRealizada(String nomeEdit) {
		JOptionPane.showMessageDialog(null, nomeEdit + " Editado com Sucesso!");
	}

	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
		Usuario g = new Usuario();
		g.setNome("Teste");
		Editar frame = new Editar(g, "Circuito");
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}

}