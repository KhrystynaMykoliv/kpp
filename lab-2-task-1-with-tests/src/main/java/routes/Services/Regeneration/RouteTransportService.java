package routes.Services.Regeneration;

import routes.Entities.Leg;
import routes.Entities.Route;

import java.time.Duration;

public class RouteTransportService implements IRegenerateRoute {

    @Override
    public boolean regenerateStops(Route route, Duration maxDuration) {
        Duration totalTravelTime = Duration.ZERO;
        Duration[] newStopTimes = new Duration[route.getLegs().size()];

        for (int i = 0; i < route.getLegs().size(); i++) {
            Leg leg = route.getLegs().get(i);
            totalTravelTime = totalTravelTime.plus(leg.getTravelTime());

            // зменшення часу зупинок на половину
            Duration reducedStopTime = leg.getStopDuration().dividedBy(2);
            newStopTimes[i] = reducedStopTime;
            totalTravelTime = totalTravelTime.plus(reducedStopTime);

            System.out.println("Зупинка " + (i + 1) + ": початковий час зупинки = " + leg.getStopDuration() +
                    ", зменшений час = " + reducedStopTime +
                    ", загальний час подорожі = " + totalTravelTime);
        }

        if (totalTravelTime.compareTo(maxDuration) <= 0) {
            for (int i = 0; i < route.getLegs().size(); i++) {
                route.getLegs().get(i).setStopDuration(newStopTimes[i]);
            }
            return true;
        }

        return false;
    }
}
