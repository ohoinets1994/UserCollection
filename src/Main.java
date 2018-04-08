import java.util.LinkedList;
import java.util.TreeSet;

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

        System.out.println(userTreeSet.remove(15));
//        System.out.println(userTreeSet.add(null));
        System.out.println(userTreeSet.size());
        System.out.println(userTreeSet.contains(9));
//        System.out.println(userTreeSet.isEmpty());
        System.out.println(userTreeSet.maxElement());
        System.out.println(userTreeSet.minElement());


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
