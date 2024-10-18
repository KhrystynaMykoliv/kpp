package routes.Entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class Leg {
  private Stop from;
  private Stop to;
  private long distance;
  private Duration travelTime;
  private LocalDateTime arrivalTime;
  private Duration stopDuration;

  public Leg(Stop from, Stop to, long distance, Duration travelTime, LocalDateTime arrivalTime, Duration stopDuration) {
    this.from = from;
    this.to = to;
    this.distance = distance;
    this.travelTime = travelTime;
    this.arrivalTime = arrivalTime;
    this.stopDuration = stopDuration;
  }

  public Stop getFrom() {
    return from;
  }

  public Stop getTo() {
    return to;
  }

  public long getDistance() {
    return distance;
  }

  public Duration getTravelTime() {
    return travelTime;
  }

  public LocalDateTime getArrivalTime() {
    return arrivalTime;
  }

  public Duration getStopDuration() {
    return stopDuration;
  }

  public void setStopDuration(Duration duration) {
    this.stopDuration = duration;
  }
}
