package nl.personal.prowars.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by maarten on 1/8/2015.
 */
public class SortedGameObjects {
    private ArrayList<Integer> game_objects;

    public SortedGameObjects() {
        this.game_objects = new ArrayList<Integer>();
    }

    public void addObject(GameObject obj) {
        int index = Collections.binarySearch(game_objects, 2);

    }
}
