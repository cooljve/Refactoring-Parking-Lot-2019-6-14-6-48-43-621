package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NOT_ENOUGH_POSITION_EXCEPTION;
import com.thoughtworks.tdd.exception.TICKET_MISSING_EXCEPTION;
import com.thoughtworks.tdd.exception.UNRECOGNIZED_PARKING_TICKET_EXCEPTION;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
  private List<ParkingLot> parkingLotList;

  public ParkingTicket park(Car car) {
    setParkingLotList(getParkingLotList().stream().filter(x -> !x.isFull()).collect(Collectors.toList()));
    if (getParkingLotList().size() == 0) {
      throw new NOT_ENOUGH_POSITION_EXCEPTION();
    }
    ParkingTicket ticket = getParkingLotList().get(0).park(car);
    return ticket;
  }

  public Car fetch(ParkingTicket ticket) {
    if (ticket == null) {
      throw new TICKET_MISSING_EXCEPTION();
    }
    Car car = null;
    for (ParkingLot lot : getParkingLotList()) {
      if (lot.getMap().containsKey(ticket)) {
        car = lot.fetch(ticket);
      }
    }
    if (car == null) {
      throw new UNRECOGNIZED_PARKING_TICKET_EXCEPTION();
    }
    return car;
  }

  public List<ParkingLot> getParkingLotList() {
    return parkingLotList;
  }

  public void setParkingLotList(List<ParkingLot> parkingLotList) {
    this.parkingLotList = parkingLotList;
  }

}
