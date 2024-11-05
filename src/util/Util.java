package util;

import Objects.Disciplina;
import Objects.Nota;
import bd.StatementResultSet;

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

    public static String getProfessorNameFromId(int id){
        return getProfessorNameFromResultSet(
                Objects.requireNonNull(
                        getFieldFrom(
                                "professor",
                                "nome_professor",
                                "id = " + id)));
    }

    public static String getProfessorNameFromResultSet(StatementResultSet in){
        String str = "";
        try{
            while (in.resultSet.next()){
                str = in.resultSet.getString("nome_professor");
            }
        } catch (SQLException e) {
            //TODO
        }
        return str;
    }
}
