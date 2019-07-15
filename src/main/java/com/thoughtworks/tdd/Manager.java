package com.thoughtworks.tdd;

import java.util.List;

public class Manager extends ParkingBoy {
  private List<ParkingBoy> parkingBoyList;
  private List<ParkingLot> parkingLotList;

  public Manager(List<ParkingLot> parkingLotList) {
    this.parkingLotList = parkingLotList;
  }

  public void distribute(ParkingBoy boy, List<ParkingLot> parkingLots) {
    boy.setParkingLotList(parkingLots);
  }

  public ParkingTicket distributeParkingBoyToPark(ParkingBoy boy, Customer customer) {
    return boy.park(customer.getCar());
  }

  public Car distributeParkingBoyToFetch(ParkingBoy boy, Customer customer) {
    return boy.fetch(customer.getParkingTicket());
  }

  public void setParkingBoyList(List<ParkingBoy> parkingBoyList) {
    this.parkingBoyList = parkingBoyList;
  }

  public List<ParkingLot> getParkingLotList() {
    return parkingLotList;
  }

  public void setParkingLotList(List<ParkingLot> parkingLotList) {
    this.parkingLotList = parkingLotList;
  }

  public List<ParkingBoy> getParkingBoyList() {
    return parkingBoyList;
  }
}
