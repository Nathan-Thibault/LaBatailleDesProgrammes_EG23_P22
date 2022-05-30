package fr.utt.eg23.labattailledesprogrammes;

public enum FighterProperty {
    STRENGHT("Force", 1, 2, 10),
    DEXTERITY("Dextérité", 1, 2, 10),
    RESISTANCE("Résistance", 1, 2, 10),
    CONSTITUTION("Constitution", 5, 10, 30),
    INITIATIVE("Initiative", 1, 2, 10);

    private final String name;
    private final int minEliteSoldier;
    private final int minMasterOfWar;
    private final int max;

    FighterProperty(String name, int minEliteSoldier, int minMasterOfWar, int max) {
        this.name = name;
        this.minEliteSoldier = minEliteSoldier;
        this.minMasterOfWar = minMasterOfWar;
        this.max = max;
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
}
