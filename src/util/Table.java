package util;

import GUI.tabela.*;
import Objects.Aluno;
import Objects.Disciplina;
import Objects.Nota;
import Objects.Professor;

import javax.swing.*;
import java.util.ArrayList;

public class Table {
    public static void populateTable(JScrollPane scroll, ArrayList<?> lista){
        JTable pointer;
        try{
            if (lista.getFirst() instanceof Nota) {
                pointer = new JTable(new NotaTableModel((ArrayList<Nota>) lista));
            } else if (lista.getFirst() instanceof Disciplina) {
                pointer = new JTable(new DisciplinasTableModel((ArrayList<Disciplina>) lista));
            } else if (lista.getFirst() instanceof Professor) {
                pointer = new JTable(new ProfessorTableModel((ArrayList<Professor>) lista));
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
