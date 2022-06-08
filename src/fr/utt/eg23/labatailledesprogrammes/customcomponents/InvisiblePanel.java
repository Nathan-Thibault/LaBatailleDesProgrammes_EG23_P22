package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.card.CombatCard;
import fr.utt.eg23.labatailledesprogrammes.card.GameCard;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterType;
import fr.utt.eg23.labatailledesprogrammes.screens.BattleVisualisation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class InvisiblePanel extends JPanel implements MouseListener {

    private final String colorName;
    private final BattleVisualisation vis;

    public InvisiblePanel(String colorName, BattleVisualisation vis) {
        this.colorName = colorName;
        this.vis = vis;
        addMouseListener(this);
        update();
    }

    private void update() {
        JPanel playerTroopPanel = vis.getPlayerTroopPanel();
        playerTroopPanel.removeAll();
        for (GameCard gc : LaBatailleDesProgrammes.getInstance().getPlayerTroops().get(colorName)) {
            playerTroopPanel.add(new CombatCard(gc));
        }
        playerTroopPanel.revalidate();

        JPanel opponentTroopPanel = vis.getOpponentTroopPanel();
        opponentTroopPanel.removeAll();
        for (Map.Entry<FighterType, Integer> entry : LaBatailleDesProgrammes.getInstance().getOpponentTroops().get(colorName).entrySet()) {
            for (int i = 0; i < entry.getValue(); i++)
                opponentTroopPanel.add(new OpponentFighter(entry.getKey()));
        }
        opponentTroopPanel.revalidate();

        vis.setBackground(colorName + "_bg.png");
        vis.revalidate();
        vis.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //uncomment to show hitbox
//        g.setColor(Color.WHITE);
//        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        update();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
