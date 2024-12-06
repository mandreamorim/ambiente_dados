package GUI.tabela;

import Objects.Bibliotecario;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static util.Util.getAlunoNameFromId;

public class BibliotecariosTableModel extends AbstractTableModel {

    private final String[] colunas = {"Id", "Nome", "Data de nascimento"};
    private final ArrayList<Bibliotecario> notas;

    public BibliotecariosTableModel(ArrayList<Bibliotecario> notas) {
        this.notas = notas;
    }

    @Override
    public int getRowCount() {
        return notas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bibliotecario bib = notas.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> bib.id;
            case 1 -> bib.nome_bibliotecario;
            case 2 -> bib.data_nascimento;
            default -> null;
        };
    }

}
