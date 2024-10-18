package routes.Services;

import routes.Entities.Leg;
import routes.Entities.Route;

import java.time.Duration;

public class TimeLimitService {
    public Duration calculateTotalTravelTime(Route route) {
        Duration totalDuration = Duration.ZERO;

        for (Leg leg : route.getLegs()) {
            totalDuration = totalDuration.plus(leg.getTravelTime());
            totalDuration = totalDuration.plus(leg.getStopDuration());
        }

        return totalDuration;
    }

    public boolean canCompleteInTime(Route route, Duration maxDuration) {
        Duration totalDuration = calculateTotalTravelTime(route);
        return totalDuration.compareTo(maxDuration) <= 0;
    }
}
