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

class RouteTransportServiceTest {

    private RouteTransportService routeTransportService;

    @BeforeEach
    void setUp() {
        routeTransportService = new RouteTransportService();
    }

    @Test
    void testRegenerateStopsWithinMaxDuration() {
        Route route = createTestRoute();
        Duration maxDuration = Duration.ofHours(20);

        boolean result = routeTransportService.regenerateStops(route, maxDuration);

        assertTrue(result, "Очікується, що оновлення зупинок пройде успішно, коли загальний час у межах максимальної тривалості");

        assertEquals(Duration.ofHours(2), route.getLegs().get(0).getStopDuration());
        assertEquals(Duration.ofHours(2), route.getLegs().get(1).getStopDuration());
    }

    @Test
    void testRegenerateStopsExceedsMaxDuration() {
        Route route = createTestRoute();
        Duration maxDuration = Duration.ofHours(10);

        boolean result = routeTransportService.regenerateStops(route, maxDuration);

        assertFalse(result, "Очікується, що оновлення зупинок не відбудеться, коли загальний час перевищує максимальну тривалість");

        assertEquals(Duration.ofHours(4), route.getLegs().get(0).getStopDuration());
        assertEquals(Duration.ofHours(4), route.getLegs().get(1).getStopDuration());
    }

    private Route createTestRoute() {
        Stop stop1 = new Stop("Київ", null);
        Stop stop2 = new Stop("Львів", null);
        Stop stop3 = new Stop("Одеса", null);

        Leg leg1 = new Leg(stop1, stop2, 300, Duration.ofHours(5), LocalDateTime.now(), Duration.ofHours(4));
        Leg leg2 = new Leg(stop2, stop3, 400, Duration.ofHours(6), LocalDateTime.now(), Duration.ofHours(4));

        Route route = new Route();
        route.setStops(Arrays.asList(stop1, stop2, stop3));
        route.addLeg(leg1);
        route.addLeg(leg2);

        return route;
    }
}
