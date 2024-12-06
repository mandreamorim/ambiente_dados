package bd;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DbMySQL {
    static String url = "jdbc:mysql://localhost:3306/quest3";
    static String user = "root"; // Seu nome de usuário
    static String password = "Kopf08091965."; // Sua senha
    static Connection connection;

    public static Connection getConnection() {
        if(connection == null) {
            try{
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (SQLException e) {
                e.printStackTrace(); // Imprime o erro no console
                JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
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

        try {
            PreparedStatement statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();

            return new StatementResultSet(resultSet, statement);
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void registerLocacao(int id_livro, int id_aluno) throws SQLException {
        Connection con = getConnection();
        con.setAutoCommit(false);

        String query = "INSERT INTO emprestimo (id_aluno, id_livro, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        String queryLivrosDisp = "select qntd_estoque from livro where id = ?";
        String queryAtualizarLivrosDisp = "update livro set qntd_estoque = (qntd_estoque - 1) where id = ? ";

        try {
            LocalDate hoje = LocalDate.now();
            LocalDate dataEntrega = hoje.plusDays(30);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataEmprestimoStr = hoje.format(formatter);
            String dataDevolucaoStr = dataEntrega.format(formatter);

            PreparedStatement statement = con.prepareStatement(queryLivrosDisp);
            statement.setInt(1, id_livro);
            int quantidade;

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                quantidade = result.getInt("qntd_estoque");
            } else {
                throw new SQLException("Livro não encontrado");
            }
            if (quantidade < 1){
                throw new SQLException("Livros insuficientes");
            }
            statement.close();

            PreparedStatement locacaoStatement = con.prepareStatement(query);
            locacaoStatement.setInt(1, id_aluno);
            locacaoStatement.setInt(2, id_livro);
            locacaoStatement.setString(3, dataEmprestimoStr);
            locacaoStatement.setString(4, dataDevolucaoStr);
            locacaoStatement.executeUpdate();
            con.commit();
            locacaoStatement.close();

            PreparedStatement atualizarLivrosDisp = con.prepareStatement(queryAtualizarLivrosDisp);
            atualizarLivrosDisp.setInt(1, id_livro);
            int rowsUpdated = atualizarLivrosDisp.executeUpdate();
            con.commit();

            statement.close();
            JOptionPane.showMessageDialog(null, "Registrado", "Tudo certo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado! Talvez livros insuficientes", "Alerta", JOptionPane.WARNING_MESSAGE);
            try {
                if (con != null) con.rollback();
            } catch (SQLException rollbackEx) {
                System.err.println("Erro ao reverter transação: " + rollbackEx.getMessage());
            }
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static void criarTabelaCidade(){
        String query = "create table cidade(estado String, pais String, idade int)";

        try {
            Connection con = getConnection();
            assert con != null;
            PreparedStatement prep = con.prepareStatement(query);
            prep.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela");
        }
    }

    public static void main(String[] args) {
        criarTabelaCidade();
    }

    public static void devolverLocacao(int id_locacao){
        Connection con = getConnection();

        String obterLivroQuery = "select id_livro from emprestimo where id = ?";
        String atualizarQuantidadeQuery = "update livro set qntd_estoque = (qntd_estoque + 1) where id = ?";
        String removerLocacaoQuery = "delete from emprestimo where id = ?";

        try {
            con.setAutoCommit(false);

            PreparedStatement obterLivroStmt = con.prepareStatement(obterLivroQuery);
            obterLivroStmt.setInt(1, id_locacao);
            ResultSet rs = obterLivroStmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Locação não encontrada para o ID: " + id_locacao);
            }

            int id_livro = rs.getInt("id_livro");

            PreparedStatement atualizarQuantidadeStmt = con.prepareStatement(atualizarQuantidadeQuery);
            atualizarQuantidadeStmt.setInt(1, id_livro);
            atualizarQuantidadeStmt.executeUpdate();

            PreparedStatement removerLocacaoStmt = con.prepareStatement(removerLocacaoQuery);
            removerLocacaoStmt.setInt(1, id_locacao);
            removerLocacaoStmt.executeUpdate();

            // Confirmar transação
            con.commit();

            // Fechar Statements
            obterLivroStmt.close();
            atualizarQuantidadeStmt.close();
            removerLocacaoStmt.close();

            JOptionPane.showMessageDialog(null, "Devolvido", "Tudo certo", JOptionPane.INFORMATION_MESSAGE);


        } catch (SQLException e) {
            System.err.println("Erro ao processar devolução: " + e.getMessage());
            e.printStackTrace();

            // Reverter transação em caso de erro
            try {
                if (con != null) con.rollback();
            } catch (SQLException rollbackEx) {
                System.err.println("Erro ao reverter transação: " + rollbackEx.getMessage());
            }

            JOptionPane.showMessageDialog(null, "Erro inesperado!", "Alerta", JOptionPane.WARNING_MESSAGE);


        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println("Erro ao restaurar auto-commit: " + ex.getMessage());
            }
        }
    }
}
