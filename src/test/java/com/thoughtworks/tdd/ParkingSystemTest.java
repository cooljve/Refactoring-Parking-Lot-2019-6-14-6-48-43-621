package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.tdd.Constant.*;
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
    ParkingTicket ticket = (ParkingTicket) parkingBoy.park(car).getObject();
    Car fetchedCar = (Car) parkingBoy.fetch(ticket).getObject();
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
    ParkingTicket ticket = (ParkingTicket) parkingBoy.park(firstCar).getObject();
    ParkingTicket ticket1 = (ParkingTicket) parkingBoy.park(secondCar).getObject();
    Car fetchedCar = (Car) parkingBoy.fetch(ticket).getObject();
    Car fetchedCar1 = (Car) parkingBoy.fetch(ticket1).getObject();
    //then
    assertEquals(firstCar, fetchedCar);
    assertEquals(secondCar, fetchedCar1);
  }

  @Test
  public void should_null_when_fetch_car_with_wrong_ticket() {
    //give
    Customer customer = new Customer();
    customer.setParkingTicket(new ParkingTicket());
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    Car fetchedCar = (Car) parkingBoy.fetch(customer.getParkingTicket()).getObject();
    Car fetchedCar1 = (Car) parkingBoy.fetch(null).getObject();
    //then
    assertEquals(null, fetchedCar);
    assertEquals(null, fetchedCar1);
  }

  @Test
  public void should_null_when_fetch_car_give_used_ticket() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    ParkingTicket ticket = (ParkingTicket) parkingBoy.park(customer.getCar()).getObject();
    customer.setParkingTicket(ticket);
    Car fetchedCar = (Car) parkingBoy.fetch(customer.getParkingTicket()).getObject();
    Car fetchedCar1 = (Car) parkingBoy.fetch(customer.getParkingTicket()).getObject();
    //then
    assertEquals(customer.getCar(), fetchedCar);
    assertEquals(null, fetchedCar1);
  }

  @Test
  public void should_null_when_park_car_give_full_parkingLot() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    ParkingLot parkingLot = mock(ParkingLot.class);
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot);
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    when(parkingLot.isFull()).thenReturn(true);
    ParkingTicket ticket = (ParkingTicket) parkingBoy.park(customer.getCar()).getObject();
    customer.setParkingTicket(ticket);
    //then
    assertEquals(null, customer.getParkingTicket());
  }

  @Test
  public void should_return_null_ticket_when_park_car_give_parkedCar() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    parkingBoy.park(customer.getCar());
    ParkingTicket ticket = (ParkingTicket) parkingBoy.park(customer.getCar()).getObject();
    //then
    assertEquals(null, ticket);
  }

  @Test
  public void should_return_null_ticket_when_park_car_give_null_car() {
    //give
    Customer customer = new Customer();
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    ParkingTicket ticket = (ParkingTicket) parkingBoy.park(customer.getCar()).getObject();
    //then
    assertEquals(null, ticket);
  }

  @Test
  public void should_return_error_string_when_fetch_car_give_used_ticket() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    ParkingTicket ticket = (ParkingTicket) parkingBoy.park(customer.getCar()).getObject();
    customer.setParkingTicket(ticket);
    parkingBoy.fetch(customer.getParkingTicket());
    Response response1 = parkingBoy.fetch(customer.getParkingTicket());
    //then
    assertEquals(WRONG_TICKET, response1.getMessage());
  }

  @Test
  public void should_return_error_string_when_fetch_car_give_no_ticket() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(new ParkingLot(10));
    ParkingBoy parkingBoy = new ParkingBoy();
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    Response response = parkingBoy.fetch(null);
    //then
    assertEquals(NO_TICKET, response.getMessage());
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
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    when(parkingLot.isFull()).thenReturn(true);
    Response response = parkingBoy.park(customer.getCar());
    //then
    assertEquals(PARKING_LOT_IS_FULL, response.getMessage());
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
    //when
    parkingBoy.setParkingLotList(parkingLotList);
    when(parkingLot1.isFull()).thenReturn(true);
    Response response = parkingBoy.park(customer.getCar());
    //then
    assertNotNull(response.getObject());
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
  public void should_return_null_when_distribute_different_parkingBoys_park_and_fetch_same_car_give_customer_and_manager() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    Manager manager = expectManagerWithParkingBoysAndParkingLots();
    //when
    Response response = manager.distributeParkingBoyToPark(manager.getParkingBoyList().get(0), customer);
    customer.setParkingTicket((ParkingTicket) response.getObject());
    Response response1 = manager.distributeParkingBoyToFetch(manager.getParkingBoyList().get(1), customer);
    //then
    assertNotNull(response.getObject());
    assertNull(response1.getObject());
  }

  @Test
  public void should_return_car_when_distribute_same_parkingBoy_park_and_fetch_same_car_give_customer_and_manager() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    Manager manager = expectManagerWithParkingBoysAndParkingLots();
    //when
    Response response = manager.distributeParkingBoyToPark(manager.getParkingBoyList().get(0), customer);
    customer.setParkingTicket((ParkingTicket) response.getObject());
    Response response1 = manager.distributeParkingBoyToFetch(manager.getParkingBoyList().get(0), customer);
    //then
    assertNotNull(response.getObject());
    assertNotNull(response1.getObject());
  }

  @Test
  public void should_return_car_when_distribute_manager_park_and_fetch_same_car_give_customer_and_manager() {
    //give
    Customer customer = new Customer();
    customer.setCar(new Car());
    Manager manager = expectManagerWithParkingBoysAndParkingLots();
    //when
    Response response = manager.distributeParkingBoyToPark(manager, customer);
    customer.setParkingTicket((ParkingTicket) response.getObject());
    Response response1 = manager.distributeParkingBoyToFetch(manager, customer);
    //then
    assertNotNull(response.getObject());
    assertNotNull(response1.getObject());
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