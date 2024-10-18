package routes.Entities;

import routes.Entities.Command.ICalculateTimeCommand;

import java.time.Duration;

public abstract class Transport implements ICalculateTimeCommand {
  protected int speed;
  protected double fuelConsumptionPerKm;

  public Transport(int speed, double fuelConsumptionPerKm) {
    this.speed = speed;
    this.fuelConsumptionPerKm = fuelConsumptionPerKm;
  }

  @Override
  public Duration calculateTravelTime(long distance) {
    double timeInHours = (double) distance / speed;
    long totalMinutes = (long) (timeInHours * 60);

    return Duration.ofMinutes(totalMinutes);
  }

  public int getSpeed() {
    return speed;
  }

  public double getFuelConsumptionPerKm() {
    return fuelConsumptionPerKm;
  }
}
