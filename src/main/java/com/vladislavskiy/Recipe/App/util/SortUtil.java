package com.vladislavskiy.Recipe.App.util;

import com.vladislavskiy.Recipe.App.entity.Recept;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {
    public static void sortRecipes(List<Recept> recepts, Comparator<Recept> comparator, String sortOrder) {
        if (sortOrder.equalsIgnoreCase("desc")) {
            Comparator comparator2 = Collections.reverseOrder(comparator);
            Collections.sort(recepts, comparator2);
        } else {
            Collections.sort(recepts, comparator);
        }
    }
}
