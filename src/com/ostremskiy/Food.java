package com.ostremskiy;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by DevAs on 12.03.2016.
 */
final class FoodList{
    public static Map<String, Food> foodList = new TreeMap<>();

    public static boolean addFood(Food newFood){
        String newFoodName = newFood.getName();
        if(foodList.containsKey(newFoodName))
            return false;
        else
            foodList.put(newFood.getName(), newFood);
        return true;
    }
}
enum FoodEnum{
    MEAT, VEGETABLES, FRUIT, OTHER;
}
public class Food implements Comparable {

    private String name;
    private int pricePerKg;
    private FoodEnum type;


    public String getName() {
        return name;
    }

    public FoodEnum getType() {
        return type;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public Food(String name, int pricePerKg, FoodEnum type) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.type = type;
    }

    @Override
    public int compareTo(Object o) {
        Food food2 = (Food) o;
        return name.compareToIgnoreCase(food2.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
