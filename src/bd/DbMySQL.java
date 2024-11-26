package bd;

import user.UserSession;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class DbMySQL {
    static String url = "jdbc:mysql://localhost:3306/estrutura_de_dados_q1";
    static String user = "root"; // Seu nome de usuário
    static String password = "1234"; // Sua senha
    static Connection connection;

    public static void main(String[] args) {
        try {
            Connection con = getConnection();


            StatementResultSet statementResultSet = getAllFrom("logins");

            // Processa o resultado da consulta
            while (statementResultSet.resultSet.next()) {
                System.out.println("Dado: " + statementResultSet.resultSet.getString("senha"));
            }

            // Fecha recursos
            statementResultSet.closeAll();
            closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if(connection == null) {
            try{
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (SQLException e) {
                //TODO
                //JOptionPane.showMessageDialog(, "Invalid password");
                return null;
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try{
            if (connection != null) {
                connection.close();
                System.out.println("Conexão encerrada");
            }
        } catch (SQLException ignored) {}
    }

    public static StatementResultSet getAllFrom(String table) {
        Connection con = getConnection();
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + table;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return new StatementResultSet(resultSet, statement);

        } catch (SQLException ignored) {
            //TODO
        }

        return null;
    }

    public static StatementResultSet getFieldFrom(String table, String field, String cond) {
        //TODO
        Connection con = getConnection();
        ResultSet resultSet = null;
        String query = "SELECT " + field + " FROM " + table + " WHERE " + cond;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return new StatementResultSet(resultSet, statement);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static StatementResultSet getAllCond(String table, String cond) {
        //TODO
        Connection con = getConnection();
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + table + " WHERE " + cond;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return new StatementResultSet(resultSet, statement);

        } catch (SQLException ignored) {
            //TODO
        }

        return null;
    }

    public static boolean registerNewNota(int id_aluno, int id_disciplina, double nota, int tipo){
        String query = "INSERT INTO notas(id_login, id_aluno, id_disciplina, nota, data_avaliacao, tipo) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, UserSession.loginId);
            stmt.setInt(2, id_aluno);
            stmt.setInt(3, id_disciplina);
            stmt.setDouble(4, nota);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.setInt(6, tipo);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean registerNewNota(JPanel jpanel, int id_aluno, int id_disciplina, double nota, int tipo){
        String query = "INSERT INTO notas(id_login, id_aluno, id_disciplina, nota, data_avaliacao, tipo) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, UserSession.loginId);
            stmt.setInt(2, id_aluno);
            stmt.setInt(3, id_disciplina);
            stmt.setDouble(4, nota);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.setInt(6, tipo);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jpanel, "Erro: " + e.getMessage());
            return false;
        }
    }

    public static boolean editNota(int id_aluno, int id_disciplina, double nota, int tipo){
        String query = "update notas set nota = ?, id_login = ? where tipo = ? and id_disciplina = ? and id_aluno = ?";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, nota);
            stmt.setInt(2, UserSession.loginId);
            stmt.setInt(3, tipo);
            stmt.setInt(4, id_disciplina);
            stmt.setInt(5, id_aluno);

            int rowsEdited = stmt.executeUpdate();
            return rowsEdited > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean editNota(JPanel jpanel, int id_aluno, int id_disciplina, double nota, int tipo){
        String query = "update notas set nota = ?, id_login = ? where tipo = ? and id_disciplina = ? and id_aluno = ?";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, nota);
            stmt.setInt(2, UserSession.loginId);
            stmt.setInt(3, tipo);
            stmt.setInt(4, id_disciplina);
            stmt.setInt(5, id_aluno);

            int rowsEdited = stmt.executeUpdate();
            return rowsEdited > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(jpanel, "Erro: " + e.getMessage());
            return false;
        }
    }
}
