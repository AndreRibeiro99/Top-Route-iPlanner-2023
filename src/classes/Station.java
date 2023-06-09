package classes;

import algs4.In;

import java.util.*;

class Station implements Comparable<Station> {
    private int id;
    private String name;
    private int x;
    private int y;
    private Integer aviao;
    private Integer barco;
    private Integer metro;
    private Integer taxi;


    public Station(Integer id, String name, Integer x, Integer y,Integer a, Integer b, Integer m, Integer t) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.aviao = a;
        this.barco = b;
        this.metro = m;
        this.taxi = t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Integer getAviao() {
        return aviao;
    }

    public Integer getBarco() {
        return barco;
    }

    public Integer getMetro() {
        return metro;
    }

    public Integer getTaxi() {
        return taxi;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Station o) {
        return 0;
    }
}