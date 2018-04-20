import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearch(data, 10));

        UserTreeSet<Integer> userTreeSet = new UserTreeSet<>();
        userTreeSet.add(15);
        userTreeSet.add(25);
        userTreeSet.add(42);
        userTreeSet.add(9);
        userTreeSet.add(2);
        userTreeSet.add(3);
        userTreeSet.add(1);
        userTreeSet.add(13);
        userTreeSet.add(14);
        userTreeSet.add(12);
        userTreeSet.add(11);
        userTreeSet.add(10);
        System.out.println(userTreeSet.size());
        userTreeSet.print();
    }

//    Написать метод, который принимает на вход строку и подсчитывает кол-во уникальных символов в строке.
//    * использовать структуру данных из Collection framework.
    private static int countChars(String string) {
        char[] chars = string.toCharArray();
        Set<Character> characters = new HashSet<>();

        for (char c : chars) {
            characters.add(c);
        }
        return characters.size();
    }

//    Написать метод, который принимает на вход строку, и подсчитывает кол-во вхождений каждого из символов в строке.
//    * использовать структуру данных из Collection framework.
    private static Map<Character, Integer> numberOccurrences(String string) {
        char[] chars = string.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (char c: chars) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
        return map;
    }
    private static int binarySearch(int[] data, int element) {
        int index = -1;
        int first = 0;
        int last = data.length - 1;

        while (first <= last) {
            int mid = (first + last) / 2;
            if (data[mid] > element)
                last = mid - 1;
            else if (data[mid] < element)
                first = mid + 1;

            else {
                index = mid;
                break;
            }
        }

        return index;
    }
}
