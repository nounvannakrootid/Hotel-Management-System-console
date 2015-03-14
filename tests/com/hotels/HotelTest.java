package com.hotels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by kaustavc on 3/14/2015.
 * Reception test file for a hotel transaction
 */
public class HotelTest {
    List<Room> rooms;
    Hotel hotel;
    Customer sukhvindar;

    @Before
    public void setUp() throws Exception {
        rooms = new ArrayList<Room>();
        rooms.add(new Room(2, 3, 101, 2));

        hotel = new Hotel("Treejon Guest House", rooms, "Puri");
        hotel.addManager("Santosh");
        sukhvindar = new Customer("Sukhvindar", new City("Chandigarh"), 42, 1234);
    }

    @After
    public void tearDown() throws Exception {
        rooms = new ArrayList<Room>();
        hotel = null;
        sukhvindar = null;
    }

    @Test
    public void testCustomerShouldGetRoomIfAvailable() {
        Room rm = hotel.getRoom(sukhvindar.getDetails(), 3);
        assertNotNull(rm);
    }

    @Test
    public void testCustomerShouldGetRoomIfNotAvailable() {
        Room rm = hotel.getRoom(sukhvindar.getDetails(), 4);
        assertNull(rm);
    }

    @Test
    public void testCustomerShouldGetRoomIfMultipleRoomAvailableFor3People() {
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(2, 3, 201, 2));
        rooms.add(new Room(2, 3, 202, 2));
        rooms.add(new Room(1, 2, 102, 1));

        Room rm = hotel.getRoom(sukhvindar.getDetails(), 3);
        assertNotNull(rm);
    }

    @Test
    public void testHotelShouldAddANewRoom() {
        Room rm = new Room(3, 4, 301, 3);

        hotel.addRoom(rm);
        assertEquals(2, hotel.totalRooms());

        new Room(3, 4, 301, 3);
        hotel.addRoom(rm);
        assertEquals(3, hotel.totalRooms());
    }
}