package fourzeta.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fourzeta.models.Jogo;
import fourzeta.resources.JogoResource;


public class JogosTableModel extends AbstractTableModel {

	private List<Jogo> dados = new ArrayList();
	private String[] colunas = { "PARTIDA" };
	private JogoResource jr;

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
			return dados.get(linha).getPartida();

		}
		return null;
	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		switch (coluna) {
		case 1:
			dados.get(linha).setPartida(((String) valor));
			break;

		}
		this.fireTableRowsUpdated(linha, linha);
	}

	public Jogo getJogo(int linha, int coluna) {
		Jogo jogo = new Jogo();
		jr = new JogoResource();
		jogo.setId(dados.get(linha).getId());
		for (Jogo j : jr.listaJogos()) {
			if (j.getId() == jogo.getId()) {
				return j;
			}
		}

		return null;
	}

	public void addRow(Jogo jogo) {
		this.dados.add(jogo);
		this.fireTableDataChanged();

	}

	public void removeRow(int linha) {
		this.dados.remove(linha);

		// dao.remove(); // Removendo utilizando m�todo da DAO
		this.fireTableRowsDeleted(linha, linha);

	}

}
