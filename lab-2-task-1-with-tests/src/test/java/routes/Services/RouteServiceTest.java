package routes.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import routes.Entities.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RouteServiceTest {

    private RouteService routeService;

    @BeforeEach
    void setUp() {
        routeService = new RouteService();
    }

    @Test
    void testCreateRouteWithMultipleStopsAndTransport() {
        Stop stop1 = new Stop("Stop1", ZoneId.of("America/New_York"));
        Stop stop2 = new Stop("Stop2", ZoneId.of("Europe/London"));
        Stop stop3 = new Stop("Stop3", ZoneId.of("Asia/Tokyo"));

        List<Stop> stops = Arrays.asList(stop1, stop2, stop3);

        Transport train = new Train(100, 0.5);

        Route route = routeService.createRoute(stops, train);

        assertNotNull(route);
        assertEquals(3, route.getStops().size());
        assertEquals(2, route.getLegs().size());
    }

    @Test
    void testLegDistanceCalculationIsInRange() {
        Stop stop1 = new Stop("Stop1", ZoneId.of("America/New_York"));
        Stop stop2 = new Stop("Stop2", ZoneId.of("Europe/London"));

        List<Stop> stops = Arrays.asList(stop1, stop2);

        Transport train = new Train(100, 0.5);

        Route route = routeService.createRoute(stops, train);

        Leg leg = route.getLegs().get(0);
        assertTrue(leg.getDistance() >= 200 && leg.getDistance() <= 1000);
    }

    @Test
    void testCalculateStopDuration() {
        Stop stop1 = new Stop("Stop1", ZoneId.of("America/New_York"));
        Stop stop2 = new Stop("Stop2", ZoneId.of("Europe/London"));

        Transport train = new Train(100, 0.5);

        Route route = routeService.createRoute(Arrays.asList(stop1, stop2), train);

        Leg leg = route.getLegs().get(0);

        if (leg.getDistance() > 500) {
            assertEquals(Duration.ofHours(10), leg.getStopDuration());
        } else {
            assertTrue(leg.getStopDuration().toHours() >= 1 && leg.getStopDuration().toHours() <= 3);
        }
    }

    @Test
    void testTrainCalculateTravelTime() {
        Train train = new Train(100, 0.5);

        long distance = 500;
        Duration travelTime = train.calculateTravelTime(distance);

        assertEquals(Duration.ofHours(5), travelTime);
    }

    @Test
    void testPlaneCalculateTravelTime() {
        Plane plane = new Plane(800, 2.5);

        long distance = 1600;
        Duration travelTime = plane.calculateTravelTime(distance);

        assertEquals(Duration.ofHours(2), travelTime);
    }

    @Test
    void testLegHasCorrectArrivalTime() {
        Stop stop1 = new Stop("Stop1", ZoneId.of("America/New_York"));
        Stop stop2 = new Stop("Stop2", ZoneId.of("Europe/London"));

        List<Stop> stops = Arrays.asList(stop1, stop2);

        Transport plane = new Plane(800, 2.5);

        Route route = routeService.createRoute(stops, plane);
        Leg leg = route.getLegs().getFirst();

        LocalDateTime expectedArrivalTime = LocalDateTime.now().plusHours(2);

        assertFalse(leg.getArrivalTime().isAfter(expectedArrivalTime.minusMinutes(1)));
        assertTrue(leg.getArrivalTime().isBefore(expectedArrivalTime.plusMinutes(1)));
    }

    @Test
    void testRouteContainsCorrectStopsAndLegs() {
        Stop stop1 = new Stop("Stop1", ZoneId.of("America/New_York"));
        Stop stop2 = new Stop("Stop2", ZoneId.of("Europe/London"));

        List<Stop> stops = Arrays.asList(stop1, stop2);

        Transport train = new Train(100, 0.5);

        Route route = routeService.createRoute(stops, train);

        assertNotNull(route.getLegs());
        assertEquals(1, route.getLegs().size());
        assertEquals(stop1, route.getLegs().getFirst().getFrom());
        assertEquals(stop2, route.getLegs().getFirst().getTo());
    }
}
