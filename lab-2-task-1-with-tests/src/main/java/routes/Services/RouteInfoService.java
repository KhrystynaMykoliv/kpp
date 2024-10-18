package routes.Services;

import routes.Entities.Leg;
import routes.Entities.Route;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RouteInfoService {
    List<String> routeDetails = new ArrayList<>();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");

    public List<String> getRouteInfo(Route route) {
        if (route.getLegs().isEmpty()) {
            routeDetails.add("Маршрут не містить етапів.");
            return routeDetails;
        }

        long totalDistance = 0;
        Duration totalDuration = Duration.ZERO;
        
        LocalDateTime startTime = route.getLegs().get(0).getArrivalTime().minus(route.getLegs().get(0).getTravelTime());
        ZoneId startZone = route.getLegs().get(0).getFrom().getZoneId();
        
        LocalDateTime endTime = route.getLegs().get(route.getLegs().size() - 1).getArrivalTime();
        ZoneId endZone = route.getLegs().get(route.getLegs().size() - 1).getTo().getZoneId();

        for (Leg leg : route.getLegs()) {
            totalDistance += leg.getDistance();
            totalDuration = totalDuration.plus(leg.getTravelTime()).plus(leg.getStopDuration());
        }

        routeDetails.add("Загальна кількість етапів: " + route.getLegs().size());
        routeDetails.add("Загальна відстань: " + totalDistance + " км");
        routeDetails.add("Загальний час подорожі (включаючи зупинки): " + formatDuration(totalDuration));

        addLegsToSummary(route.getLegs());

        routeDetails.add("Час відправлення з початкової зупинки: " + startTime.atZone(startZone).format(formatter));
        routeDetails.add("Час прибуття на кінцеву зупинку: " + endTime.atZone(endZone).format(formatter));

        return routeDetails;
    }

    private void addLegsToSummary(List<Leg> legs) {
        for (Leg leg : legs) {
            routeDetails.add("Маршрут: " + leg.getFrom().getName() + " -> " + leg.getTo().getName());
            routeDetails.add("Відстань: " + leg.getDistance() + " км");
            routeDetails.add("Час подорожі: " + formatDuration(leg.getTravelTime()));
            routeDetails.add("Час зупинки: " + formatDuration(leg.getStopDuration()));
            routeDetails.add("Прибуття: " + leg.getArrivalTime().atZone(leg.getTo().getZoneId()).format(formatter));
            routeDetails.add("");
        }
    }

    private String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();

        if (days > 0) {
            return days + " днів " + hours + " годин " + minutes + " хвилин";
        } else if (hours > 0) {
            return hours + " годин " + minutes + " хвилин";
        } else {
            return minutes + " хвилин";
        }
    }
}
