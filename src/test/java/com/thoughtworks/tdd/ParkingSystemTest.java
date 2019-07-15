package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NOT_ENOUGH_POSITION_EXCEPTION;
import com.thoughtworks.tdd.exception.TICKET_MISSING_EXCEPTION;
import com.thoughtworks.tdd.exception.UNRECOGNIZED_PARKING_TICKET_EXCEPTION;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParkingSystemTest {

  @Test
  public void should_return_car_when_park_car_fetch_car() {
    Car car = new Car();
    ParkingLot parkingLot = new ParkingLot(10);
    ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

    ParkingTicket ticket = parkingBoy.park(car);
    Car fetchedCar = parkingBoy.fetch(ticket);

    assertEquals(car, fetchedCar);
  }

  @Test
  public void should_return_right_car_when_park_car_fetch_car() {
    Car firstCar = new Car();
    Car secondCar = new Car();
    ParkingLot parkingLot = new ParkingLot(10);
    ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

    ParkingTicket ticket = parkingBoy.park(firstCar);
    ParkingTicket ticket1 = parkingBoy.park(secondCar);
    Car fetchedCar = parkingBoy.fetch(ticket);
    Car fetchedCar1 = parkingBoy.fetch(ticket1);

    assertEquals(firstCar, fetchedCar);
    assertEquals(secondCar, fetchedCar1);
  }

  @Test
  public void should_throw_unrecognized_parking_ticket_exception_when_fetch_car_give_used_ticket() {
    Car car = new Car();
    ParkingLot parkingLot = new ParkingLot(10);
    ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

    ParkingTicket ticket = parkingBoy.park(car);
    parkingBoy.fetch(ticket);

    assertThrows(UNRECOGNIZED_PARKING_TICKET_EXCEPTION.class, () -> parkingBoy.fetch(ticket));
  }

  @Test
  public void should_throw_ticket_missing_exception_when_fetch_car_give_no_ticket() {
    ParkingLot parkingLot = new ParkingLot(10);
    ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

    assertThrows(TICKET_MISSING_EXCEPTION.class, () -> parkingBoy.fetch(null));
  }

  @Test
  public void should_throw_not_enough_position_exception_when_park_car_give_full_parkingLot() {
    Car car = new Car();
    ParkingLot parkingLot = mock(ParkingLot.class);
    ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
    when(parkingLot.isFull()).thenReturn(true);

    assertThrows(NOT_ENOUGH_POSITION_EXCEPTION.class, () -> parkingBoy.park(car));
  }

  @Test
  public void should_return_not_null_ticket_when_park_car_give_a_full_parkingLot_and_an_empty_parkingLot() {
    Car car = new Car();
    ParkingLot parkingLot1 = mock(ParkingLot.class);
    ParkingLot parkingLot2 = new ParkingLot(10);
    ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
    when(parkingLot1.isFull()).thenReturn(true);

    ParkingTicket ticket = parkingBoy.park(car);

    assertNotNull(ticket);
  }

  @Test
  public void should_park_at_parkingLot3_when_smart_parkingBoy_park_car_give_three_different_capacity_parkingLots() {
    Car car = new Car();
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(2);
    ParkingLot parkingLot3 = new ParkingLot(3);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2, parkingLot3);

    smartParkingBoy.park(car);

    assertEquals(1, parkingLot3.getMap().size());
  }

  @Test
  public void should_park_at_parkingLot1_when_super_smart_parikingBoy_park_car_give_two_different_capacity_parkingLots() {
    Car car = new Car();
    ParkingLot parkingLot1 = new ParkingLot(10);
    ParkingLot parkingLot2 = new ParkingLot(20);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot1, parkingLot2);

    superSmartParkingBoy.park(car);

    assertEquals(1, parkingLot1.getMap().size());
  }

  @Test
  public void should_return_car_when_manage_specify_parker_park_and_fetch_give_mangage_with_a_parker() {
    Car car = new Car();
    ParkingLot parkingLot = new ParkingLot(10);
    Parker parkingBoy = new ParkingBoy(parkingLot);
    Manager manager = new Manager(parkingBoy);

    ParkingTicket ticket = manager.park(car);
    Car fetchedCar = manager.fetch(ticket);

    assertEquals(car, fetchedCar);
  }

  @Test
  public void should_throw_not_enough_position_exception_when_manage_park_give_full_parking_lot() {
    Car car = new Car();
    ParkingLot parkingLot = new ParkingLot(0);
    Manager manager = new Manager(parkingLot);

    assertThrows(NOT_ENOUGH_POSITION_EXCEPTION.class, () -> manager.park(car));
  }

  @Test
  public void should_should_throw_not_enough_position_exception_when_manage_park_give_full_parking_lot() {
    Car car = new Car();
    ParkingLot parkingLot1 = new ParkingLot(0);
    ParkingLot parkingLot2 = new ParkingLot(2);
    Manager manager = new Manager(parkingLot1, parkingLot2);

    ParkingTicket ticket = manager.park(car);
    Car fetchedCar = manager.fetch(ticket);

    assertEquals(car, fetchedCar);
  }


}