package fr.utt.eg23.labatailledesprogrammes.fighter;

import java.awt.Color;

/**
 * Represents the types of fighter existing in the game.
 */
public enum FighterType {
    SOLDIER("Soldat", "so", new Color(9, 9, 10)),
    ELITE_SOLDIER("Soldat d'élite", "se", new Color(100, 100, 101)),
    MASTER_OF_WAR("Maître de guerre", "mg", new Color(237, 231, 25));

    private final String name;
    private final String fileNameAffix;
    private final Color color;

    FighterType(String name, String fileNameAffix, Color color) {
        this.name = name;
        this.fileNameAffix = fileNameAffix;
        this.color = color;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getFileNameAffix() {
        return fileNameAffix;
    }

    public Color getColor() {
        return color;
    }
}
