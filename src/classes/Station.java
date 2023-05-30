import java.util.*;

class Station {
    private String name;
    private String type;

    public Station(String name, String type) {
        this.name = name;
        this.type = type;
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

}