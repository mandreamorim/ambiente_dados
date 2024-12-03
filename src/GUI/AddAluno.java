package GUI;

import Objects.Aluno;
import Objects.Disciplina;
import bd.DbMySQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static bd.DbMySQL.registerNewNota;
import static util.Util.*;

public class AddAluno extends Nimbus {
    private JRadioButton AV1RadioButton;
    private JRadioButton AV2RadioButton;
    private JRadioButton AV3RadioButton;
    private ButtonGroup group = new ButtonGroup();
    private JButton button1;
    private JTextPane notaField;
    private JPanel homePage;
    private JPanel fieldIdAluno;
    private JPanel radioButtonTipoNota;
    private JPanel fieldNewNotaValue;
    private JPanel fieldIdDisciplina;
    private JComboBox comboBox1;
    private JComboBox comboBox2;


    public AddAluno() {
        setContentPane(homePage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Adicionar nota");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(screenSize.width * 5 / 10, screenSize.height * 5 / 10);
        setLocationRelativeTo(null);
        setResizable(false);

        group.add(AV1RadioButton);
        group.add(AV2RadioButton);
        group.add(AV3RadioButton);

        for(Aluno aluno: getAllAluno()){
            comboBox1.addItem(aluno.nome);
        }

        for (Disciplina disciplina: getDisciplines()){
            comboBox2.addItem(disciplina.nome);
        }

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome_disciplina;
                String nome_aluno;
                double nota;

                try{
                    nome_aluno = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();
                    nome_disciplina = Objects.requireNonNull(comboBox2.getSelectedItem()).toString();
                    nota = Double.parseDouble(notaField.getText());
                } catch (Exception e1){
                    JOptionPane.showMessageDialog(homePage, e1.getMessage());
                    return;
                }

                String type_nota;
                try{
                     type_nota = getSelectedButton(group).getText();
                } catch (Exception ignored){
                    JOptionPane.showMessageDialog(homePage, "No AV option selected.");
                    return;
                }
                int type = switch (type_nota){
                    case "AV1" -> 1;
                    case "AV2" -> 2;
                    case "AV3" -> 3;
                    default -> 0;
                };
                int id_aluno = getAlunoIdFromAlunoName(nome_aluno);
                int id_disciplina = getDisciplinaIdFromDisciplinaName(nome_disciplina);

                if(DbMySQL.registerNewNota(id_aluno, id_disciplina, nota, type)){
                    JOptionPane.showMessageDialog(homePage, "Nova nota adicionada");
                }
            }
        });
        setVisible(true);
    }
}
