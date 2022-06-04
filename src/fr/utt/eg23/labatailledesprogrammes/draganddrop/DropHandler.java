package fr.utt.eg23.labatailledesprogrammes.draganddrop;

import fr.utt.eg23.labatailledesprogrammes.card.DotCard;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.CardDropPanel;
import fr.utt.eg23.labatailledesprogrammes.card.GameCard;
import fr.utt.eg23.labatailledesprogrammes.card.MinimizedCard;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.Serializable;

public class DropHandler implements DropTargetListener, Serializable {

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {
            //make sure we respect the maxNumberOfChild in a DropPanel
            if (dtde.getDropTargetContext().getComponent() instanceof CardDropPanel panel) {
                if (panel.getComponentCount() < panel.getMaxNumberOfCards())
                    dtde.acceptDrag(DnDConstants.ACTION_MOVE);
                else
                    dtde.rejectDrag();
            } else
                dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        } else {
            dtde.rejectDrag();
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        boolean success;
        // Basically, we want to unwrap the present...
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {
            Transferable transferable = dtde.getTransferable();
            try {
                Object data = transferable.getTransferData(PanelDataFlavor.SHARED_INSTANCE);
                if (data instanceof JPanel panel) {
                    DropTargetContext dtc = dtde.getDropTargetContext();
                    if (dtc.getComponent() instanceof JComponent component) {
                        Container parent = panel.getParent();
                        if (parent != null) {
                            parent.remove(panel);
                            parent.revalidate();
                            parent.repaint();
                        }
                        if (panel instanceof GameCard gameCard) {
                            gameCard.setOtherForm(null);
                            if(component instanceof CardDropPanel cardDropPanel){
                                switch (cardDropPanel.getCardForm()){
                                    case FULL -> component.add(gameCard);
                                    case MINIMIZED -> component.add(new MinimizedCard(gameCard));
                                    case DOT -> component.add(new DotCard(gameCard));
                                }
                            }
                        }else component.add(panel);
                        success = true;
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                        component.invalidate();
                        component.repaint();
                    } else {
                        success = false;
                        dtde.rejectDrop();
                    }
                } else {
                    success = false;
                    dtde.rejectDrop();
                }
            } catch (Exception exp) {
                success = false;
                dtde.rejectDrop();
                exp.printStackTrace();
            }
        } else {
            success = false;
            dtde.rejectDrop();
        }
        dtde.dropComplete(success);
    }
}
