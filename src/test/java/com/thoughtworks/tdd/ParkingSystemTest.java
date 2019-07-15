package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.NOT_ENOUGH_POSITION_EXCEPTION;
import com.thoughtworks.tdd.exception.TICKET_MISSING_EXCEPTION;
import com.thoughtworks.tdd.exception.UNRECOGNIZED_PARKING_TICKET_EXCEPTION;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParkingSystemTest {

  @Test
  public void should_return_car_when_park_car_fetch_car() {
    //give
    Car car = new Car();
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    ParkingTicket ticket = parkingBoy.park(car);
    Car fetchedCar = parkingBoy.fetch(ticket);
    //then
    assertEquals(car, fetchedCar);
  }

  @Test
  public void should_return_right_car_when_park_car_fetch_car() {
    //give
    Car firstCar = new Car();
    Car secondCar = new Car();
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    ParkingTicket ticket = parkingBoy.park(firstCar);
    ParkingTicket ticket1 = parkingBoy.park(secondCar);
    Car fetchedCar = parkingBoy.fetch(ticket);
    Car fetchedCar1 = parkingBoy.fetch(ticket1);
    //then
    assertEquals(firstCar, fetchedCar);
    assertEquals(secondCar, fetchedCar1);
  }

  @Test
  public void should_throw_unrecognized_parking_ticket_exception_when_fetch_car_give_used_ticket() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    ParkingTicket ticket = parkingBoy.park(customer.getCar());
    customer.setParkingTicket(ticket);
    parkingBoy.fetch(customer.getParkingTicket());
    //then
    assertThrows(UNRECOGNIZED_PARKING_TICKET_EXCEPTION.class, () -> parkingBoy.fetch(customer.getParkingTicket()));
  }

  @Test
  public void should_throw_ticket_missing_exception_when_fetch_car_give_no_ticket() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    //then
    assertThrows(TICKET_MISSING_EXCEPTION.class, () -> parkingBoy.fetch(null));
  }

  @Test
  public void should_return_full_parkingLot_message_when_park_car_give_full_parkingLot() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    ParkingLot parkingLot = mock(ParkingLot.class);
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot);
    ParkingBoy parkingBoy = new ParkingBoy();
    when(parkingLot.isFull()).thenReturn(true);
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    //then
    assertThrows(NOT_ENOUGH_POSITION_EXCEPTION.class, () -> parkingBoy.park(customer.getCar()));
  }

  @Test
  public void should_return_not_null_ticket_when_park_car_give_a_full_parkingLot_and_an_empty_parkingLot() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    ParkingLot parkingLot1 = mock(ParkingLot.class);
    ParkingLot parkingLot2 = new ParkingLot(10);
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot1);
    parkingLotList.add(parkingLot2);
    ParkingBoy parkingBoy = new ParkingBoy();
    when(parkingLot1.isFull()).thenReturn(true);
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    ParkingTicket ticket = parkingBoy.park(customer.getCar());
    //then
    assertNotNull(ticket);
  }

  @Test
  public void should_park_at_parkingLot3_when_smart_parkingBoy_park_car_give_three_different_capacity_parkingLots() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(2);
    ParkingLot parkingLot3 = new ParkingLot(3);
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot1);
    parkingLotList.add(parkingLot2);
    parkingLotList.add(parkingLot3);
    Manager manager = new Manager(parkingLotList);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    List<ParkingBoy> parkingBoyList = new ArrayList<>();
    parkingBoyList.add(smartParkingBoy);
    manager.distribute(smartParkingBoy, parkingLotList);
    //when
    smartParkingBoy.park(customer.getCar());
    //then
    assertEquals(1, parkingLot3.getMap().size());
  }

  @Test
  public void should_park_at_parkingLot2_when_super_smart_parikingBoy_park_car_give_two_different_capacity_parkingLots() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    ParkingLot parkingLot1 = mock(ParkingLot.class);
    ParkingLot parkingLot2 = mock(ParkingLot.class);
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot1);
    parkingLotList.add(parkingLot2);
    Manager manager = new Manager(parkingLotList);
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    List<ParkingBoy> parkingBoyList = new ArrayList<>();
    parkingBoyList.add(superSmartParkingBoy);
    manager.distribute(superSmartParkingBoy, parkingLotList);
    Map<ParkingTicket, Car> map1 = expectMapWithSize(6);
    Map<ParkingTicket, Car> map2 = expectMapWithSize(10);
    //when
    when(parkingLot1.getMap()).thenReturn(map1);
    when(parkingLot1.getCapacity()).thenReturn(10);
    when(parkingLot2.getMap()).thenReturn(map2);
    when(parkingLot2.getCapacity()).thenReturn(20);
    superSmartParkingBoy.park(customer.getCar());
    //then
    assertEquals(7, parkingLot1.getMap().size());
    assertEquals(10, parkingLot2.getMap().size());
  }

  @Test
  public void should_return_car_when_distribute_same_parkingBoy_park_and_fetch_same_car_give_customer_and_manager() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    Manager manager = expectManagerWithParkingBoysAndParkingLots();
    //when
    ParkingTicket ticket = manager.distributeParkingBoyToPark(manager.getParkingBoyList().get(0), customer);
    customer.setParkingTicket(ticket);
    Car car = manager.distributeParkingBoyToFetch(manager.getParkingBoyList().get(0), customer);
    //then
    assertNotNull(ticket);
    assertNotNull(car);
  }

  @Test
  public void should_return_car_when_distribute_manager_park_and_fetch_same_car_give_customer_and_manager() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    Manager manager = expectManagerWithParkingBoysAndParkingLots();
    //when
    ParkingTicket ticket = manager.distributeParkingBoyToPark(manager, customer);
    customer.setParkingTicket(ticket);
    Car car = manager.distributeParkingBoyToFetch(manager, customer);
    //then
    assertNotNull(ticket);
    assertNotNull(car);
  }

  private Manager expectManagerWithParkingBoysAndParkingLots() {
    List<ParkingLot> parkingLotList = expectParkingLotListByCount(4);
    Manager manager = new Manager(parkingLotList);
    ParkingBoy parkingBoy = new ParkingBoy();
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
    SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
    List<ParkingBoy> parkingBoyList = new ArrayList<>();
    parkingBoyList.add(parkingBoy);
    parkingBoyList.add(smartParkingBoy);
    parkingBoyList.add(superSmartParkingBoy);
    manager.setParkingBoyList(parkingBoyList);
    manager.distribute(parkingBoy, parkingLotList.subList(0, 1));
    manager.distribute(smartParkingBoy, parkingLotList.subList(1, 2));
    manager.distribute(superSmartParkingBoy, parkingLotList.subList(2, 3));
    return manager;
  }

  private List<ParkingLot> expectParkingLotListByCount(int count) {
    List<ParkingLot> parkingLotList = new ArrayList<>();
    for (int i = count; i > 0; i--) {
      ParkingLot parkingLot = new ParkingLot(10);
      parkingLotList.add(parkingLot);
    }
    return parkingLotList;
  }

  private Map<ParkingTicket, Car> expectMapWithSize(int i2) {
    Map<ParkingTicket, Car> map1 = new HashMap<>();
    for (int i = 0; i < i2; i++) {
      map1.put(new ParkingTicket(), null);
    }
    return map1;
  }


}