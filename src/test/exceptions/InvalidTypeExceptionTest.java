package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class InvalidTypeExceptionTest {
    InvalidTypeException e = new InvalidTypeException();

    @Test
    public void constructorTest() {
        assertNull(e.getMessage());
    }
}
