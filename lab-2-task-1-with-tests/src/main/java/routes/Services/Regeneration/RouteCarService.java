package routes.Services.Regeneration;

import routes.Entities.Leg;
import routes.Entities.Route;

import java.time.Duration;

public class RouteCarService implements IRegenerateRoute {
    @Override
    public boolean regenerateStops(Route route, Duration maxDuration) {
        Duration totalTravelTime = Duration.ZERO;
        Duration[] newStopTimes = new Duration[route.getLegs().size()];

        for (int i = 0; i < route.getLegs().size(); i++) {
            Leg leg = route.getLegs().get(i);
            totalTravelTime = totalTravelTime.plus(leg.getTravelTime());

            Duration originalStopTime = leg.getStopDuration();
            Duration reducedStopTime;

            if (originalStopTime.toHours() > 8) {
                reducedStopTime = originalStopTime.minus(Duration.ofHours(4));
            } else if (originalStopTime.toHours() > 5) {
                reducedStopTime = originalStopTime.minus(Duration.ofHours(3));
            } else {
                reducedStopTime = originalStopTime.minus(Duration.ofHours(1));
            }

            if (reducedStopTime.isNegative()) {
                reducedStopTime = Duration.ZERO;
            }

            newStopTimes[i] = reducedStopTime;
            totalTravelTime = totalTravelTime.plus(reducedStopTime);

            if (totalTravelTime.compareTo(maxDuration) > 0) {
                return false;
            }
        }

        for (int i = 0; i < route.getLegs().size(); i++) {
            route.getLegs().get(i).setStopDuration(newStopTimes[i]);
        }

        return true;
    }
}
