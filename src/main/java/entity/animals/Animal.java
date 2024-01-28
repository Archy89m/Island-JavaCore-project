package entity.animals;

import entity.Entity;

abstract class Animal extends Entity {

    private int movementSpeed;
    private int amountFoodForSatiety;

    public abstract void eat(Object food);

    public abstract void move();

    public abstract void reproduce();

}
