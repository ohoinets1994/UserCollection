import org.junit.Test;

import static org.junit.Assert.*;

public class UserLinkedListTest {

    @Test
    public void size() {
        UserLinkedList<String> userLinkedList = new UserLinkedList<>();
        userLinkedList.add("a");
        userLinkedList.add("b");
        userLinkedList.add("c");
        int expected = 3;
        int actual = userLinkedList.size();
        assertEquals(expected, actual);
        userLinkedList.remove("a");
        userLinkedList.remove("b");
        userLinkedList.remove("c");
        assertEquals(0, userLinkedList.size());
        UserLinkedList<String> userLinkedList2 = new UserLinkedList<>();
        assertEquals(0, userLinkedList2.size());
    }

    @Test
    public void isEmpty() {
        UserLinkedList<String> userLinkedList = new UserLinkedList<>();
        userLinkedList.add("a");
        assertEquals(false, userLinkedList.isEmpty());

        UserLinkedList<String> userLinkedList2 = new UserLinkedList<>();
        assertEquals(true, userLinkedList2.isEmpty());
    }

    @Test
    public void remove() {
        UserLinkedList<String> userLinkedList = new UserLinkedList<>();
        userLinkedList.add("a");
        userLinkedList.add("b");
        userLinkedList.add("c");
        assertEquals(true, userLinkedList.remove("a"));
        assertEquals(true, userLinkedList.remove("b"));
        assertEquals(true, userLinkedList.remove("c"));
        assertEquals(false, userLinkedList.remove("d"));
        assertEquals(false, userLinkedList.remove("a"));
        assertEquals(false, userLinkedList.remove(null));

        UserLinkedList<Integer> userLinkedList2 = new UserLinkedList<>();
        userLinkedList2.add(1);
        userLinkedList2.add(2);
        userLinkedList2.add(3);
        assertEquals(true, userLinkedList2.remove(2));
    }

    @Test
    public void contains() {
        UserLinkedList<String> userLinkedList = new UserLinkedList<>();
        userLinkedList.add("a");
        userLinkedList.add("b");
        userLinkedList.add("c");
        assertEquals(true, userLinkedList.contains("a"));
        assertEquals(false, userLinkedList.contains("d"));
    }

    @Test
    public void add() {
        UserLinkedList<String> userLinkedList = new UserLinkedList<>();
        assertEquals(true, userLinkedList.add("A"));
    }
}