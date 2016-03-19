package com.ostremskiy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by DevAs on 12.03.2016.
 */
enum CageTypes{
    WATER_CAGE, LAND_CAGE
}
public class Cage {
    private CageTypes type;
    private int cageNumber;
    private int square;
    private int maxAnimalCount;
    private int pricePerDay;
    private AnimalType animalType;
    private List<Animal> animals;

    public Cage(CageTypes type, int cageNumber, int square, int maxAnimalCount, int pricePerDay, AnimalType animalType) {
        this.type = type;
        this.cageNumber = cageNumber;
        this.square = square;
        this.maxAnimalCount = maxAnimalCount;
        this.pricePerDay = pricePerDay;
        this.animalType = animalType;
        animals = new ArrayList<>();
    }

    public CageTypes getType() {
        return type;
    }

    public int getCageNumber() {
        return cageNumber;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public boolean addAnimal(Animal newAnimal){
        if(canBeAdded(newAnimal))
            animals.add(newAnimal);
        else
            return false;
        return true;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    private boolean canBeAdded(Animal newAnimal){
        boolean isFree = maxAnimalCount-animals.size()>0;
        boolean enoughPlace = square - animals.stream().mapToInt(Animal::getNeededSquare).sum() - newAnimal.getNeededSquare() >= 0;
        boolean appropriateType = newAnimal.getType() == animalType;
        if(!isFree)
            System.err.println("Cage number = " + cageNumber + " is not free");
        if(!enoughPlace)
            System.err.println("Cage number = " + cageNumber + " has not enough place for animal number = " + newAnimal.getAnimalNumber());
        if(!appropriateType)
            System.err.println("Animal number = " + newAnimal.getAnimalNumber() + " has no appropriate type for cage number = " + cageNumber);
        return isFree && enoughPlace && appropriateType;
    }

    @Override
    public String toString() {
        return "Cage  " + cageNumber;
    }

}
