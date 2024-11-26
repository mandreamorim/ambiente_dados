package GUI.tabela;

import Objects.Aluno;

import javax.swing.table.AbstractTableModel;

public class NoContentTableModel extends AbstractTableModel {

    public NoContentTableModel() {
        ;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
