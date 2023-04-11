import java.util.List;
import java.util.LinkedList;

public class Aplicativo {

    private List<Filme> filmes = new LinkedList<Filme>();
    private List<Usuario> usuarios = new LinkedList<Usuario>();

    public void addFilme(Filme filme) {
        filmes.add(filme);
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Filme> buscarFilme(String nome) {
        List<Filme> encontrados = new LinkedList<Filme>();
        for(Filme filme: filmes){
            if(filme.getNome().equals(nome)) encontrados.add(filme);
        }
        return encontrados;

    }

    public static void main(String[] args) {
        
    }


}
