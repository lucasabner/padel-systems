package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.desktop_views.Inicial;
import fourzeta.desktop_views.RankingView;
import fourzeta.models.Circuito;
import fourzeta.models.Ranking;
import fourzeta.models.Usuario;
import fourzeta.resources.CircuitoResource;
import fourzeta.table.RankingTableModel;

public class PesquisarRankingController implements ActionListener {

	private List<Ranking> rankings;
	private Circuito circuito;
	private Usuario usuario;
	private RankingView tela;

	public PesquisarRankingController(Usuario usuario, RankingView tela) {
		this.tela = tela;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source = (JButton) arg0.getSource();
		
		if(source.getName() == "btnVoltar") {
			actionVoltar();
		}else {
			actionPesquisar();
		}
	}
	
	private void actionPesquisar() {
		rankings = new ArrayList<Ranking>();
		CircuitoResource cr = new CircuitoResource();
		for (Circuito c : cr.listaCircuitos()) {
			if (c.getNome().equalsIgnoreCase(tela.getComboCircuito().getSelectedItem().toString())) {
				circuito = c;
			}
		}

		rankings = circuito.getRanksByCategoria(tela.getComboCategoria().getSelectedItem().toString());

		RankingTableModel model = (RankingTableModel) tela.getRankingsTable().getModel();
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
		tela.readTable(rankings);
	}

	private void actionVoltar() {
		Inicial inicial = null;
		try {
			inicial = new Inicial(this.usuario);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tela.setVisible(false);
		inicial.setVisible(true);
	}
	
}
