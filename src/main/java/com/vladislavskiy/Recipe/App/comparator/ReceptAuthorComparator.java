package com.vladislavskiy.Recipe.App.comparator;

import com.vladislavskiy.Recipe.App.entity.Recept;

import java.util.Comparator;

public class ReceptAuthorComparator implements Comparator<Recept> {
    @Override
    public int compare(Recept a, Recept b) {
        return a.getUser().compareTo(b.getUser());
    }
}
