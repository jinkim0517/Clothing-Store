package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class. Code retrieved from:
 * https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/test/ca/ubc/cpsc210/alarm/test/EventTest.java
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Sensor open at door");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", e.getDescription());
        assertTrue(isWithinASecond(e.getDate(), d));
    }

    // EFFECTS: checks if two dates are within a radius of 1 second of each other
    private boolean isWithinASecond(Date t1, Date t2) {
        if ((t1.getYear() == t2.getYear() & (t1.getMonth() == t2.getMonth()) & (t1.getDay() == t2.getDay())
                & (t1.getHours() == t2.getHours()) & (t1.getMinutes() == t2.getMinutes()))) {
            if ((t1.getSeconds() - 1 < t2.getSeconds()) & (t2.getSeconds() < t1.getSeconds() + 1)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
    }

    @Test
    public void equalsTest() {
        assertFalse(e.equals(null));
        assertFalse(e.equals(new Inventory()));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(13 * e.getDate().hashCode() + e.getDescription().hashCode(), e.hashCode());
    }
}
