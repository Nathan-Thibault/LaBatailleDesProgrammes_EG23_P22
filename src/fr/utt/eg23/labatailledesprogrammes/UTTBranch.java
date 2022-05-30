package fr.utt.eg23.labatailledesprogrammes;

public enum UTTBranch {
    ISI("Informatique et Système d'Information", "isi"),
    RT("Réseaux et Télécomunications", "rt"),
    A2I("Automatique et Informatique Industrielle", "a2i"),
    GM("Génie Mécanique", "gm"),
    MTE("Matériaux : Technologie et Économie", "mte"),
    MM("Matériaux et Mécanique", "mm"),
    GI("Génie Industriel", "gi");

    private final String name;
    private final String fileNameAffix;

    UTTBranch(String name, String fileNameAffix) {
        this.name = name;
        this.fileNameAffix = fileNameAffix;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getFileNameAffix() {
        return fileNameAffix;
    }
}
