package com.thoughtworks.tdd;

public interface Parkable {
  ParkingTicket park(Car car);

  Car fetch(ParkingTicket ticket);

  boolean isAllFull();

  boolean containsTicket(ParkingTicket ticket);
}
