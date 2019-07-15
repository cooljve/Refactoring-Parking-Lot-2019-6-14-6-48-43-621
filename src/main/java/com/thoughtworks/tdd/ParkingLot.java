package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NOT_ENOUGH_POSITION_EXCEPTION;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Parkable{
  private final int capacity;
  private Map<ParkingTicket, Car> map = new HashMap<>();

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  public Car fetch(ParkingTicket ticket) {
    return map.remove(ticket);
  }

  @Override
  public boolean isAllFull() {
    return isFull();
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

  public int getAvailabel(){
    return capacity - map.size();
  }

  public double getAvailabelRate(){
    return getAvailabel() / (double) capacity;
  }

  public boolean containsTicket(ParkingTicket ticket) {
    return map.containsKey(ticket);
  }
}
