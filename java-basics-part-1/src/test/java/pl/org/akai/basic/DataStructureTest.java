package pl.org.akai.basic;

import org.junit.jupiter.api.Test;

import java.util.*;

class DataStructureTest {

    @Test
    void array() {
        String[] stringArray = new String[5];
        System.out.println(stringArray.length);
    }

    @Test
    void list() {
        List<String> stringLinkedList = new LinkedList<>();
        List<String> stringArrayList = new ArrayList<>();

        String str = "Test";
        stringArrayList.add(str);
        stringArrayList.addAll(List.of("1", "2", "3"));
        stringArrayList.remove(str);
        stringArrayList.remove(0);

        int size = stringArrayList.size();
        System.out.println(size);
        System.out.println(stringArrayList);
    }

    @Test
    void stack() {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Test");
        stringStack.push("Test 2");
        stringStack.pop();
        System.out.println(stringStack.empty());
        System.out.println(stringStack.peek());
    }

    @Test
    void queue() {
        Queue<String> stringQueue = new LinkedList<>();
        stringQueue.add("Test");
        stringQueue.add("Test2");
        stringQueue.poll();
        stringQueue.remove();
        System.out.println(stringQueue.isEmpty());
        String x = stringQueue.poll();
        System.out.println(x);
        String y = stringQueue.remove();
        System.out.println(y);
    }

    @Test
    void set() {
        Set<String> set = new HashSet<>();
        set.add("Test");
        set.add("Test2");
        set.add("Test");
        System.out.println(set.size());
        System.out.println(set);

    }

    @Test
    void map() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Test", 1);
        map.put("Test2", 2);
        map.put("Test", 3);
        System.out.println(map);
        System.out.println(map.get("Test"));
        System.out.println(map.keySet());
    }
}
