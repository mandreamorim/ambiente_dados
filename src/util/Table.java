package util;

import GUI.tabela.*;
import Objects.Aluno;
import Objects.Bibliotecario;
import Objects.Emprestimo;
import Objects.Livro;

import javax.swing.*;
import java.util.ArrayList;

public class Table {
    public static void populateTable(JScrollPane scroll, ArrayList<?> lista){
        JTable pointer;
        try{
            if (lista.getFirst() instanceof Emprestimo) {
                pointer = new JTable(new EmprestimosTableModel((ArrayList<Emprestimo>) lista));
            } else if (lista.getFirst() instanceof Bibliotecario) {
                pointer = new JTable(new BibliotecariosTableModel((ArrayList<Bibliotecario>) lista));
            } else if (lista.getFirst() instanceof Livro) {
                pointer = new JTable(new LivrosTableModel((ArrayList<Livro>) lista));
            } else if (lista.getFirst() instanceof Aluno) {
                pointer = new JTable(new AlunoTableModel((ArrayList<Aluno>) lista));
            } else {
                throw new IllegalArgumentException("Tipo não suportado");
            }
        } catch (Exception e){
            pointer = new JTable(new NoContentTableModel());
        }
        pointer.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(pointer);
    }
}
