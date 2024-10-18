package routes.Services.Regeneration;

import routes.Entities.Route;

import java.time.Duration;

public interface IRegenerateRoute {
    boolean regenerateStops(Route route, Duration maxDuration);
}
