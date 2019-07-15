package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NOT_ENOUGH_POSITION_EXCEPTION;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
  private final int capacity;
  private Map<ParkingTicket, Car> map = new HashMap<>();

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  public Car fetch(ParkingTicket ticket) {
    return map.remove(ticket);
  }

  public ParkingTicket park(Car car) {
    if (isFull()) {
      throw new NOT_ENOUGH_POSITION_EXCEPTION();
    }
    ParkingTicket ticket = new ParkingTicket();
    map.put(ticket, car);
    return ticket;
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

  public int getAvailabel(){
    return capacity - map.size();
  }

  public double getAvailabelRate(){
    return getAvailabel() / (double) capacity;
  }
}
