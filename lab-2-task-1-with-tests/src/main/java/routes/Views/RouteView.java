package routes.Views;

import routes.Entities.Route;
import routes.Entities.Stop;
import routes.Entities.Transport;
import routes.Services.RouteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RouteView {
    private Scanner scanner = new Scanner(System.in);

    public Route handle(Transport transport) {
        RouteService routeService = new RouteService();
        List<Stop> stops = new ArrayList<>();
        
        System.out.println("Введіть початковий пункт маршруту:");
        String startPoint = scanner.nextLine();
        Stop startStop = new Stop(startPoint, null);
        stops.add(startStop);

        while (true) {
            System.out.println("Додати зупинку (введіть назву або 'end' щоб закінчити):");
            String stop = scanner.nextLine();
            if (stop.equalsIgnoreCase("end")) {
                break;
            }
            Stop intermediateStop = new Stop(stop, null);
            stops.add(intermediateStop);
        }

        System.out.println("Введіть кінцевий пункт маршруту:");
        String endPoint = scanner.nextLine();
        Stop endStop = new Stop(endPoint, null);
        stops.add(endStop);

        Route route = routeService.createRoute(stops, transport);
        System.out.println("Маршрут створено.");

        return route;
    }
}
