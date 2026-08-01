package org.mdgmi.enginev1.engine.player.progress;

public class SkillProgress {

    private int level = 1;

    private int mastery = 0;

    private int experience = 0;

    public int getLevel() {
        return level;
    }

    public int getMastery() {
        return mastery;
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int amount) {

        experience += amount;

        while (experience >= getRequiredExperience()) {

            experience -= getRequiredExperience();

            level++;
        }
    }

    public void addMastery(int amount) {

        mastery += amount;
    }

    public int getRequiredExperience() {

        return level * 100;

    }

}