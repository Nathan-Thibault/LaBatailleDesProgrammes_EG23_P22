package fr.utt.eg23.labatailledesprogrammes;

/**
 * Represents a playable branch (sort of "faction", "team" or "side").
 */
public enum UTTBranch {
    ISI("Informatique et Système d'Information", "isi", new String[]{"smartphone.png", "tablette.png"}),
    RT("Réseaux et Télécomunications", "rt", new String[]{"cloud.png", "server.png"}),
    A2I("Automatique et Informatique Industrielle", "a2i", new String[]{"processeur.png", "robot.png"}),
    GM("Génie Mécanique", "gm", new String[]{"presse.png", "engrenage.png"}),
    MTE("Matériaux : Technologie et Économie", "mte", new String[]{"bitcoin.png", "materiaux.png"}),
    MM("Matériaux et Mécanique", "mm", new String[]{}),
    GI("Génie Industriel", "gi", new String[]{"calendrier.png", "optimisation.png"});

    private final String name;
    private final String fileNameAffix;
    private final String[] skinsFileNames;//list of skins

    UTTBranch(String name, String fileNameAffix, String[] skinsFileNames) {
        this.name = name;
        this.fileNameAffix = fileNameAffix;
        this.skinsFileNames = skinsFileNames;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getFileNameAffix() {
        return fileNameAffix;
    }

    public String[] getSkinsFileNames() {
        return skinsFileNames;
    }
}
