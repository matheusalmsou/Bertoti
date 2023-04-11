public class Filme {
    
    private String nome;
    private int duracao;
    private String autor;

    public Filme(String nome, int duracao, String autor) {
        this.nome = nome;
        this.duracao = duracao;
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
