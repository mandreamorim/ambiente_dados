package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static bd.DbMySQL.devolverLocacao;
import static bd.DbMySQL.registerLocacao;

public class DevolverLivro extends Nimbus{
    private JTextField idLivro;
    private JButton reservarButton;
    private JPanel panel;

    public DevolverLivro(){
        setContentPane(panel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Devolver livro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(screenSize.width/3, screenSize.height/3);
        setLocationRelativeTo(null);
        setResizable(false);

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_livro = Integer.parseInt(idLivro.getText().trim());

                devolverLocacao(id_livro);
            }
        });

        setVisible(true);
    }
}
