package user;

import Objects.Disciplina;

import java.util.ArrayList;

public class UserSession {
     public static int loginId = -1;
     public static ArrayList<Disciplina> availableDisciplines;

     static public void saveLoginId(int loginId) {
         UserSession.loginId = loginId;
     }

     static public void saveAvailableDisciplines(ArrayList<Disciplina> a) {
         availableDisciplines = a;
     }
}
