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
import fourzeta.controllers.desktop.ExcluirCircuitoController;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;

public class ExcluirCircuito extends JFrame {

	private final Dimension SIZE = new Dimension(350, 200);
	private JLabel lblTitulo;
	private JLabel lblNomeCircuito;
	private JButton btnVoltar;
	private JButton btnExcluir;
	private JComboBox comboBoxCircuito;
	private Circuito circuito;

	public ExcluirCircuito(Usuario usuario) throws ParseException, IOException, NotBoundException {

		setTitle("Excluir Circuito");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblTitulo = new JLabel("Excluir Circuito");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitulo.setBounds(12, 12, 321, 26);
		getContentPane().add(lblTitulo);

		lblNomeCircuito = new JLabel("Selecione um circuito:");
		lblNomeCircuito.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeCircuito.setBounds(22, 66, 300, 15);
		getContentPane().add(lblNomeCircuito);

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

		btnExcluir = new JButton("Excluir");
		ExcluirCircuitoController controller = new ExcluirCircuitoController(usuario, this);
		btnExcluir.addActionListener(controller);
		getContentPane().add(btnExcluir);
		btnExcluir.setBounds(209, 133, 114, 25);

		CircuitoResource cr = new CircuitoResource();
		comboBoxCircuito = new JComboBox();
		comboBoxCircuito.setBounds(22, 90, 300, 24);
		comboBoxCircuito.addItem("Selecionar");
		for (Circuito circuito : cr.listaCircuitos()) {
			comboBoxCircuito.addItem(circuito.getNome());
		}

		getContentPane().add(comboBoxCircuito);

	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	public void setBtnVoltar(JButton btnVoltar) {
		this.btnVoltar = btnVoltar;
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}

	public void setBtnExcluir(JButton btnExcluir) {
		this.btnExcluir = btnExcluir;
	}

	public JComboBox getComboBoxCircuito() {
		return comboBoxCircuito;
	}

	public void setComboBoxCircuito(JComboBox comboBoxCircuito) {
		this.comboBoxCircuito = comboBoxCircuito;
	}

	public void notifyExluirCircuito() {
		JOptionPane.showMessageDialog(null, "Circuito excluido com Sucesso!");

	}

	public static void main(String[] args) throws IOException, ParseException, NotBoundException {
		Usuario g = new Usuario();
		g.setNome("Teste");
		ExcluirCircuito frame = new ExcluirCircuito(g);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);
	}
}