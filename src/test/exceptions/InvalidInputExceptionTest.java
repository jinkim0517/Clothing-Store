package exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class InvalidInputExceptionTest {
    InvalidInputException e = new InvalidInputException();

    @Test
    public void constructorTest() {
        assertNull(e.getMessage());
    }
}
