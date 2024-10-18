package routes.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import routes.Entities.Car;
import routes.Entities.Plane;
import routes.Entities.Train;
import routes.Entities.Transport;

import static org.junit.jupiter.api.Assertions.*;

class TransportServiceTest {

    private TransportService transportService;

    @BeforeEach
    void setUp() {
        transportService = new TransportService();
    }

    @Test
    void testSelectTransportCar() {
        Transport transport = transportService.selectTransport(1);
        assertNotNull(transport);
        assertInstanceOf(Car.class, transport);
        assertEquals(80, transport.getSpeed());
        assertEquals(0.08, transport.getFuelConsumptionPerKm());
    }

    @Test
    void testSelectTransportPlane() {
        Transport transport = transportService.selectTransport(2);
        assertNotNull(transport);
        assertInstanceOf(Plane.class, transport);
        assertEquals(900, transport.getSpeed());
        assertEquals(2.5, transport.getFuelConsumptionPerKm());
    }

    @Test
    void testSelectTransportTrain() {
        Transport transport = transportService.selectTransport(3);
        assertNotNull(transport);
        assertInstanceOf(Train.class, transport);
        assertEquals(100, transport.getSpeed());
        assertEquals(0.05, transport.getFuelConsumptionPerKm());
    }

    @Test
    void testSelectTransportInvalidChoice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transportService.selectTransport(4);
        });
        assertEquals("Неправльний вибір вибір транспорту", exception.getMessage());
    }
}
