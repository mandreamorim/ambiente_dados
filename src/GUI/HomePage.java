package GUI;

import Objects.Aluno;
import Objects.Livro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

import static util.Table.populateTable;
import static util.Util.*;
import static bd.DbMySQL.*;

public class HomePage extends Nimbus {

    private JPanel homePage;
    private JTable table1;
    private JButton locacoes;
    private JButton alunos;
    private JButton livros;
    private JButton bibliotecario;
    private JComboBox comboBox1;
    private JScrollPane scroll;
    private JScrollPane buttons;
    private JComboBox comboBox2;
    private JButton devolverButton;
    private JButton alugarButton;

    public HomePage() {
        setContentPane(homePage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Biblioteca do professor maluco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width * 8 / 10, screenSize.height * 8 / 10);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        for(Aluno aluno: getAllAluno()){
            comboBox2.addItem(aluno.nome);
        }

        for(Livro livro: getAllLivros()){
            comboBox1.addItem(livro.titulo);
        }

        locacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableAllLocacoes();
            }
        });
        livros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableAllLivros();
            }
        });
        bibliotecario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableAllBibliotecarios();
            }
        });
        alunos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableAllAlunos();
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAluno = Objects.requireNonNull(comboBox2.getSelectedItem()).toString();
                int id_aluno = getAlunoIdFromAlunoName(selectedAluno);
                ResultSet response = getAllCond("emprestimo", "id_aluno = " + id_aluno).resultSet;
                populateTable(scroll, resultSetIntoLocacoesArrayList(response));
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLivro = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();
                int id_livro = getLivroIdFromLivroName(selectedLivro);
                ResultSet response = getAllCond("emprestimo", "id_livro = " + id_livro).resultSet;
                populateTable(scroll, resultSetIntoLocacoesArrayList(response));
            }
        });
        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        DevolverLivro devolver = new DevolverLivro();
                    }
                });
            }
        });
        alugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ReservarLivro reservar = new ReservarLivro();
                    }
                });
            }
        });
    }

    private void tableAllLocacoes(){
        populateTable(scroll, getAllLocacoes());
    }

    private void tableAllBibliotecarios(){
        populateTable(scroll, getAllBibliotecaios());
    }

    private void tableAllLivros(){
        populateTable(scroll, getAllLivros());
    }

    private void tableAllAlunos(){
        populateTable(scroll, getAllAluno());
    }
}
