package classes;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Viagem implements Serializable {
    private int id;
    private int n_connections;
    private List<Connection> conexoes;

    public Viagem(Integer id) {
        this.id = id;
        this.n_connections = 0;
        this.conexoes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getN_connections() {
        return n_connections;
    }

    public void setN_connections(int n_connections) {
        this.n_connections = n_connections;
    }

    public List<Connection> getConexoes() {
        return conexoes;
    }

    public void setConexoes(List<Connection> conexoes) {
        this.conexoes = conexoes;
    }

    @Override
    public String toString() {
        return "Viagem(id): " + id + '\'' +
                " nConexoes: " + n_connections ;
    }
}