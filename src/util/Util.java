package util;

import Objects.Disciplina;
import Objects.Nota;
import Objects.Professor;
import bd.DbMySQL;
import bd.StatementResultSet;
import user.UserSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static bd.DbMySQL.getFieldFrom;

public class Util {
    public static ArrayList<Nota> resultSetIntoNotasArrayList(ResultSet resultSet){
        ArrayList<Nota> arrayList = new ArrayList<Nota>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int id_l = resultSet.getInt("id_login");
                int id_a = resultSet.getInt("id_aluno");
                int id_d = resultSet.getInt("id_disciplina");
                short nota = resultSet.getShort("nota");
                String data = resultSet.getString("data_avaliacao");
                arrayList.add(new Nota(id, id_l, id_a, id_d, nota, data));
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
}
