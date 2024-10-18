package routes.Views;

import routes.Entities.Transport;
import routes.Services.TransportService;

import java.util.Scanner;

public class TransportView {
  private Scanner scanner = new Scanner(System.in);

  public routes.Entities.Transport handle() {
    TransportService transportService = new TransportService();
    Transport selectedTransport = null;

    while (selectedTransport == null) {
        System.out.println("Оберіть тип транспорту:");
        System.out.println("1. Автомобіль");
        System.out.println("2. Літак");
        System.out.println("3. Поїзд");

        int transportChoice = scanner.nextInt();
        try {
            selectedTransport = transportService.selectTransport(transportChoice);
            System.out.println("Ви обрали транспорт: " + selectedTransport.getClass().getSimpleName());
        } catch (IllegalArgumentException e) {
            System.out.println("Неправильний вибір. Спробуйте знову!");
        }
    }

    return selectedTransport;
  }
}
