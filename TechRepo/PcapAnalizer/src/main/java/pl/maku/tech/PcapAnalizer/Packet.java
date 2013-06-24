package pl.maku.tech.PcapAnalizer;

/**
 * Created with IntelliJ IDEA.
 * User: Maciek
 * Date: 22.06.13
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */

public class Packet {


    private String time;
    private int id;
    private String source;
    private String destination;

    public Packet(String time, int id, String source, String destination) {
        this.time = time;
        this.id = id;
        this.source = source;
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "time='" + time + '\'' +
                ", id=" + id +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
