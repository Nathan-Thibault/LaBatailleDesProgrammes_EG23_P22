package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.card.CardForm;
import fr.utt.eg23.labatailledesprogrammes.card.DotCard;
import fr.utt.eg23.labatailledesprogrammes.draganddrop.DropHandler;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.JPanel;

public class CardDropPanel extends JPanel {

    private DropTarget dropTarget;
    private DropHandler dropHandler;

    private final int maxNumberOfCards;
    private final CardForm cardForm;

    public CardDropPanel(int maxNumberOfCards, CardForm cardForm) {
        this.maxNumberOfCards = maxNumberOfCards;
        this.cardForm = cardForm;

        if (cardForm == CardForm.DOT)
            addContainerListener(new ContainerListener() {
                @Override
                public void componentAdded(ContainerEvent e) {
                    updateComponentsSize(e);

                }

                @Override
                public void componentRemoved(ContainerEvent e) {
                    updateComponentsSize(e);
                }

                private void updateComponentsSize(ContainerEvent e) {
                    Container container = e.getContainer();
                    int count = container.getComponentCount();
                    if (count > 0) {
                        double size = DotCard.DEFAULT_SIZE.getWidth();
                        int newSize = (int) (size * 1 / Math.pow(count, 1f / 3f));
                        Dimension newDimension = new Dimension(newSize, newSize);
                        for (Component c : container.getComponents()) {
                            c.setPreferredSize(newDimension);
                        }
                    }
                }
            });
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
