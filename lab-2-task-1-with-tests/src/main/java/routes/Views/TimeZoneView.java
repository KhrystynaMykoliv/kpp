package routes.Views;

import routes.Entities.Route;
import routes.Entities.Stop;
import routes.Services.TimeZoneService;

import java.util.List;

public class TimeZoneView {
    public void handle(Route route) {
        TimeZoneService timeZoneService = new TimeZoneService();
        List<Stop> stops = route.getStops();

        stops = timeZoneService.adjustTimeZones(stops);

        System.out.println("Часові зони враховано для маршруту.");
        for (Stop stop : stops) {
          System.out.println("Зупинка: " + stop.getName() + ". Часовий пояс: " + stop.getZoneId());
        }
    }
}
