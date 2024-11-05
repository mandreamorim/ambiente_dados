package GUI.tabela;

import Objects.Nota;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class NotaTableModel extends AbstractTableModel {

    private final String[] colunas = {"ID Aluno", "ID Disciplina", "Nota", "Data"};
    private final ArrayList<Nota> notas;

    public NotaTableModel(ArrayList<Nota> notas) {
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
        Nota nota = notas.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> nota.id_aluno;
            case 1 -> nota.id_disciplina;
            case 2 -> nota.nota;
            case 3 -> nota.data;
            default -> null;
        };
    }

}
