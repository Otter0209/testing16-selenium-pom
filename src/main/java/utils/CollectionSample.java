package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionSample {

    public static void collectionSample() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        for(int number : numbers) {
            System.out.println(number);
        }

        List<String[]> users = new ArrayList<>();
        users.add(new String[] {"Admin", "admin123", "success"});
        users.add(new String[] {"Admin", "admin", "fail"});
        users.add(new String[] {"Admin123", "admin123", "fail"});

        for(String[] user : users) {
//            System.out.println(user[0] + " " + user[1] + " " + user[2]);
            System.out.println(Arrays.toString(user));
        }
    }

    //    khai niem Collection
    public static void main(String[] args) {
        System.out.println("Testing16");
        collectionSample();
    }
}