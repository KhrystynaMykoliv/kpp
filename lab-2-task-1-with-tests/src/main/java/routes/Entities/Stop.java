package routes.Entities;

import java.time.ZoneId;

public class Stop {
    private String name;
    private ZoneId zoneId;

    public Stop(String name, ZoneId zoneId) {
        this.name = name;
        this.zoneId = zoneId;
    }

    public String getName() {
        return name;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
      this.zoneId = zoneId;
    }
}
