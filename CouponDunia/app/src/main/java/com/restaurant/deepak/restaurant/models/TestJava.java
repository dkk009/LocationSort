package com.restaurant.deepak.restaurant.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TestJava {

    public static void main(String args[]) {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setBrandName("abcd");
        List<Category> list = new ArrayList<>();


        Category category1 = new Category();
        category1.setName("Abcd");
        list.add(category1);
        restaurant1.setCategories(list);
        Category category2 = category1.clone();
        System.out.println("Name1:" + category1.getName() +",Name2:" + category2.getName());
        category2.setName("xyz");
        System.out.println("Name1:" + category1.getName() + ",Name2:" + category2.getName());
        System.out.println("Name1:" + restaurant1.getBrandName() + ",Category1:" + restaurant1.getCategories().get(0).getName());
        Restaurant restaurant2 = restaurant1.clone();
        restaurant2.getCategories().get(0).setName("2-category");
        System.out.println("Name1:" + restaurant1.getBrandName() + ",Category1:" + restaurant1.getCategories().get(0).getName());
    }
}
