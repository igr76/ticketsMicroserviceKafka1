package com.example.stmlabs.model;

import java.time.LocalDateTime;

public class Ticket {

  private long id;
  private Route route;
  private LocalDateTime dateTime;
  private int place;
  private int cost;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Route getRoute() {
    return route;
  }

  public void setRoute(Route route) {
    this.route = route;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public int getPlace() {
    return place;
  }

  public void setPlace(int place) {
    this.place = place;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

}
