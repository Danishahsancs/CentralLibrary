package com.zipcodewilmington.centrallibrary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest 
{   
   
@Test
    void testConstructorAndGetters() 
    {
    Person p = new Person("Danish", 21, "Danish@example.com", "1234567890", new Address("this street", "this city", "this state", "12312"));
    assertEquals("Danish", p.getName());
    assertEquals(Integer.valueOf(21), p.getAge());
    assertEquals("Danish@example.com", p.getEmail());
    assertEquals("1234567890", p.getPhoneNumber());
    }
@Test
    public void testSettersWork() // creates and object assigns values and then changes those values with Set and compares the result
    { 
        Person person = new Person("Danish", 21, "Danish@example.com", "1234567890",new Address("this street2", "this city2", "this state2", "22222"));
        person.setName("Younis");
        person.setAge(27);
        person.setEmail("Younis@email.com");
        person.setPhoneNumber("4408458137");

        assertEquals("Younis", person.getName());
        assertEquals(27, person.getAge());
        assertEquals("Younis@email.com", person.getEmail());
        assertEquals("4408458137", person.getPhoneNumber());
    }

@Test
    public void testSetNameThrowsExceptionForNull() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setName(null);
            });
            assertEquals("Name cannot be null or empty", exception.getMessage());
    }


@Test
    public void testSetNameLength()
    {
        
        Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 51; i++) {
            sb.append("A");
        }
        String longName = sb.toString();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setName(longName);
        });
        assertEquals("Name cannot be longer than 50 characters", exception.getMessage());
    }

@Test
    public void testSetNameInvalidCharacter() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setName("$$$$");
            });
            assertEquals("Name must start with a letter and contain only letters and spaces", exception.getMessage());
    }
    @Test
public void testSetNameFisrtLetter() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setName(" Danish");
            });
            assertEquals("Name must start with a letter and contain only letters and spaces", exception.getMessage());
    }
    
@Test
public void testSetAgeLessThanSeven() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setAge(4);
            });
            assertEquals("Age must be between 7 and 120", exception.getMessage());
    }
@Test
public void testSetAgeMoreThan120() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setAge(125);
            });
            assertEquals("Age must be between 7 and 120", exception.getMessage());
    }

    @Test
public void testSetEmailEmpty() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setEmail("");
            });
            assertEquals("Email cannot be null or empty", exception.getMessage());
    }
    @Test
public void testSetEmailNotValid() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setEmail("hello+");
            });
            assertEquals("Email is not valid", exception.getMessage());
    }

@Test
public void testSetPhoneNumberEmpty() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setPhoneNumber("");
            });
            assertEquals("Phone number cannot be null or empty", exception.getMessage());
        }
        @Test
public void testSetPhoneNNumberNotValid() 
    {
            Person person = new Person("Valid Name", 25, "email@example.com", "1234567890",new Address("this street", "this city", "this state", "12312"));
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setPhoneNumber("123-456-789");
            });
            assertEquals("Phone number must be 10 to 15 digits, optionally starting with +", exception.getMessage());
        }

}
    






