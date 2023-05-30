package classes;


import java.io.Serializable;

public class Viagem implements Serializable {
    private int id;
    private Date startdate;
    private Date enddate;
    private User user;
    private Connection connection;


    /**
     * prints User
     */
    public void printUser() {
        if (this.getUser() != null) {
            System.out.println(this.user);
        } else {
            System.out.println("There isn't a user in this Travel");
        }
    }

    /**
     * prints a classroom
     */
    public void printConnection() {
        if(this.getConnection() != null) {
            System.out.println(this.connection);
        }
        else {
            System.out.println("There isn't a connection in this Travel");
        }
    }
    public int getid() {
        return id;
    }
    public Date getStartdate() {
        return startdate;
    }
    public Date getEnddate() {
        return enddate;
    }
    public User getUser() {
        return user;
    }
    public Connection getConnection() {
        return connection;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Viagem(Connection connection, Date startdate, Date enddate, User user) {
        this.connection = connection;
        this.startdate = startdate;
        this.enddate = enddate;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Connection: " + connection + '\'' +
                " StartDate: " + startdate +
                " EndDate: " + enddate +
                "User: " + user;
    }
}