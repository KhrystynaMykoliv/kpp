package routes.Entities.Command;

import java.time.Duration;

public interface ICalculateTimeCommand {
  Duration calculateTravelTime(long distance);
}
