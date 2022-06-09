package fr.utt.eg23.labatailledesprogrammes.fighter;

import java.awt.*;

/**
 * Represents different properties that can be modified on a fighter.
 */
public enum FighterProperty {
    STRENGTH("Force", 1, 2, 10, Color.RED),
    DEXTERITY("Dextérité", 1, 2, 10, Color.ORANGE),
    RESISTANCE("Résistance", 1, 2, 10, Color.BLUE),
    CONSTITUTION("Constitution", 5, 10, 30, Color.GREEN),
    INITIATIVE("Initiative", 1, 2, 10, Color.MAGENTA);

    private final String name;
    private final int minEliteSoldier;//default value of that property for the elite soldiers
    private final int minMasterOfWar;//default value of that property for the master of war
    private final int max;
    private final Color color;

    FighterProperty(String name, int minEliteSoldier, int minMasterOfWar, int max, Color color) {
        this.name = name;
        this.minEliteSoldier = minEliteSoldier;
        this.minMasterOfWar = minMasterOfWar;
        this.max = max;
        this.color = color;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getMin(FighterType fighterType) {
        return switch (fighterType) {
            case ELITE_SOLDIER -> minEliteSoldier;
            case MASTER_OF_WAR -> minMasterOfWar;
            default -> 0;
        };
    }

    public int getMax() {
        return max;
    }

    public Color getColor() {
        return color;
    }
}
