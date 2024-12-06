package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static bd.DbMySQL.*;

public class LoginPage extends Nimbus {
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JPanel panel;

    public LoginPage() {
        setContentPane(panel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width/3, screenSize.height/3);
        setLocationRelativeTo(null);
        setResizable(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginHandler();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }

    private void loginHandler() throws SQLException {
        String pswd = new String(password.getPassword()).trim();
        String user = username.getText().trim();

        if (!checkInvalidPasswordInput(pswd)) {
            //TODO
            JOptionPane.showMessageDialog(this, "Invalid password");
            return;
        }

        if(!checkLoginData(user, pswd)) {
            //TODO
            JOptionPane.showMessageDialog(this, "Invalid username or password");
            return;
        }

        dispose();

        callMenu();
    }

    private void callMenu(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HomePage home = new HomePage();
            }
        });
    }

    private boolean checkLoginData(String user, String pswd) throws SQLException {
        ResultSet response = getAllCond("login", "usuario = '" + user + "' and senha = '" + pswd + "'").resultSet;
        return response.next();
    }

    private boolean checkInvalidPasswordInput(String pswd) {
        return pswd.matches("[a-zA-Z0-9]{1,16}");
    }
}
