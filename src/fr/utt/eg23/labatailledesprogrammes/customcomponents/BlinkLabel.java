package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import javax.swing.*;
import java.awt.*;

//label with timer and modified paint() method to make text blinking
public class BlinkLabel extends JLabel {

    private boolean on = true;
    private int tCount = 0;

    public BlinkLabel(String text, int timeInMillis) {
        super(text);

        Timer timer = new Timer(timeInMillis, ae -> {
            if (on && tCount == 0) {//make text appears only 2 timer loops out of 3
                tCount++;
            } else {
                tCount = 0;
                on = !on;
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        if (!on) {
            g2d.setComposite(AlphaComposite.SrcOver.derive(0f));
        } else {
            g2d.setComposite(AlphaComposite.SrcOver.derive(1f));
        }
        super.paint(g2d);
        g2d.dispose();
    }
}
