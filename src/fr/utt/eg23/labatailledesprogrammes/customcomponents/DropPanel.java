package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.draganddrop.DropHandler;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.JPanel;

public class DropPanel extends JPanel {

    DropTarget dropTarget;
    DropHandler dropHandler;

    private final int maxNumberOfChild;

    public DropPanel(int maxNumberOfChild) {
        this.maxNumberOfChild = maxNumberOfChild;
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

    public int getMaxNumberOfChild() {
        return maxNumberOfChild;
    }
}
