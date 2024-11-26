package GUI;

import bd.DbMySQL;
import user.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
        setVisible(true);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginHandler();
//                callMenu();
            }
        });
    }

    private void loginHandler(){
        String pswd = new String(password.getPassword()).trim();
        String user = username.getText().trim();

        if (!checkInvalidPasswordInput(pswd)) {
            //TODO
            JOptionPane.showMessageDialog(this, "Invalid password");
            return;
        }

        if(!checkLoginData(user, pswd)) {
            //TODO
            UserSession.saveLoginId(-1);
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

    private boolean checkLoginData(String user, String pswd) {
        String query = "SELECT id FROM logins WHERE username = ? AND senha = ?";

        try{
            Connection connection = DbMySQL.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user);
            statement.setString(2, pswd);


            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    UserSession.saveLoginId(resultSet.getInt("id"));
                    return true;
                }
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return false;
        }
    }

    private boolean checkInvalidPasswordInput(String pswd) {
        return pswd.matches("[a-zA-Z0-9]{1,16}");
    }
}
