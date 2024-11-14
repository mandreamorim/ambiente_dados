package util;

import GUI.tabela.DisciplinasTableModel;
import GUI.tabela.NotaTableModel;
import GUI.tabela.ProfessorTableModel;
import Objects.Disciplina;
import Objects.Nota;
import Objects.Professor;

import javax.swing.*;
import java.util.ArrayList;

public class Table {
    public static void populateTable(JScrollPane scroll, ArrayList<?> lista){
        JTable pointer;
        if (lista.getFirst() instanceof Nota) {
            pointer = new JTable(new NotaTableModel((ArrayList<Nota>) lista));
        } else if (lista.getFirst() instanceof Disciplina) {
            pointer = new JTable(new DisciplinasTableModel((ArrayList<Disciplina>) lista));
        } else if (lista.getFirst() instanceof Professor) {
            pointer = new JTable(new ProfessorTableModel((ArrayList<Professor>) lista));
        } else {
            throw new IllegalArgumentException("Tipo não suportado");
        }

        pointer.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(pointer);
    }
}
