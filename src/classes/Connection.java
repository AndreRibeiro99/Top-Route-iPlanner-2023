class Connection {
    private Station source;
    private Station destination;
    private int distance;
    private double price;
    private int time;

    public Connection(Station source, Station destination, int distance, double price, int time) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.price = price;
        this.time = time;
    }

    public Station getSource() {
        return source;
    }

    public Station getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public double getPrice() {
        return price;
    }

    public int getTime() {
        return time;
    }
    public void setSource(Station source) {
        this.source = source;
    }

    // Métodos getters e setters para os atributos

    @Override
    public String toString() {
        return source + " -> " + destination + " (" + distance + ")";
    }
}