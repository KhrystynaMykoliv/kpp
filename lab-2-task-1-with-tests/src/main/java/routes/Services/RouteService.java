package routes.Services;

import routes.Entities.Leg;
import routes.Entities.Route;
import routes.Entities.Stop;
import routes.Entities.Transport;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class RouteService {
    private final Random random = new Random();

    public Route createRoute(List<Stop> stops, Transport transport) {
        Route route = new Route();
        route.setStops(stops);
        route.setTransport(transport);

        LocalDateTime currentTime = LocalDateTime.now();

        for (int i = 0; i < stops.size() - 1; i++) {
            Stop from = stops.get(i);
            Stop to = stops.get(i + 1);

            long distance = calculateDistance(from, to);

            Duration travelTime = transport.calculateTravelTime(distance);
            LocalDateTime arrivalTime = currentTime.plus(travelTime);
            Duration stopDuration = calculateStopDuration(distance);

            Leg leg = new Leg(from, to, distance, travelTime, arrivalTime, stopDuration);
            route.addLeg(leg);

            currentTime = arrivalTime.plus(stopDuration);
        }

        return route;
    }

    private long calculateDistance(Stop from, Stop to) {
        return 200 + random.nextInt(801);
    }

    private Duration calculateStopDuration(long distance) {
        if (distance > 500) {
            return Duration.ofHours(10);
        } else {
            int randomStopHours = 1 + random.nextInt(3);
            return Duration.ofHours(randomStopHours);
        }
    }
}
