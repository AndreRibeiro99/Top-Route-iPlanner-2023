import java.util.*;

class User {
    private String nome;
    private String preferencia;
    private List<String> historico;

    public User(String nome, String preferencia) {
        this.nome = nome;
        this.preferencia = preferencia;
        this.historico = new ArrayList<>();
    }

    public void adicionarViagem(String viagem) {
        historico.add(viagem);
    }

}