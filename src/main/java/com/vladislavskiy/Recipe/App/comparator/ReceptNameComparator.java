package com.vladislavskiy.Recipe.App.comparator;

import com.vladislavskiy.Recipe.App.entity.Recept;

import java.util.Comparator;

public class ReceptNameComparator implements Comparator<Recept> {

    public int compare(Recept a, Recept b) {
        return a.getName().compareTo(b.getName());
    }

}
