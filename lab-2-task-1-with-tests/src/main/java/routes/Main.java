package routes;

import java.util.Scanner;
import routes.Entities.*;
import routes.Views.*;
import routes.Templates.Holder;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Holder<Route> routeHolder = new Holder<>();
        Holder<Transport> transportHolder = new Holder<>();

        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (ensureTransportIsSelected(transportHolder.get())) {
                        routeHolder.set(new RouteView().handle(transportHolder.get()));
                    }
                    break;
                case 2:
                    transportHolder.set(new TransportView().handle());
                    break;
                case 3:
                    ifRouteExists(routeHolder.get(), () -> new TimeZoneView().handle(routeHolder.get()));
                    break;
                case 4:
                    ifRouteExists(routeHolder.get(), () -> new TimeLimitView().handle(routeHolder.get(), transportHolder.get()));
                    break;
                case 5:
                    ifRouteExists(routeHolder.get(), () -> new RouteInfoView().handle(routeHolder.get()));
                    break;
                case 0:
                    System.out.println("Вихід із програми.");
                    return;
                default:
                    System.out.println("Неправильний вибір.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Оберіть дію:");
        System.out.println("1. Вибір маршрутів");
        System.out.println("2. Вибір транспорту");
        System.out.println("3. Врахування часових зон");
        System.out.println("4. Обмеження на кількість годин");
        System.out.println("5. Виведення інформації про подорож");
        System.out.println("0. Вихід");
    }

    public static boolean ensureTransportIsSelected(Transport transport) {
        if (transport == null) {
            System.out.println("Спочатку оберіть транспорт.");
            return false;
        }
        return true;
    }

    public static void ifRouteExists(Route route, Runnable action) {
        if (route != null) {
            action.run();
        } else {
            System.out.println("Спочатку створіть маршрут.");
        }
    }
}
