package classes;

import algs4.In;

class Connection {
    private int id;
    private Station source;
    private Station destination;
    private double distance;
    private double price;
    private Date startdate;
    private Date enddate;
    private int time;

    //Construtor
    public Connection(Integer id, Station source, Station destination, double distance, double price, Date startdate, Date enddate) {
        this.id = id;
        this.source = source; //origem
        this.destination = destination; //destino
        this.distance = distance;
        this.price = price;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Station getSource() {
        return source;
    }

    public Station getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
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
        return source + " --> " + destination + " (" + distance + ")";
    }

}