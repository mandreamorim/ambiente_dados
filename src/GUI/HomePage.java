package GUI;

import Objects.Aluno;
import Objects.Disciplina;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static util.Table.populateTable;
import static util.Util.*;

public class HomePage extends Nimbus {

    private JPanel homePage;
    private JTable table1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JComboBox comboBox1;
    private JScrollPane scroll;
    private JScrollPane buttons;
    private JComboBox comboBox2;
    private JButton editarNotaButton;
    private JButton adicionarNotaButton;

    public HomePage() {
        setContentPane(homePage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Sistema de notas do professor maluco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width * 8 / 10, screenSize.height * 8 / 10);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        for(Aluno aluno: getAllAluno()){
            comboBox2.addItem(aluno.nome);
        }

        for (Disciplina disciplina: getDisciplines()){
            comboBox1.addItem(disciplina.nome);
        }

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableAllNotas();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableAllDisciplinas();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableAllProfessores();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableAllAlunos();
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAluno = Objects.requireNonNull(comboBox2.getSelectedItem()).toString();
                populateTable(scroll, resultSetIntoNotasArrayList(getNotasFromAlunoId(getAlunoIdFromAlunoName(selectedAluno)).resultSet));
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDisciplina = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();
                populateTable(scroll, getNotasArrayListFromDisciplinaId(getDisciplinaIdFromDisciplinaName(selectedDisciplina)));
            }
        });
        editarNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        EditAluno edit = new EditAluno();
                    }
                });
            }
        });
        adicionarNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AddAluno add = new AddAluno();
                    }
                });
            }
        });
    }

    private void tableAllNotas(){
        populateTable(scroll, getNotasFromDisciplina());
    }

    private void tableAllProfessores(){
        populateTable(scroll, getProfessores());
    }

    private void tableAllDisciplinas(){
        populateTable(scroll, getDisciplines());
    }

    private void tableAllAlunos(){
        populateTable(scroll, getAllAluno());
    }
}
