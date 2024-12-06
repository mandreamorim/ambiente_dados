package GUI.tabela;
import Objects.Livro;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class LivrosTableModel extends AbstractTableModel {
    private final String[] colunas = {"Id", "Titulo", "Autor", "Publicação", "Disponíveis"};
    private final ArrayList<Livro> livros;

    public LivrosTableModel(ArrayList<Livro> d) {
        this.livros = d;
    }

    @Override
    public int getRowCount() {
        return livros.size();
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
        Livro livro = livros.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> livro.id;
            case 1 -> livro.titulo;
            case 2 -> livro.autor;
            case 3 -> livro.ano_publicacao;
            case 4 -> livro.qntd_estoque;
            default -> null;
        };
    }
}
