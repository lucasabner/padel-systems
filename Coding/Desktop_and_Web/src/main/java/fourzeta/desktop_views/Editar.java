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
	private JLabel lblCadastrar;
	private JLabel lblNome;
	private JButton btnVoltar;
	private JButton btnCadastrar;
	private JComboBox comboBox;

	public Editar(Usuario usuario, String nomeEdit) throws ParseException, IOException, NotBoundException {

		setTitle(nomeEdit);
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblCadastrar = new JLabel("Editar " + nomeEdit);
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setFont(new Font("Dialog", Font.BOLD, 30));
		lblCadastrar.setBounds(12, 12, 321, 26);
		getContentPane().add(lblCadastrar);

		lblNome = new JLabel("Selecione um " + nomeEdit + ": ");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNome.setBounds(22, 66, 300, 15);
		getContentPane().add(lblNome);

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
		btnVoltar.setBounds(22, 133, 114, 25);
		getContentPane().add(btnVoltar);

		btnCadastrar = new JButton("Editar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCadastrar.setBounds(212, 133, 114, 25);
		getContentPane().add(btnCadastrar);

		if (nomeEdit == "Circuito") {
			CircuitoResource cr = new CircuitoResource();
			comboBox = new JComboBox();
			comboBox.setBounds(22, 86, 301, 24);
			comboBox.addItem("Selecionar");
			for (Circuito circuito : cr.listaCircuitos()) {
				comboBox.addItem(circuito.getNome());
			}

			getContentPane().add(comboBox);
		} else {
			TorneioResource tr = new TorneioResource();
			comboBox = new JComboBox();
			comboBox.setBounds(22, 86, 301, 24);
			comboBox.addItem("Selecionar");
			for (Torneio torneio : tr.listaTorneios()) {
				comboBox.addItem(torneio.getNome());
			}

			getContentPane().add(comboBox);
		}
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
		Editar frame = new Editar(g, "");
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}

}