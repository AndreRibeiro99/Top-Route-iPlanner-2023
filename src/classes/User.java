package classes;

import algs4.RedBlackBST;

import java.io.Serializable;
import java.util.*;

class User implements Serializable {
    private int id;
    private String nome;
    private String preferencia;

    private RedBlackBST<Date, Connection> historico;
    private RedBlackBST<Date, Viagem> viagens;

    public User(Integer id, String nome, String preferencia) {
        this.id = id;
        this.nome = nome;
        this.preferencia = preferencia;
        this.historico = new RedBlackBST<>();
        this.viagens = new RedBlackBST<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public void addHistorico(Date data, Connection conexao) {
        historico.put(data, conexao);
    }

    public void setHistorico(RedBlackBST<Date, Connection> historico) {
        this.historico = historico;
    }


    public RedBlackBST<Date, Connection> getHistorico() {
        return historico;
    }
}