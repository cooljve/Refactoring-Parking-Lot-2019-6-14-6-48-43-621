package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager {
  private List<Parker> parkers =new ArrayList<>();
  private List<ParkingLot> parkingLots = new ArrayList<>();

  public Manager(Parker... parkers) {
    this.parkers.addAll(Arrays.asList(parkers));
  }

  public Manager(ParkingLot... parkingLots) {
    this.parkingLots.addAll(Arrays.asList(parkingLots));
  }

  public ParkingTicket park(Car car) {
    for (Parker parker : parkers) {
      return parker.park(car);
    }
    for (ParkingLot parkingLot : parkingLots) {
      return parkingLot.park(car);
    }
    return park(car);
  }

  public Car fetch(ParkingTicket ticket) {
    for (Parker parker : parkers) {
      return parker.fetch(ticket);
    }
    for (ParkingLot parkingLot : parkingLots) {
      return parkingLot.fetch(ticket);
    }
    return fetch(ticket);
  }

  public void setParkers(List<Parker> parkers) {
    this.parkers = parkers;
  }

  public void setParkingLots(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }
}
