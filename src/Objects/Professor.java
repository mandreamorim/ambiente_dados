package Objects;

public class Professor {
    public String nome_professor;
    public int id;
    public int id_login;

    public Professor(int id, int id_login, String nome) {
        this.id = id;
        this.id_login = id_login;
        this.nome_professor = nome;
    }
}
