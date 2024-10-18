package routes.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import routes.Entities.Leg;
import routes.Entities.Route;
import routes.Entities.Stop;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TimeLimitServiceTest {

    private TimeLimitService timeLimitService;

    @BeforeEach
    void setUp() {
        timeLimitService = new TimeLimitService();
    }

    @Test
    void testCalculateTotalTravelTime() {
        Route route = createTestRoute();

        Duration totalTravelTime = timeLimitService.calculateTotalTravelTime(route);

        assertNotNull(totalTravelTime);
        assertEquals(Duration.ofHours(17), totalTravelTime);
    }

    @Test
    void testCanCompleteInTimeWithinLimit() {
        Route route = createTestRoute();
        Duration maxDuration = Duration.ofHours(20);

        boolean result = timeLimitService.canCompleteInTime(route, maxDuration);

        assertTrue(result, "Маршрут має бути виконаним у межах 20 годин");
    }

    @Test
    void testCanCompleteInTimeExceedsLimit() {
        Route route = createTestRoute();
        Duration maxDuration = Duration.ofHours(15);

        boolean result = timeLimitService.canCompleteInTime(route, maxDuration);

        assertFalse(result, "Маршрут не може бути виконаним у межах 15 годин");
    }

    private Route createTestRoute() {
        Stop stop1 = new Stop("Київ", null);
        Stop stop2 = new Stop("Львів", null);
        Stop stop3 = new Stop("Одеса", null);

        LocalDateTime now = LocalDateTime.now();

        Leg leg1 = new Leg(stop1, stop2, 300, Duration.ofHours(5), now.plusHours(5), Duration.ofHours(3));
        Leg leg2 = new Leg(stop2, stop3, 400, Duration.ofHours(6), now.plusHours(11), Duration.ofHours(3));

        Route route = new Route();
        route.setStops(Arrays.asList(stop1, stop2, stop3));
        route.addLeg(leg1);
        route.addLeg(leg2);

        return route;
    }
}
