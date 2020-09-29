package fourzeta.desktop_views;

import java.awt.Dimension;
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import fourzeta.IElement;
import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Usuario;
import fourzeta.table.ChavesTableModel;

public class Chaveamento extends JFrame {

	private static final Dimension SIZE = new Dimension(800, 550);
	private JLabel titulo;
	//private ChavesPdfController controllerPdf;
	//private MostrarJogosController jogosController;
	private JButton btnVoltar;
	private JButton btnPdf;
	private JTable duplasTable;
	private ChavesTableModel model;
	private JScrollPane sp;
	private JButton btnVisualizarJogos;
	private JLabel lblIcon;

	public Chaveamento(Usuario usuario, Circuito circuito) throws ParseException {
//		setTitle("Sistema de Gerenciamento de Padel");
//
//		// mudando o icon
//		ImageIcon imgLogin = new ImageIcon("assets\\FourZeta.png");
//		setIconImage(imgLogin.getImage());
//		lblIcon = new JLabel(imgLogin);
//		getContentPane().add(lblIcon);
//
//		getContentPane().setLayout(null);
//		this.setSize(SIZE);
//		this.setLocationRelativeTo(null);
//		model = new ChavesTableModel();
//		duplasTable = new JTable(model);
//		duplasTable.setBounds(53, 100, 402, 150);
//		sp = new JScrollPane(duplasTable);
//		sp.setLocation(36, 140);
//		sp.setBounds(44, 80, 688, 318);
//		getContentPane().add(sp);
//
//		titulo = new JLabel("Chaveamento");
//		titulo.setHorizontalAlignment(SwingConstants.CENTER);
//		titulo.setBounds(0, 11, 784, 58);
//		titulo.setFont(new Font("Times New Roman", Font.BOLD, 45));
//		getContentPane().add(titulo);
//
//		btnVoltar = new JButton("Voltar");
//		btnVoltar.addActionListener(new ActionListener() { // Implementando Voltar
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				Inicial inicial = null;
//				try {
//					inicial = new Inicial(usuario);
//				} catch (ParseException | IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				setVisible(false);
//				inicial.setVisible(true);
//			}
//		});
//		btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
//		btnVoltar.setBounds(44, 409, 106, 23);
//		getContentPane().add(btnVoltar);
//
//		btnPdf = new JButton("Gerar PDF");
//		btnPdf.setBounds(558, 409, 174, 23);
//		controllerPdf = new ChavesPdfController(circuito, this);
//		btnPdf.addActionListener(controllerPdf);
//		getContentPane().add(btnPdf);
//		btnPdf.setFont(new Font("Times New Roman", Font.BOLD, 16));
//
//		jogosController = new MostrarJogosController(circuito, this);
//		btnVisualizarJogos = new JButton("Visualizar Jogos");
//		btnVisualizarJogos.addActionListener(jogosController);
//		btnVisualizarJogos.setFont(new Font("Times New Roman", Font.BOLD, 16));
//		btnVisualizarJogos.setBounds(372, 409, 156, 23);
//		getContentPane().add(btnVisualizarJogos);

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

//	public static void main(String[] args) throws IOException, ParseException {
//
//		TelaChaveamento frame = new TelaChaveamento();
//		frame.setVisible(true);
//		frame.setLocationRelativeTo(null);
//	}
}
