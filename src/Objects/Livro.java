package Objects;

public class Livro {
    public int id;
    public String titulo;
    public String autor;
    public int ano_publicacao;
    public int qntd_estoque;

    public Livro(int id_livro, String titulo, String autor, int ano_publicacao, int qntd_estoque) {
        this.id = id_livro;
        this.titulo = titulo;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
        this.qntd_estoque = qntd_estoque;
    }
}
