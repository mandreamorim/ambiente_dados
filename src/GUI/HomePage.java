package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static util.Table.populateTable;
import static util.Util.*;

public class HomePage extends JFrame {

    private JPanel homePage;
    private JTable table1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JComboBox comboBox1;
    private JScrollPane scroll;

    public HomePage() {
        setContentPane(homePage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Sistema de notas do professor maluco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width * 8 / 10, screenSize.height * 8 / 10);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
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
}
