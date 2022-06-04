package fr.utt.eg23.labatailledesprogrammes.card;

import javax.swing.*;

public class DotCard extends OtherCardForm {
    public DotCard(GameCard original) {
        super(original);
        add(new JLabel("test"));
        //TODO paint circle corresponding to soldier type
    }
}
