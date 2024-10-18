package routes.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import routes.Entities.Stop;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeZoneServiceTest {

  private TimeZoneService timeZoneService;

  @BeforeEach
  void setUp() {
    timeZoneService = new TimeZoneService();
  }

  @Test
  void testAdjustTimeZonesWithKnownCities() {
    Stop stop1 = new Stop("Kyiv", null);
    Stop stop2 = new Stop("Lviv", null);
    Stop stop3 = new Stop("Berlin", null);

    List<Stop> stops = Arrays.asList(stop1, stop2, stop3);
    List<Stop> updatedStops = timeZoneService.adjustTimeZones(stops);

    assertEquals(ZoneId.of("Europe/Kiev"), updatedStops.get(0).getZoneId());
    assertEquals(ZoneId.of("Europe/Kiev"), updatedStops.get(1).getZoneId());
    assertEquals(ZoneId.of("Europe/Berlin"), updatedStops.get(2).getZoneId());
  }

  @Test
  void testAdjustTimeZonesWithUnknownCity() {
    Stop stop1 = new Stop("UnknownCity", null);

    List<Stop> stops = Arrays.asList(stop1);
    List<Stop> updatedStops = timeZoneService.adjustTimeZones(stops);

    assertEquals(ZoneId.of("UTC"), updatedStops.get(0).getZoneId());
  }

  @Test
  void testAdjustTimeZonesWithEmptyList() {
    List<Stop> stops = Arrays.asList();
    List<Stop> updatedStops = timeZoneService.adjustTimeZones(stops);

    assertTrue(updatedStops.isEmpty());
  }
}
