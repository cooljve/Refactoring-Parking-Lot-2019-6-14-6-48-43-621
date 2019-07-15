package com.thoughtworks.tdd;

import java.util.stream.Collectors;

import static com.thoughtworks.tdd.Constant.PARKING_LOT_IS_FULL;

public class SuperSmartParkingBoy extends ParkingBoy {
  public Response park(Car car) {
    setParkingLotList(getParkingLotList().stream().filter(x -> !x.isFull()).collect(Collectors.toList()));
    if (getParkingLotList().size() == 0) {
      return new Response(PARKING_LOT_IS_FULL, null);
    }
    if (getParkingLotList().get(0).getMap().containsValue(car) || car == null) {
      return new Response("", null);
    }
    ParkingTicket ticket = new ParkingTicket();
    getParkingLotList().sort((a, b) -> (int) (((double)b.getMap().size() / b.getCapacity()) -  ((double)a.getMap().size() / a.getCapacity())));
    getParkingLotList().get(0).getMap().put(ticket, car);
    return new Response("", ticket);
  }
}
