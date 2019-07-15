package com.thoughtworks.tdd;

import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.tdd.Constant.NO_TICKET;
import static com.thoughtworks.tdd.Constant.PARKING_LOT_IS_FULL;
import static com.thoughtworks.tdd.Constant.WRONG_TICKET;

public class ParkingBoy {
  private List<ParkingLot> parkingLotList;

  public Response park(Car car) {
    setParkingLotList(getParkingLotList().stream().filter(x -> !x.isFull()).collect(Collectors.toList()));
    if (getParkingLotList().size() == 0) {
      return new Response(PARKING_LOT_IS_FULL, null);
    }
    if (getParkingLotList().get(0).getMap().containsValue(car) || car == null) {
      return new Response("", null);
    }
    ParkingTicket ticket = new ParkingTicket();
    getParkingLotList().get(0).getMap().put(ticket, car);
    return new Response("", ticket);
  }

  public Response fetch(ParkingTicket ticket) {
    if (ticket == null) {
      return new Response(NO_TICKET, null);
    }
    Car car = null;
    for (ParkingLot lot : getParkingLotList()) {
      if (lot.getMap().containsKey(ticket)) {
        car = lot.getMap().get(ticket);
        lot.getMap().remove(ticket);
      }
    }
    if (car == null) {
      return new Response(WRONG_TICKET, null);
    }
    return new Response("", car);
  }

  public List<ParkingLot> getParkingLotList() {
    return parkingLotList;
  }

  public void setParkingLotList(List<ParkingLot> parkingLotList) {
    this.parkingLotList = parkingLotList;
  }

}
