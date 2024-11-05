package Objects;

public class Nota {
    public int id;
    public int id_login;
    public int id_aluno;
    public int id_disciplina;
    public short nota;
    public String data;

    public Nota(int id, int id_login, int id_aluno, int id_disciplina, short nota, String data) {
        this.id = id;
        this.id_login = id_login;
        this.id_aluno = id_aluno;
        this.id_disciplina = id_disciplina;
        this.nota = nota;
        this.data = data;
    }
}
