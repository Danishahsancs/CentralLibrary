package com.zipcodewilmington.centrallibrary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressTest {

    @Test
    public void testConstructorAndGetters() {
        Address address = new Address("123 Main St", "Wilmington", "DE", "19801");

        assertEquals("123 Main St", address.getStreet());
        assertEquals("Wilmington", address.getCity());
        assertEquals("DE", address.getState());
        assertEquals("19801", address.getZipcode());
    }

    @Test
    public void testSetters() {
        Address address = new Address("123 Main St", "Wilmington", "DE", "19801");

        address.setStreet("456 Elm St");
        address.setCity("Newark");
        address.setState("NJ");
        address.setZipcode("7101");

        assertEquals("456 Elm St", address.getStreet());
        assertEquals("Newark", address.getCity());
        assertEquals("NJ", address.getState());
        assertEquals("7101", address.getZipcode());
    }

    @Test
    public void testToStringOverride() {
        Address address = new Address("123 Main St", "Wilmington", "DE", "19801");
        String expected = "123 Main St Wilmington DE 19801";
        assertEquals(expected, address.toString());
    }

}
