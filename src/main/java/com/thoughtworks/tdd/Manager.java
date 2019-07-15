package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NOT_ENOUGH_POSITION_EXCEPTION;
import com.thoughtworks.tdd.exception.UNRECOGNIZED_PARKING_TICKET_EXCEPTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager {
  private List<Parkable> parkables = new ArrayList<>();

  public Manager(Parkable... parkables) {
    this.parkables.addAll(Arrays.asList(parkables));
  }

  public ParkingTicket park(Car car) {
    if (parkables.stream().allMatch(x -> x.isAllFull())) {
      throw new NOT_ENOUGH_POSITION_EXCEPTION();
    }
    for (Parkable parkable : parkables) {
      if (!parkable.isAllFull()) {
        return parkable.park(car);
      }
    }
    throw new NOT_ENOUGH_POSITION_EXCEPTION();
  }

  public Car fetch(ParkingTicket ticket) {
    for (Parkable parkable : parkables) {
      if (parkable.containsTicket(ticket)) {
        return parkable.fetch(ticket);
      }
    }
    throw new UNRECOGNIZED_PARKING_TICKET_EXCEPTION();
  }

}
