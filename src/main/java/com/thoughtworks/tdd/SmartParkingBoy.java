package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NOT_ENOUGH_POSITION_EXCEPTION;

import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(ParkingLot... parkingLots) {
    super(parkingLots);
  }

  public ParkingTicket park(Car car) {
    parkingLots = parkingLots.stream().filter(x -> !x.isFull()).collect(Collectors.toList());
    if (parkingLots.size() == 0) {
      throw new NOT_ENOUGH_POSITION_EXCEPTION();
    }
    parkingLots.sort((a, b) -> b.getAvailabel() - a.getAvailabel());
    return parkingLots.get(0).park(car);
  }
}
