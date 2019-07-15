package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NOT_ENOUGH_POSITION_EXCEPTION;

import java.util.stream.Collectors;

public class SuperSmartParkingBoy extends ParkingBoy {
  public ParkingTicket park(Car car) {
    setParkingLotList(getParkingLotList().stream().filter(x -> !x.isFull()).collect(Collectors.toList()));
    if (getParkingLotList().size() == 0) {
      throw new NOT_ENOUGH_POSITION_EXCEPTION();
    }
    ParkingTicket ticket = new ParkingTicket();
    getParkingLotList().sort((a, b) -> (int) (((double) b.getMap().size() / b.getCapacity()) - ((double) a.getMap().size() / a.getCapacity())));
    getParkingLotList().get(0).getMap().put(ticket, car);
    return ticket;
  }
}
