package com.ostremskiy;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
/**
 * Created by DevAs on 12.03.2016.
 */
public class Zoo {
    private Set<Integer> animalNumbers;
    private Set<Integer> cagesNumbers;
    private List<Cage> cages;
    private List<Animal> animals;

    Zoo(){
        animalNumbers = new HashSet<>();
        cagesNumbers = new HashSet<>();
        cages = new ArrayList<>();
        animals = new ArrayList<>();

        //-----------
        FoodList.addFood(new Food("kasha1", 14, FoodEnum.FRUIT));
        FoodList.addFood(new Food("kivi", 5, FoodEnum.FRUIT));

        addNewCage(new Cage(CageTypes.LAND_CAGE, 0, 15, 5, 15, AnimalType.MAMMALS));
        addNewCage(new Cage(CageTypes.WATER_CAGE, 1, 10, 20, 10, AnimalType.BIRDS));
        addNewCage(new Cage(CageTypes.WATER_CAGE, 2, 10, 20, 10, AnimalType.INSECTS));

        TreeMap<Food, Integer> foodKgPerDay = new TreeMap<>();
        foodKgPerDay.put(FoodList.foodList.get("kasha1"), 10);
        foodKgPerDay.put(FoodList.foodList.get("kivi"), 2);

        addAnimal(new Animal(100, foodKgPerDay, 5, 10, AnimalType.MAMMALS, cages.get(0)));
        addAnimal(new Animal(101, foodKgPerDay, 7, 12, AnimalType.MAMMALS, cages.get(0)));
        addAnimal(new Animal(102, foodKgPerDay, 2, 45, AnimalType.INSECTS, cages.get(1)));
        addAnimal(new Animal(103, foodKgPerDay, 8, 12, AnimalType.BIRDS, cages.get(1)));
        addAnimal(new Animal(104, foodKgPerDay, 8, 12, AnimalType.INSECTS, cages.get(2)));
        addAnimal(new Animal(104, foodKgPerDay, 8, 12, AnimalType.INSECTS, cages.get(2)));
        //------------



        showInf();
        showPricePerDay();
    }

    public void addNewCage(Cage newCage){
        if(cagesNumbers.add(newCage.getCageNumber())) {
            cages.add(newCage);
        }else {
            System.err.println("Cage with id=" + newCage.getCageNumber() + " already exists in zoo!");
        }

    }

    public void showInf(){
        Map<CageTypes, Map<Integer, List<Animal>>> inf = cages.stream().collect(groupingBy(Cage::getType, Collectors.toMap(Cage::getCageNumber, Cage::getAnimals)));
        System.out.println("information about zoo:" + inf);
    }

    public void showPricePerDay(){
        Map<Animal, Map<Food, Integer>> food = animals.stream().collect(Collectors.toMap(animal -> animal, Animal::getFoodKgPerDay));
        //Map<Animal, Map<FoodEnum, Map<Food, Integer>>> food2 = animals.stream().collect(Collectors.toMap(animal -> animal, Collectors.toMap((Animal animal) -> animal.getFoodKgPerDay().keySet().stream().)));
        System.out.println("information about food:" +food);
        int priceFoodPerDay = animals.stream().mapToInt(Animal::priceFoodPerDay).sum();
        System.out.println("price food for all animals per day = " + priceFoodPerDay);
        int priceCleaningPerDay = animals.stream().mapToInt(Animal::getCleaningPricePerDay).sum();
        System.out.println("price cleaning all animals  per day = " + priceCleaningPerDay);
        int priceCagesPerDay = cages.stream().mapToInt(Cage::getPricePerDay).sum();
        System.out.println("price all cages  per day = " + priceCagesPerDay);
        System.out.println("Full price per day = " + (priceFoodPerDay + priceCagesPerDay + priceCleaningPerDay));
    }

    public void addAnimal(Animal newAnimal){
        if(animalNumbers.add(newAnimal.getAnimalNumber())) {
            if(newAnimal.intoCagesAnimalList())
                animals.add(newAnimal);
        }else {
            System.err.println("Animal number = " + newAnimal.getAnimalNumber() + " already exists in zoo!");
        }
    }



}
