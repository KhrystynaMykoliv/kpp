package routes.Views;

import routes.Entities.Car;
import routes.Entities.Route;
import routes.Entities.Transport;
import routes.Services.Regeneration.IRegenerateRoute;
import routes.Services.Regeneration.RouteCarService;
import routes.Services.Regeneration.RouteTransportService;
import routes.Services.TimeLimitService;

import java.time.Duration;
import java.util.Scanner;

public class TimeLimitView {
    private Scanner scanner = new Scanner(System.in);

    public void handle(Route route, Transport transport) {
        TimeLimitService timeLimitService = new TimeLimitService();
        Duration maxTravelTime = getUserInput();

        Duration actualTravelTime = timeLimitService.calculateTotalTravelTime(route);
        displayActualTravelTime(actualTravelTime);

        if (timeLimitService.canCompleteInTime(route, maxTravelTime)) {
            System.out.println("Подорож можлива за заданий час: " + maxTravelTime.toHours() + " годин.");
        } else {
            IRegenerateRoute regenerationService = getRegenerationService(transport);
            if (regenerationService instanceof RouteCarService) {
                handleCarRouteRegeneration(route, maxTravelTime, regenerationService);
            } else {
                handleOtherTransportRegeneration(route, maxTravelTime, regenerationService);
            }
        }
    }

    private Duration getUserInput() {
        System.out.println("Введіть максимальну кількість годин, яку готові витратити на подорож:");
        int hours = scanner.nextInt();
        return Duration.ofHours(hours);
    }

    private void displayActualTravelTime(Duration actualTravelTime) {
        System.out.println("Фактичний час подорожі: " + formatDuration(actualTravelTime));
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        return hours + " годин " + minutes + " хвилин";
    }

    private IRegenerateRoute getRegenerationService(Transport transport) {
        if (transport.getClass() == Car.class) {
            return new RouteCarService();
        } else {
            return new RouteTransportService();
        }
    }

    private void handleCarRouteRegeneration(Route route, Duration maxTravelTime, IRegenerateRoute regenerationService) {
        boolean canReduceStops = regenerationService.regenerateStops(route, maxTravelTime);

        if (canReduceStops) {
            Duration newTravelTime = new TimeLimitService().calculateTotalTravelTime(route);
            System.out.println("Час зупинок було зменшено. Подорож тепер можлива.");
            System.out.println("Новий фактичний час подорожі: " + formatDuration(newTravelTime));
        } else {
            System.out.println("Навіть при 0 зупинках, подорож неможлива.");
            System.out.println("Будьте обережні! Водіння без відпочинку може призвести до аварій.");
        }
    }

    private void handleOtherTransportRegeneration(Route route, Duration maxTravelTime, IRegenerateRoute regenerationService) {
        boolean stopsRegenerated = regenerationService.regenerateStops(route, maxTravelTime);

        if (stopsRegenerated) {
            Duration newTravelTime = new TimeLimitService().calculateTotalTravelTime(route);
            System.out.println("Час зупинок було перегенеровано. Подорож тепер можлива.");
            System.out.println("Новий фактичний час подорожі: " + formatDuration(newTravelTime));
        } else {
            System.out.println("Пошук нового маршруту не допоміг. Виберіть інший транспорт.");
        }
    }
}
