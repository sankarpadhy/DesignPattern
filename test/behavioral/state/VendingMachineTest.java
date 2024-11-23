package behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {
    private VendingMachine machine;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        machine = new VendingMachine();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testInitialState() {
        assertTrue(machine.getCurrentState() instanceof NoCoinState);
        assertEquals(0, machine.getCurrentAmount());
    }

    @Test
    void testInsertCoin() {
        machine.insertCoin(25);
        assertTrue(machine.getCurrentState() instanceof HasCoinState);
        assertEquals(25, machine.getCurrentAmount());
    }

    @Test
    void testInsufficientAmount() {
        machine.insertCoin(20);
        machine.selectProduct("Coke");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Insufficient amount"));
        assertTrue(machine.getCurrentState() instanceof HasCoinState);
    }

    @Test
    void testSuccessfulPurchase() {
        machine.insertCoin(25);
        machine.selectProduct("Coke");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Dispensing Coke"));
        assertTrue(machine.getCurrentState() instanceof NoCoinState);
        assertEquals(0, machine.getCurrentAmount());
    }

    @Test
    void testCancelTransaction() {
        machine.insertCoin(25);
        machine.cancel();
        
        assertTrue(machine.getCurrentState() instanceof NoCoinState);
        assertEquals(0, machine.getCurrentAmount());
        assertTrue(outputStream.toString().contains("Returning 25"));
    }

    @Test
    void testUnavailableProduct() {
        machine.insertCoin(50);
        machine.selectProduct("Invalid");
        
        assertTrue(machine.getCurrentState() instanceof HasCoinState);
        assertTrue(outputStream.toString().contains("Product not available"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
