package fourzeta.table;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import fourzeta.models.Ranking;
import fourzeta.resources.RankingResource;

public class RankingTableModel extends AbstractTableModel {

	private List<Ranking> dados = new ArrayList();
	private String[] colunas = { "NOME", "CPF", "PONTUAÇÃO" };
	private RankingResource rr;

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public int getColumnCount() { // Colunas
		// Quantidade de colunas
		return colunas.length;
	}

	@Override
	public int getRowCount() { // Linha
		// Tamanho da lista de Dados
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return dados.get(linha).getAtleta().getNome();
		case 1:
			return dados.get(linha).getAtleta().getCpf();
		case 2:
			return dados.get(linha).getPontos();

		}
		return null;
	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		switch (coluna) {
		case 1:
			dados.get(linha).getAtleta().setNome((String) valor);
			break;
		case 2:
			dados.get(linha).getAtleta().setCpf((String) valor);
			break;
		case 3:
			dados.get(linha).setPontos((Integer) valor);

		}
		this.fireTableRowsUpdated(linha, linha);
	}

	public int getRankingAtRow(int linha, int coluna) {
		Ranking ranking = new Ranking();
		ranking.setId(dados.get(linha).getId());
		if (dados.get(linha).getId() != 0) {
			return ranking.getId();
		}
		return 0;
	}

	public Ranking getRanking(int linha, int coluna) throws RemoteException, MalformedURLException, NotBoundException {
		Ranking ranking = new Ranking();
		rr = new RankingResource();
		ranking.setId(dados.get(linha).getId());
		for (Ranking r : rr.listaRankings()) {
			if (r.getId() == ranking.getId()) {
				return r;
			}
		}

		return null;
	}

	public void addRow(Ranking ranking) {
		this.dados.add(ranking);
		this.fireTableDataChanged();

	}

	public void removeRow(int linha) {
		this.dados.remove(linha);

		// dao.remove(); // Removendo utilizando m�todo da DAO
		this.fireTableRowsDeleted(linha, linha);

	}

}
