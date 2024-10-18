package routes.Services;

import routes.Entities.Car;
import routes.Entities.Plane;
import routes.Entities.Train;
import routes.Entities.Transport;

public class TransportService {

    public Transport selectTransport(int choice) {
        switch (choice) {
            case 1:
                return new Car(80, 0.08);
            case 2:
                return new Plane(900, 2.5);
            case 3:
                return new Train(100, 0.05);
            default:
                throw new IllegalArgumentException("Неправльний вибір вибір транспорту");
        }
    }
}
