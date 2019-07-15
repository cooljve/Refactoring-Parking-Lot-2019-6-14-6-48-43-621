package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
  private final int capacity;
  private Map<ParkingTicket, Car> map = new HashMap<>();

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  public boolean isFull() {
    return capacity <= map.size();
  }

  public Map<ParkingTicket, Car> getMap() {
    return map;
  }

  public void setMap(Map<ParkingTicket, Car> map) {
    this.map = map;
  }

  public int getCapacity() {
    return capacity;
  }
}
