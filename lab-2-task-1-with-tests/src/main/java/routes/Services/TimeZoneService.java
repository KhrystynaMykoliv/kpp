package routes.Services;

import routes.Entities.Stop;

import java.time.ZoneId;
import java.util.List;

public class TimeZoneService {
  public List<Stop> adjustTimeZones(List<Stop> stops) {
    for (Stop stop : stops) {
      stop.setZoneId(determineTimeZone(stop.getName()));
    }
    return stops;
  }

  private ZoneId determineTimeZone(String cityName) {
    switch (cityName.toLowerCase()) {
      case "kyiv", "lviv":
        return ZoneId.of("Europe/Kiev");
        case "Tokyo":
        return ZoneId.of("Asia/Tokyo");  
      case "berlin":
        return ZoneId.of("Europe/Berlin");
      default:
        return ZoneId.of("UTC");
    }
  }
}
