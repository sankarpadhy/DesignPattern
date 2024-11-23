package behavioral.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopicListTest {
    private TopicList topicList;
    private Iterator<String> iterator;
    private String[] topics;

    @BeforeEach
    void setUp() {
        topics = new String[]{"Java", "Python", "JavaScript", "C++"};
        topicList = new TopicList(topics);
        iterator = topicList.getIterator();
    }

    @Test
    void testIteratorHasNext() {
        assertTrue(iterator.hasNext());
        for (int i = 0; i < topics.length; i++) {
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorNext() {
        for (String topic : topics) {
            assertEquals(topic, iterator.next());
        }
        assertNull(iterator.next());
    }

    @Test
    void testIteratorReset() {
        // Traverse the entire list
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertFalse(iterator.hasNext());

        // Reset and check if we can traverse again
        iterator.reset();
        assertTrue(iterator.hasNext());
        assertEquals(topics[0], iterator.next());
    }

    @Test
    void testEmptyTopicList() {
        TopicList emptyList = new TopicList(new String[]{});
        Iterator<String> emptyIterator = emptyList.getIterator();
        
        assertFalse(emptyIterator.hasNext());
        assertNull(emptyIterator.next());
    }
}
