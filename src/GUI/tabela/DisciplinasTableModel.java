package GUI.tabela;

import Objects.Disciplina;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import static util.Util.getProfessorNameFromId;

public class DisciplinasTableModel extends AbstractTableModel {
    private final String[] colunas = {"Nome do professor responsável", "Nome da disciplina"};
    private final ArrayList<Disciplina> disciplinas;

    public DisciplinasTableModel(ArrayList<Disciplina> d) {
        this.disciplinas = d;
    }

    @Override
    public int getRowCount() {
        return disciplinas.size();
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
        Disciplina disciplina = disciplinas.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> getProfessorNameFromId(disciplina.id_professor);
            case 1 -> disciplina.nome;
            default -> null;
        };
    }
}
