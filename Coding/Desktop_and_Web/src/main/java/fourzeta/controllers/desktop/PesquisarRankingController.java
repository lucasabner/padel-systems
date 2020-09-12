package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import fourzeta.desktop_views.RankingView;
import fourzeta.models.Circuito;
import fourzeta.models.Ranking;
import fourzeta.resources.CircuitoResource;
import fourzeta.table.RankingTableModel;

public class PesquisarRankingController implements ActionListener {

	private List<Ranking> rankings;
	private Circuito circuito;
	private RankingView tela;

	public PesquisarRankingController(RankingView tela) {
		this.tela = tela;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
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

}
