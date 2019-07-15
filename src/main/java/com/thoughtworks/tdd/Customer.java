package com.thoughtworks.tdd;

public class Customer {
  private Car car;
  private ParkingTicket parkingTicket;

  public ParkingTicket getParkingTicket() {
    return parkingTicket;
  }

  public void setParkingTicket(ParkingTicket parkingTicket) {
    this.parkingTicket = parkingTicket;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }
}
