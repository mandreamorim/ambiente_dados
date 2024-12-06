package Objects;

public class Emprestimo {
    public int id_aluno;
    public int id;
    public int id_livro;
    public String data_emprestimo;
    public String data_devolucao;

    public Emprestimo(int id, int id_aluno, int id_livro, String emprestimo, String devolucao) {
        this.data_devolucao = devolucao;
        this.data_emprestimo = emprestimo;
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_livro = id_livro;
    }
}
