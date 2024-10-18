package routes.Views;

import routes.Entities.Route;
import routes.Services.RouteInfoService;

import java.util.List;

public class RouteInfoView {
  public void handle(Route route) {
    RouteInfoService routeInfoService = new RouteInfoService();

    List<String> routeDetails = routeInfoService.getRouteInfo(route);

    for (String detail : routeDetails) {
        System.out.println(detail);
    }
  }
}
