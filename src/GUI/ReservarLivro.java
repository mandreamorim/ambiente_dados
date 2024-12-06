package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static bd.DbMySQL.*;

public class ReservarLivro extends Nimbus{
    private JTextField idAluno;
    private JTextField idLivro;
    private JButton reservarButton;
    private JPanel panel;

    public ReservarLivro(){
        setContentPane(panel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Reservar livro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(screenSize.width/3, screenSize.height/3);
        setLocationRelativeTo(null);
        setResizable(false);

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_aluno = Integer.parseInt(idAluno.getText().trim());
                int id_livro = Integer.parseInt(idLivro.getText().trim());

                try {
                    registerLocacao(id_livro, id_aluno);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }
}
