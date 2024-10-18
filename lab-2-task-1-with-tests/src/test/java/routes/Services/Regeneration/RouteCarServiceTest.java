package routes.Services.Regeneration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import routes.Entities.Leg;
import routes.Entities.Route;
import routes.Entities.Stop;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RouteCarServiceTest {

    private RouteCarService routeCarService;

    @BeforeEach
    void setUp() {
        routeCarService = new RouteCarService();
    }

    @Test
    void testRegenerateStopsWithinMaxDuration() {
        Route route = createTestRouteWithVariedStopTimes();
        Duration maxDuration = Duration.ofHours(25);

        boolean result = routeCarService.regenerateStops(route, maxDuration);

        assertFalse(result, "Regeneration should succeed when total travel time is within max duration");

        assertEquals(Duration.ofHours(10), route.getLegs().get(0).getStopDuration());
        assertEquals(Duration.ofHours(7), route.getLegs().get(1).getStopDuration());
        assertEquals(Duration.ofHours(4), route.getLegs().get(2).getStopDuration());
    }

    @Test
    void testRegenerateStopsExceedsMaxDuration() {
        Route route = createTestRouteWithVariedStopTimes();
        Duration maxDuration = Duration.ofHours(15);

        boolean result = routeCarService.regenerateStops(route, maxDuration);

        assertFalse(result, "Regeneration should fail when total travel time exceeds max duration");

        assertEquals(Duration.ofHours(10), route.getLegs().get(0).getStopDuration());
        assertEquals(Duration.ofHours(7), route.getLegs().get(1).getStopDuration());
        assertEquals(Duration.ofHours(4), route.getLegs().get(2).getStopDuration());
    }

    @Test
    void testRegenerateStopsWithStopTimesBecomingNegative() {
        Route route = createTestRouteWithShortStopTimes();
        Duration maxDuration = Duration.ofHours(15);

        boolean result = routeCarService.regenerateStops(route, maxDuration);

        assertTrue(result, "Regeneration should succeed");

        assertEquals(Duration.ZERO, route.getLegs().get(0).getStopDuration());
        assertEquals(Duration.ofHours(2), route.getLegs().get(1).getStopDuration());
    }

    private Route createTestRouteWithVariedStopTimes() {
        Stop stop1 = new Stop("Stop1", null);
        Stop stop2 = new Stop("Stop2", null);
        Stop stop3 = new Stop("Stop3", null);
        Stop stop4 = new Stop("Stop4", null);

        Leg leg1 = new Leg(stop1, stop2, 300, Duration.ofHours(5), LocalDateTime.now(), Duration.ofHours(10));
        Leg leg2 = new Leg(stop2, stop3, 400, Duration.ofHours(6), LocalDateTime.now(), Duration.ofHours(7));
        Leg leg3 = new Leg(stop3, stop4, 500, Duration.ofHours(7), LocalDateTime.now(), Duration.ofHours(4));

        Route route = new Route();
        route.setStops(Arrays.asList(stop1, stop2, stop3, stop4));
        route.addLeg(leg1);
        route.addLeg(leg2);
        route.addLeg(leg3);

        return route;
    }

    private Route createTestRouteWithShortStopTimes() {
        Stop stop1 = new Stop("Stop1", null);
        Stop stop2 = new Stop("Stop2", null);
        Stop stop3 = new Stop("Stop3", null);

        Leg leg1 = new Leg(stop1, stop2, 300, Duration.ofHours(4), LocalDateTime.now(), Duration.ofHours(1));
        Leg leg2 = new Leg(stop2, stop3, 400, Duration.ofHours(5), LocalDateTime.now(), Duration.ofHours(3));

        Route route = new Route();
        route.setStops(Arrays.asList(stop1, stop2, stop3));
        route.addLeg(leg1);
        route.addLeg(leg2);

        return route;
    }
}
