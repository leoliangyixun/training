package com.b5m.training.collection;

import java.util.TreeSet;
import static com.b5m.training.collection.Test.*;

/**
 * Created by yangkai on 16/5/22.
 */
public class TestTreeSet {

    public static void main(String[] args) {
        User p1 = new User("mm", 20);
        User p2 = new User("gg", 20);
        TreeSet<User> treeSet = new TreeSet<User>();
        treeSet.add(p1);
        treeSet.add(p2);

        System.out.println(treeSet);

    }
}
