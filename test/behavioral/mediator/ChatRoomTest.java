package behavioral.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ChatRoomTest {
    private ChatMediator chatRoom;
    private User john;
    private User alice;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        chatRoom = new ChatRoom();
        john = new ChatUser(chatRoom, "John");
        alice = new ChatUser(chatRoom, "Alice");
        chatRoom.addUser(john);
        chatRoom.addUser(alice);

        // Capture System.out for testing
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testMessageSending() {
        john.send("Hello Alice!");
        String output = outputStream.toString();
        
        assertTrue(output.contains("John sends: Hello Alice!"));
        assertTrue(output.contains("Alice receives: Hello Alice!"));
    }

    @Test
    void testMessageNotReceivedBySender() {
        john.send("Test message");
        String output = outputStream.toString();
        
        assertFalse(output.contains("John receives: Test message"));
    }

    @Test
    void testMultipleUsers() {
        User bob = new ChatUser(chatRoom, "Bob");
        chatRoom.addUser(bob);
        
        alice.send("Hello everyone!");
        String output = outputStream.toString();
        
        assertTrue(output.contains("Bob receives: Hello everyone!"));
        assertTrue(output.contains("John receives: Hello everyone!"));
        assertFalse(output.contains("Alice receives: Hello everyone!"));
    }

    @Test
    void testEmptyMessage() {
        john.send("");
        String output = outputStream.toString();
        
        assertTrue(output.contains("John sends: "));
        assertTrue(output.contains("Alice receives: "));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
