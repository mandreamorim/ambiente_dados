package bd;

import Objects.Disciplina;
import Objects.Nota;
import user.UserSession;

import java.sql.*;
import java.util.ArrayList;

import static util.Util.resultSetIntoDisciplinasArrayList;
import static util.Util.resultSetIntoNotasArrayList;

public class DbMySQL {
    static String url = "jdbc:mysql://localhost:3306/estrutura_de_dados_q1";
    static String user = "root"; // Seu nome de usuário
    static String password = "1234"; // Sua senha
    static Connection connection = null;

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

        } catch (SQLException ignored) {
            //TODO
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

    public static ArrayList<Nota> getNotasFromDisciplina(){

        StatementResultSet result = DbMySQL.getAllFrom("notas");

        ArrayList<Nota> notasList = resultSetIntoNotasArrayList(result.resultSet);

        result.closeAll();
        return notasList;
    }

    public static ArrayList<Nota> getNotasFromDisciplina(Disciplina d){
        //TODO
        String cond = "id_disciplina = " + d.id;
        StatementResultSet result = DbMySQL.getAllCond("notas", cond);

        ArrayList<Nota> notasList = resultSetIntoNotasArrayList(result.resultSet);

        result.closeAll();
        return notasList;
    }

    public static ArrayList<Disciplina> getDisciplines(){
        if(UserSession.availableDisciplines == null){
            StatementResultSet result = DbMySQL.getAllFrom("disciplina");

            ArrayList<Disciplina>disciplinesList = resultSetIntoDisciplinasArrayList(result.resultSet);
            UserSession.saveAvailableDisciplines(disciplinesList);

            result.closeAll();
        }
        return UserSession.availableDisciplines;
    }
}
