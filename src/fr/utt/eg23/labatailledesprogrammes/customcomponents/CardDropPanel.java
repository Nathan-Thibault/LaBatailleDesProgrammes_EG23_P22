package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.card.CardForm;
import fr.utt.eg23.labatailledesprogrammes.draganddrop.DropHandler;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.JPanel;

public class CardDropPanel extends JPanel {

    private DropTarget dropTarget;
    private DropHandler dropHandler;

    private final int maxNumberOfCards;
    private final CardForm cardForm;

    public CardDropPanel(int maxNumberOfCards, CardForm cardForm) {
        this.maxNumberOfCards = maxNumberOfCards;
        this.cardForm = cardForm;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        dropHandler = new DropHandler();
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        dropTarget.removeDropTargetListener(dropHandler);
    }

    public int getMaxNumberOfCards() {
        return maxNumberOfCards;
    }

    public CardForm getCardForm() {
        return cardForm;
    }
}
