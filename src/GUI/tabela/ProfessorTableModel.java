package GUI.tabela;

import Objects.Disciplina;
import Objects.Professor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static util.Util.getProfessorNameFromId;

public class ProfessorTableModel extends AbstractTableModel {
    private final String[] colunas = {"ID do professor", "Nome do professor"};
    private final ArrayList<Professor> professores;

    public ProfessorTableModel(ArrayList<Professor> d) {
        this.professores = d;
    }

    @Override
    public int getRowCount() {
        return professores.size();
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
        Professor professor = professores.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> professor.id;
            case 1 -> professor.nome_professor;
            default -> null;
        };
    }
}
