package GUI.tabela;

import Objects.Aluno;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AlunoTableModel extends AbstractTableModel {
    private final String[] colunas = {"ID do aluno","Nome do aluno", "Data de nascimento"};
    private final ArrayList<Aluno> alunos;

    public AlunoTableModel(ArrayList<Aluno> d) {
        this.alunos = d;
    }

    @Override
    public int getRowCount() {
        return alunos.size();
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
        Aluno aluno = alunos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> aluno.id;
            case 1 -> aluno.nome;
            case 2 -> aluno.data_nasc;
            default -> null;
        };
    }


}
