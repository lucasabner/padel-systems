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
import fourzeta.controllers.desktop.ExcluirTorneioController;
import fourzeta.controllers.desktop.RetornarInicial;
import fourzeta.models.Usuario;
import fourzeta.models.Torneio;
import fourzeta.resources.TorneioResource;

public class ExcluirTorneio extends JFrame {

	private final Dimension SIZE = new Dimension(350, 200);
	private JLabel lblTitulo;
	private JLabel lblNome;
	private JButton btnVoltar;
	private RetornarInicial retorna;
	private JButton btnExcluir;
	private JComboBox comboBoxTorneio;
	private ExcluirTorneioController excluirTorneio;
	private TorneioResource tr;

	public ExcluirTorneio(Usuario usuario) throws ParseException, IOException, NotBoundException {

		setTitle("Excluir Torneio");
		setSize(SIZE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		criarRotulos();

		botaoExcluir(usuario);

		botaoVoltar(usuario);

		comboBoxTorneio();
	}

	public void comboBoxTorneio() {
		tr = new TorneioResource();
		comboBoxTorneio = new JComboBox();
		comboBoxTorneio.addItem("Selecionar");
		for (Torneio torneio : tr.listaTorneios()) {
			comboBoxTorneio.addItem(torneio.getNome());
		}
		comboBoxTorneio.setBounds(22, 86, 301, 24);
		getContentPane().add(comboBoxTorneio);
	}

	public void botaoVoltar(Usuario usuario) {
		btnVoltar = new JButton("Voltar");
		retorna = new RetornarInicial(usuario, this);
		btnVoltar.addActionListener(retorna);
		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVoltar.setBounds(44, 409, 106, 23);
		getContentPane().add(btnVoltar);

	}

	public void botaoExcluir(Usuario usuario) {
		btnExcluir = new JButton("Excluir");
		excluirTorneio = new ExcluirTorneioController(usuario, this);
		btnExcluir.addActionListener(excluirTorneio);
		btnExcluir.setBounds(212, 133, 114, 25);
		getContentPane().add(btnExcluir);
	}

	public void criarRotulos() {
		lblTitulo = new JLabel("Excluir Torneio");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitulo.setBounds(12, 12, 321, 26);
		getContentPane().add(lblTitulo);

		lblNome = new JLabel("Selecione um torneio:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNome.setBounds(22, 66, 300, 15);
		getContentPane().add(lblNome);
	}

	public JComboBox getComboBoxTorneio() {
		return comboBoxTorneio;
	}

	public void setComboBoxTorneio(JComboBox comboBoxTorneio) {
		this.comboBoxTorneio = comboBoxTorneio;
	}

	public void notifyExluirTorneio() {
		JOptionPane.showMessageDialog(null, "Torneio excluido com Sucesso!");

	}

	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
		Usuario g = new Usuario();
		g.setNome("Teste");
		ExcluirTorneio frame = new ExcluirTorneio(g);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}
}