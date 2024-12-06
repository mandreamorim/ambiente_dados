package util;

import Objects.*;
import bd.StatementResultSet;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;

import static bd.DbMySQL.*;
import static java.lang.Integer.parseInt;

public class Util {
    public static ArrayList<Aluno> resultSetIntoAlunosArrayList(ResultSet resultSet){
        ArrayList<Aluno> arrayList = new ArrayList<Aluno>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String data_nasc = resultSet.getString("data_nascimento");
                int matricula = resultSet.getInt("matricula");
                arrayList.add(new Aluno(id, nome, data_nasc, matricula));
            }
        } catch (SQLException e) {
            //TODO
        }
        return arrayList;
    }

    public static ArrayList<Bibliotecario> resultSetIntoBibliotecariosArrayList(ResultSet resultSet){
        ArrayList<Bibliotecario> arrayList = new ArrayList<Bibliotecario>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String data_nasc = resultSet.getString("data_nascimento");
                arrayList.add(new Bibliotecario(nome, id, data_nasc));
            }
        } catch (SQLException e) {
            //TODO
        }
        return arrayList;
    }

    public static ArrayList<Livro> resultSetIntoLivrosArrayList(ResultSet resultSet){
        ArrayList<Livro> arrayList = new ArrayList<Livro>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                int estoque = resultSet.getInt("qntd_estoque");
                int ano = resultSet.getInt("ano_publi");
                arrayList.add(new Livro(id, nome, autor, ano, estoque));
            }
        } catch (SQLException e) {
            //TODO
        }
        return arrayList;
    }

    public static ArrayList<Emprestimo> resultSetIntoLocacoesArrayList(ResultSet resultSet){
        ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int id_aluno = resultSet.getInt("id_aluno");
                int id_livro = resultSet.getInt("id_livro");
                String data_emprestimo = resultSet.getString("data_emprestimo");
                String data_devolucao = resultSet.getString("data_devolucao");
                arrayList.add(new Emprestimo(id, id_aluno, id_livro, data_emprestimo, data_devolucao));
            }
        } catch (SQLException e) {
            //TODO
        }
        return arrayList;
    }

    public static String getStringResultFromResultSet(StatementResultSet in, String campo){
        String str = "";
        try{
            while (in.resultSet.next()){
                str = in.resultSet.getString(campo);
            }
        } catch (SQLException e) {
            //TODO
        }
        in.closeAll();
        return str;
    }

    public static int getAlunoIdFromAlunoName(String nome){
        return parseInt(getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom("aluno",
                                "id",
                                "nome = " + "'" + nome + "'")),"id"));
    }

    public static int getLivroIdFromLivroName(String nome){
        return parseInt(getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom("livro",
                                "id",
                                "titulo = " + "'" + nome + "'")),"id"));
    }

    public static ArrayList<Aluno> getAllAluno(){
        StatementResultSet s = getAllFrom("aluno");

        ArrayList<Aluno> arrayList = resultSetIntoAlunosArrayList(s.resultSet);
        s.closeAll();

        return arrayList;
    }

    public static ArrayList<Livro> getAllLivros(){
        StatementResultSet s = getAllFrom("livro");

        ArrayList<Livro> arrayList = resultSetIntoLivrosArrayList(s.resultSet);
        s.closeAll();

        return arrayList;
    }

    public static ArrayList<Emprestimo> getAllLocacoes(){
        StatementResultSet s = getAllFrom("emprestimo");

        ArrayList<Emprestimo> arrayList = resultSetIntoLocacoesArrayList(s.resultSet);
        s.closeAll();

        return arrayList;
    }

    public static ArrayList<Bibliotecario> getAllBibliotecaios(){
        StatementResultSet s = getAllFrom("bibliotecario");

        ArrayList<Bibliotecario> arrayList = resultSetIntoBibliotecariosArrayList(s.resultSet);
        s.closeAll();

        return arrayList;
    }

    public static String getBibliotecarioNameFromId(int id){
        return getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom("bibliotecario",
                                "nome",
                                "id = " + id)),
                "nome");
    }

    public static String getLivroNameFromId(int id){
        return getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom("livro",
                                "titulo",
                                "id = " + id)),
                "titulo");
    }

    public static String getAlunoNameFromId(int id){
        return getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom("aluno",
                                "nome",
                                "id = " + id)),
                "nome");
    }

    public static AbstractButton getSelectedButton(ButtonGroup group){
        ButtonModel selectedModel = group.getSelection(); // Get the selected model
        if (selectedModel != null) {
            Enumeration<AbstractButton> buttons = group.getElements(); // Get all buttons
            while (buttons.hasMoreElements()) {
                AbstractButton button = buttons.nextElement();
                if (button.getModel() == selectedModel) {
                    return button;
                }
            }
        }
        return null;
    }
}
