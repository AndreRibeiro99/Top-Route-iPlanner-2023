package classes;

import java.util.*;

class Station implements Comparable<Station> {
    private int id;
    private String name;
    private String type;

    public Station(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
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