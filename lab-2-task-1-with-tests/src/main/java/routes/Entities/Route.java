package routes.Entities;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<Leg> legs = new ArrayList<>();
    private Transport transport;
    private List<Stop> stops;

    public List<Leg> getLegs() {
        return this.legs;
    }

    public void addLeg(Leg leg) {
        this.legs.add(leg);
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Stop> getStops() {
      return this.stops;
    }
}
