package routes.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import routes.Entities.Leg;
import routes.Entities.Route;
import routes.Entities.Stop;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteInfoServiceTest {

    private RouteInfoService routeInfoService;

    @BeforeEach
    void setUp() {
        routeInfoService = new RouteInfoService();
    }

    @Test
    void testGetRouteInfoWithNoLegs() {
        Route emptyRoute = new Route();
        List<String> routeInfo = routeInfoService.getRouteInfo(emptyRoute);

        assertNotNull(routeInfo);
        assertEquals(1, routeInfo.size());
        assertEquals("Маршрут не містить етапів.", routeInfo.get(0));
    }

    @Test
    void testGetRouteInfoWithLegs() {
        Route route = createTestRoute();
        List<String> routeInfo = routeInfoService.getRouteInfo(route);

        assertNotNull(routeInfo);
        assertTrue(routeInfo.size() > 1);
        assertTrue(routeInfo.contains("Загальна кількість етапів: 2"));
        assertTrue(routeInfo.contains("Загальна відстань: 700 км"));
        assertTrue(routeInfo.stream().anyMatch(info -> info.startsWith("Час відправлення з початкової зупинки: ")));
        assertTrue(routeInfo.stream().anyMatch(info -> info.startsWith("Час прибуття на кінцеву зупинку: ")));
    }

    private Route createTestRoute() {
        Stop stop1 = new Stop("Київ", ZoneId.of("Europe/Kiev"));
        Stop stop2 = new Stop("Львів", ZoneId.of("Europe/Kiev"));
        Stop stop3 = new Stop("Одеса", ZoneId.of("Europe/Kiev"));

        LocalDateTime now = LocalDateTime.now();

        Leg leg1 = new Leg(stop1, stop2, 300, Duration.ofHours(5), now.plusHours(5), Duration.ofHours(2));
        Leg leg2 = new Leg(stop2, stop3, 400, Duration.ofHours(6), now.plusHours(13), Duration.ofHours(2));

        Route route = new Route();
        route.setStops(List.of(stop1, stop2, stop3));
        route.addLeg(leg1);
        route.addLeg(leg2);

        return route;
    }
}
