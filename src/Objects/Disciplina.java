package Objects;

public class Disciplina {
    public String nome;
    public int id;
    public int id_professor;

    public Disciplina(int id, int id_professor, String nome) {
        this.id = id;
        this.id_professor = id_professor;
        this.nome = nome;
    }
}
