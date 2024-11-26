package util;

import Objects.*;
import bd.DbMySQL;
import bd.StatementResultSet;
import user.UserSession;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;

import static bd.DbMySQL.*;
import static java.lang.Integer.parseInt;

public class Util {
    public static ArrayList<Nota> resultSetIntoNotasArrayList(ResultSet resultSet){
        ArrayList<Nota> arrayList = new ArrayList<Nota>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int id_l = resultSet.getInt("id_login");
                int id_a = resultSet.getInt("id_aluno");
                int id_d = resultSet.getInt("id_disciplina");
                int tipo = resultSet.getInt("tipo");
                short nota = resultSet.getShort("nota");
                String data = resultSet.getString("data_avaliacao");
                arrayList.add(new Nota(id, id_l, id_a, id_d, tipo, nota, data));
            }
        } catch (SQLException e) {
            //TODO
        }
        return arrayList;
    }

    public static ArrayList<Disciplina> resultSetIntoDisciplinasArrayList(ResultSet resultSet){
        ArrayList<Disciplina> arrayList = new ArrayList<Disciplina>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int id_p = resultSet.getInt("id_professor");
                String nome = resultSet.getString("nome_disciplina");
                arrayList.add(new Disciplina(id, id_p, nome));
            }
        } catch (SQLException e) {
            //TODO
        }
        return arrayList;
    }

    public static ArrayList<Professor> resultSetIntoProfessorArrayList(ResultSet resultSet){
        ArrayList<Professor> arrayList = new ArrayList<Professor>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int id_p = resultSet.getInt("id_login");
                String nome = resultSet.getString("nome_professor");
                arrayList.add(new Professor(id, id_p, nome));
            }
        } catch (SQLException e) {
            //TODO
        }
        return arrayList;
    }

    public static ArrayList<Aluno> resultSetIntoAlunosArrayList(ResultSet resultSet){
        ArrayList<Aluno> arrayList = new ArrayList<Aluno>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome_aluno");
                String data_nasc = resultSet.getString("data_nascimento");
                arrayList.add(new Aluno(id, nome, data_nasc));
            }
        } catch (SQLException e) {
            //TODO
        }
        return arrayList;
    }

    public static String getProfessorNameFromId(int id){
        return getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom(
                                "professor",
                                "nome_professor",
                                "id = " + id)), "nome_professor");
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

    public static String getAlunoNameFromId(int id){
        return getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom("aluno",
                            "nome_aluno",
                            "id = " + id)),
                            "nome_aluno");
    }

    public static StatementResultSet getNotasFromAlunoId(int id){
        String cond = "id_aluno = " + id;
        return getAllCond("notas", cond);
    }

    public static int getAlunoIdFromAlunoName(String nome){
        return parseInt(getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom("aluno",
                                "id",
                                "nome_aluno = " + "'" + nome + "'")),"id"));
    }

    public static ArrayList<Aluno> getAllAluno(){
        StatementResultSet s = getAllFrom("aluno");

        ArrayList<Aluno> arrayList = resultSetIntoAlunosArrayList(s.resultSet);
        s.closeAll();

        return arrayList;
    }

    public static String getDisciplineNameFromId(int id){
        return getStringResultFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom("disciplina",
                                "nome_disciplina",
                                "id = " + id)),
                "nome_disciplina");
    }

    public static ArrayList<Nota> getNotasFromDisciplina(){

        StatementResultSet result = DbMySQL.getAllFrom("notas");

        ArrayList<Nota> notasList = resultSetIntoNotasArrayList(result.resultSet);

        result.closeAll();
        return notasList;
    }

    public static int getDisciplinaIdFromDisciplinaName(String nome){
        for(Disciplina d: getDisciplines()){
            if(Objects.equals(d.nome, nome)){
                return d.id;
            }
        }
        return -1;
    }

    public static ArrayList<Nota> getNotasArrayListFromDisciplinaId(int id){
        String cond = "id_disciplina = " + id;
        StatementResultSet result = DbMySQL.getAllCond("notas", cond);

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

    public static ArrayList<Professor> getProfessores(){
        StatementResultSet result = DbMySQL.getAllFrom("professor");

        ArrayList<Professor> professorArrayList = resultSetIntoProfessorArrayList(result.resultSet);

        result.closeAll();
        return professorArrayList;
    }

    public static String translateTipo(int tipo){
        return switch (tipo) {
            case 1 -> "AV1";
            case 2 -> "AV2";
            case 3 -> "AV3";
            default -> "?";
        };
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
