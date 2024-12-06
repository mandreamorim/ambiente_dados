package GUI.tabela;

import Objects.Emprestimo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static util.Util.*;

public class EmprestimosTableModel extends AbstractTableModel {
    private final String[] colunas = {"Id", "Aluno", "Livro", "Data de locação", "Data de devolução"};
    private final ArrayList<Emprestimo> emprestimos;

    public EmprestimosTableModel(ArrayList<Emprestimo> d) {
        this.emprestimos = d;
    }

    @Override
    public int getRowCount() {
        return emprestimos.size();
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
        Emprestimo emp = emprestimos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> emp.id;
            case 1 -> getAlunoNameFromId(emp.id_aluno);
            case 2 -> getLivroNameFromId(emp.id_livro);
            case 3 -> emp.data_emprestimo;
            case 4 -> emp.data_devolucao;
            default -> null;
        };
    }
}
