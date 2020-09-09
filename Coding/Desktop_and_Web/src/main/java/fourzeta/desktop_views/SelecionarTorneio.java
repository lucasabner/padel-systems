package fourzeta.desktop_views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import fourzeta.controllers.desktop.SelecionarTorneioController;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;


public class SelecionarTorneio extends JFrame {

	private final Dimension SIZE = new Dimension(600, 355);
	private SelecionarTorneioController controller;
	private JButton btnSelecionar;
	private JComboBox comboCircuito;
	private JComboBox comboTorneio;
	private JLabel lblIcon;
	private JButton btnNovoCircuto;
	private JButton btnNovoTorneio;
	private JButton btnEditarCircuito;
	private JButton btnEditarTorneio;
	private JButton btnExcluirTorneio;
	private JButton btnExcluirCircuito;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private Usuario usuario;

	public SelecionarTorneio(Usuario usuario) throws ParseException, IOException, NotBoundException {
		this.usuario = usuario;
		setTitle("Sistema de Gerenciamento de Padel");
		this.setSize(SIZE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// mudando o icon
		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
		setIconImage(imgLogin.getImage());
		lblIcon = new JLabel(imgLogin);
		getContentPane().add(lblIcon);

		JLabel Titulo = new JLabel("Gerenciar");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(0, 12, 600, 58);
		Titulo.setFont(new Font("Times New Roman", Font.BOLD, 45));
		getContentPane().add(Titulo);

		CircuitoResource cr = new CircuitoResource();
		comboCircuito = new JComboBox();
		comboCircuito.setBounds(404, 99, 165, 22);
		comboCircuito.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboTorneio = new JComboBox();
		comboTorneio.setBounds(404, 150, 165, 22);
		comboTorneio.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		comboCircuito.addItem("Selecionar");
		comboTorneio.addItem("Selecionar");
		for (Circuito circuito : cr.listaCircuitos()) {
			comboCircuito.addItem(circuito.getNome());
		}
		comboCircuito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comboTorneio.removeAllItems();
				comboTorneio.addItem("Selecionar");
				for (Circuito circuito : cr.listaCircuitos()) {
					if (circuito.getNome().equalsIgnoreCase(comboCircuito.getSelectedItem().toString())) {
						for (int i = 0; i < circuito.getTorneios().size(); i++) {
							comboTorneio.addItem(circuito.getTorneios().get(i).getNome());
						}
					}
				}
			}
		});
		getContentPane().add(comboCircuito);
		getContentPane().add(comboTorneio);

		btnSelecionar = new JButton("Selecionar");
		controller = new SelecionarTorneioController(usuario, this);
		btnSelecionar.addActionListener(controller);
		btnSelecionar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSelecionar.setBounds(404, 198, 165, 23);
		getContentPane().add(btnSelecionar);

		JLabel lblCircuito = new JLabel("Circuito");
		lblCircuito.setBounds(404, 82, 66, 15);
		getContentPane().add(lblCircuito);

		JLabel lblTorneio = new JLabel("Torneio");
		lblTorneio.setBounds(404, 133, 66, 15);
		getContentPane().add(lblTorneio);

		JButton btnVoltar = new JButton("Voltar");

		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVoltar.addActionListener(new ActionListener() { // Implementando Voltar

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Inicial inicial = null;
				try {
					inicial = new Inicial(usuario);
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);
			}
		});
		btnVoltar.setBounds(480, 286, 89, 23);
		getContentPane().add(btnVoltar);

		JLabel lblselecioneOCircuito = new JLabel("*Selecione o Circuito e o Torneio que deseja gerenciar.");
		lblselecioneOCircuito.setBounds(12, 269, 494, 58);
		getContentPane().add(lblselecioneOCircuito);

		btnNovoCircuto = new JButton("Novo Circuito");
		btnNovoCircuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarCircuito inicial = null;
				try {
					inicial = new CadastrarCircuito(usuario);
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);
			}
		});
		btnNovoCircuto.setBounds(26, 98, 138, 25);
		getContentPane().add(btnNovoCircuto);

		btnNovoTorneio = new JButton("Novo Torneio");
		btnNovoTorneio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarTorneio inicial = null;
				try {
					inicial = new CadastrarTorneio(usuario);
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);

			}
		});
		btnNovoTorneio.setBounds(221, 98, 138, 25);
		getContentPane().add(btnNovoTorneio);

		btnEditarCircuito = new JButton("Editar Circuito");
		btnEditarCircuito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Editar inicial = null;
				try {
					inicial = new Editar(usuario, "Circuito");
				} catch (ParseException | IOException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);
			}
		});
		btnEditarCircuito.setBounds(26, 149, 138, 25);
		getContentPane().add(btnEditarCircuito);

		btnExcluirCircuito = new JButton("Excluir Circuito");
		btnExcluirCircuito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExcluirCircuito inicial = null;
				try {
					inicial = new ExcluirCircuito(usuario);
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);
			}
		});
		btnExcluirCircuito.setBounds(26, 198, 138, 25);
		getContentPane().add(btnExcluirCircuito);

		btnEditarTorneio = new JButton("Editar Torneio");
		btnEditarTorneio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Editar inicial = null;
				try {
					inicial = new Editar(usuario, "Torneio");
				} catch (ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);
			}
		});
		btnEditarTorneio.setBounds(221, 149, 138, 25);
		getContentPane().add(btnEditarTorneio);

		btnExcluirTorneio = new JButton("Excluir Torneio");
		btnExcluirTorneio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExcluirTorneio inicial = null;
				try {
					inicial = new ExcluirTorneio(usuario);
				} catch (ParseException | IOException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				inicial.setVisible(true);
			}
		});
		btnExcluirTorneio.setBounds(221, 198, 138, 25);
		getContentPane().add(btnExcluirTorneio);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 79, 148, 167);
		getContentPane().add(scrollPane);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(217, 79, 148, 167);
		getContentPane().add(scrollPane_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(400, 79, 175, 167);
		getContentPane().add(scrollPane_2);

	}

	public void notifyEncerrarInscricaoCircuito() {
		JOptionPane.showMessageDialog(this, "Selecione um Circuito para encerrar inscrições.");

	}

	public void notifyEncerrarInscricaoTorneio() {
		JOptionPane.showMessageDialog(this, "Selecione um Torneio para encerrar inscrições.");

	}

	public void notifyEncerramentoSucesso() {
		JOptionPane.showMessageDialog(this, "Inscrições encerradas com sucesso!!");

	}

	public JComboBox getComboCircuito() {
		return comboCircuito;
	}

	public void setComboCircuito(JComboBox comboCircuito) {
		this.comboCircuito = comboCircuito;
	}

	public JComboBox getComboTorneio() {
		return comboTorneio;
	}

	public void setComboTorneio(JComboBox comboTorneio) {
		this.comboTorneio = comboTorneio;
	}

	public Dimension getSIZE() {
		return SIZE;
	}

	public static void main(String[] args) throws IOException, ParseException, NotBoundException {

		SelecionarTorneio frame = new SelecionarTorneio(null);
		frame.setVisible(true);
		frame.setSize(frame.SIZE);
		frame.setLocationRelativeTo(null);

	}
}
