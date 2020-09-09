package fourzeta.table;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fourzeta.models.Chave;
import fourzeta.resources.ChaveResource;

public class ChavesTableModel extends AbstractTableModel {

	private List<Chave> dados = new ArrayList();
	private String[] colunas = { "DUPLA 1 ", "DUPLA 2", "DUPLA 3" };
	private ChaveResource cr;

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
			return dados.get(linha).getDupla1().toString();
		case 1:
			return dados.get(linha).getDupla2().toString();
		case 2:
			return dados.get(linha).getDupla3().toString();

		}
		return null;
	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		switch (coluna) {
		case 0:
			dados.get(linha).getDupla1().toString();
			break;
		case 1:
			dados.get(linha).getDupla2().toString();
			break;
		case 2:
			dados.get(linha).getDupla3().toString();
			break;

		}
		this.fireTableRowsUpdated(linha, linha);
	}

	public int getChaveAtRow(int linha, int coluna) {
		Chave chave = new Chave();
		chave.setId(dados.get(linha).getId());
		if (dados.get(linha).getId() != 0) {
			return chave.getId();
		}
		return 0;
	}

	public Chave getChave(int linha, int coluna) throws RemoteException, MalformedURLException, NotBoundException {
		Chave chave = new Chave();
		cr = new ChaveResource();
		chave.setId(dados.get(linha).getId());
		for (Chave c : cr.listaChaves()) {
			if (c.getId() == chave.getId()) {
				return c;
			}
		}

		return null;
	}

	public void addRow(Chave chave) {
		this.dados.add(chave);
		this.fireTableDataChanged();

	}

	public void removeRow(int linha) {
		this.dados.remove(linha);

		// dao.remove(); // Removendo utilizando m�todo da DAO
		this.fireTableRowsDeleted(linha, linha);

	}

}
