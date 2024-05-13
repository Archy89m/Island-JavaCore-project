package entity;

import org.island.Island;
import org.island.Location;
import providers.SettingsProvider;

public abstract class Entity {

    private final double weight;
    private volatile boolean isAlive;
    private Location location;

    public Entity() {

        this.weight = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "weight").doubleValue();
        this.isAlive = true;
    }

    public double getWeight() {
        return weight;
    }

    public synchronized Location getLocation() {
        return location;
    }

    public synchronized void setLocation(Location location) {
        this.location = location;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
        Island.increaseNumberOfDeaths();
    }
}