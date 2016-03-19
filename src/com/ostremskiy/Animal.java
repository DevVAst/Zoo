package com.ostremskiy;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by DevAs on 12.03.2016.
 */
enum AnimalType{
    MAMMALS, REPTILES, BIRDS, INSECTS, SPIDERS, AQUATIC
}
public class Animal {
    private int animalNumber;
    private Map<Food, Integer> foodKgPerDay;
    private int neededSquare;
    private int cleaningPricePerDay;
    private AnimalType type;
    private Cage cage;

    public Animal(int animalNumber, TreeMap<Food, Integer> foodKgPerDay, int neededSquare, int cleaningPricePerDay, AnimalType type, Cage cage) {
        this.animalNumber = animalNumber;
        this.foodKgPerDay = foodKgPerDay;
        this.neededSquare = neededSquare;
        this.cleaningPricePerDay = cleaningPricePerDay;
        this.type = type;
        this.cage = cage;

    }

    public int getCleaningPricePerDay() {
        return cleaningPricePerDay;
    }

    public AnimalType getType() {
        return type;
    }

    public int priceFoodPerDay(){
        Set<Map.Entry<Food, Integer>> foodKgPerDatList = foodKgPerDay.entrySet();
        return foodKgPerDatList.stream().mapToInt(entry -> entry.getKey().getPricePerKg() * entry.getValue()).sum();
    }
    public Map<Food, Integer> getFoodKgPerDay() {
        return foodKgPerDay;
    }
    public int getAnimalNumber() {
        return animalNumber;
    }

    void changeCage(Cage newCage){
        this.cage = newCage;
    }

    public boolean intoCagesAnimalList(){
        return cage.addAnimal(this);
    }
    public Cage getCage() {
        return cage;
    }

    public int getNeededSquare() {
        return neededSquare;
    }

    @Override
    public String toString() {
        return "Animal " + animalNumber;
    }
}
