package fourzeta.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import fourzeta.IElement;
import fourzeta.models.Chave;
import fourzeta.models.Jogo;
import fourzeta.models.Torneio;
import fourzeta.resources.JogoResource;

public class GradeJogosTableModel extends AbstractTableModel {

	private List<Jogo> dados = new ArrayList();
	private String[] colunas = { "HORÁRIO", "CATEGORIA", "CONFRONTO", "PLACAR", "ETAPA" };
	private JogoResource jr;
	private Torneio torneio;

	public GradeJogosTableModel(Torneio torneio) {
		this.torneio = torneio;
	}

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
		String categoria = null;
		jr = new JogoResource();
		List<Jogo> jogos = new ArrayList<Jogo>();
		System.out.println(torneio.getNome());

		for (IElement element : torneio.getChaves()) {
			Chave c = (Chave) element;
			for (Jogo j : c.getJogos()) {
				if (dados.get(linha).getId() == j.getId()) {
					categoria = c.getCategoria().toString();
				}
			}
		}
		switch (coluna) {
		case 0:
			return dados.get(linha).getData();
		case 1:
			return dados.get(linha).getCategoria();
		case 2:
			return dados.get(linha).getDuplas();
		case 3:
			return dados.get(linha).getPlacar();
		case 4:
			return dados.get(linha).getEtapa();
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

		// dao.remove(); // Removendo utilizando metodo da DAO
		this.fireTableRowsDeleted(linha, linha);

	}

}
