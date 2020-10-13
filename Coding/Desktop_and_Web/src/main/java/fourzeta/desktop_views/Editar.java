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

import fourzeta.controllers.desktop.EditarController;
import fourzeta.models.Circuito;
import fourzeta.models.Torneio;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;
import fourzeta.resources.TorneioResource;

public class Editar extends JFrame {

	private final Dimension SIZE = new Dimension(350, 200);
	private final String FONTE = "Dialog";
	private EditarController controller;
	private JLabel lblCadastrar;
	private JLabel lblNome;
	private String nomeEdit;
	private JButton btnVoltar;
	private JButton btnEditar;
	private JComboBox<String> comboBox;

	public Editar(Usuario usuario, String nomeEdit) throws ParseException, IOException, NotBoundException {
		this.nomeEdit = nomeEdit;
		this.controller = new EditarController(usuario, nomeEdit,this);
		this.configFrame();
		this.getContentPane().add(configLblCadastrar());
		this.getContentPane().add(configLblNome());
		this.getContentPane().add(configBtnVoltar());
		this.getContentPane().add(configBtnEditar());
		this.getContentPane().add(configCombobox());
	}
	
	private void configFrame() {
		this.setTitle(this.nomeEdit);
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
	}

	private JLabel configLblCadastrar() {
		this.lblCadastrar = new JLabel("Editar " + this.nomeEdit);
		this.lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCadastrar.setFont(new Font(this.FONTE, Font.BOLD, 30));
		this.lblCadastrar.setBounds(12, 12, 321, 26);
		return this.lblCadastrar;		
	}
	
	private JLabel configLblNome() {
		this.lblNome = new JLabel("Selecione um " + this.nomeEdit + ": ");
		this.lblNome.setFont(new Font(this.FONTE, Font.BOLD, 14));
		this.lblNome.setBounds(22, 66, 300, 15);
		return this.lblNome;
	}
	
	private JButton configBtnVoltar() {
		this.btnVoltar = new JButton("Voltar");
		this.btnVoltar.setName("btnVoltar");
		this.btnVoltar.addActionListener(this.controller);
		this.btnVoltar.setBounds(22, 133, 114, 25);
		return this.btnVoltar;
	}
	
	private JButton configBtnEditar() {
		this.btnEditar = new JButton("Editar");
		this.btnEditar.setName("btnEditar");
		this.btnEditar.addActionListener(this.controller);
		this.btnEditar.setBounds(212, 133, 114, 25);
		return this.btnEditar;
	}
	
	private JComboBox configCombobox() {
		this.comboBox = new JComboBox<String>();
		this.comboBox.setBounds(22, 86, 301, 24);
		this.comboBox.addItem("Selecionar");
		if(this.nomeEdit == "Circuito") {
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

	public void notifyEdicaoRealizada() {
		JOptionPane.showMessageDialog(null, this.nomeEdit + " Editado com Sucesso!");
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