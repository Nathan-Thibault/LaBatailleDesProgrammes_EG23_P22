package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import java.awt.*;
import java.io.Serial;
import javax.swing.JProgressBar;

/**
 * Modified code found here: https://stackoverflow.com/a/14036372
 */
public class CustomProgressBar extends JProgressBar {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Composite veryTransparent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f);

    private static final int PREFERRED_STRING_MARGIN_WIDTH = 3;

    public static final Color PREFERRED_PROGRESS_COLOR = new Color(0x1869A6);

    private boolean stringVisible = true;
    private boolean barsVisible = true;

    private Color progressColor;

    private String maxString;

    public CustomProgressBar() {
        progressColor = PREFERRED_PROGRESS_COLOR;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        int x = 0, y = 0;
        w -= 1;
        h -= 1;

        Graphics2D g2d = (Graphics2D) g.create();
        // Clean background
        if (isOpaque()) {
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        g2d.translate(x, y);

        if (stringVisible) {
            FontMetrics fm = g.getFontMetrics();
            int stringW;
            int stringH;

            g2d.setColor(getForeground());

            int p = getValue();
            String stringValue = Integer.toString(p, 10);
            if (p < 10) {
                stringValue = "0" + stringValue;
            }

            if (maxString == null) {
                maxString = Integer.toString(getMaximum(), 10);
            }
            stringW = fm.stringWidth(maxString);
            stringH = ((h - fm.getHeight()) / 2) + fm.getAscent();

            g2d.drawString(stringValue, w - stringW, stringH);

            w -= (stringW + PREFERRED_STRING_MARGIN_WIDTH);
        }

        // Fill in the progress
        int min = getMinimum();
        int max = getMaximum();
        int total = max - min;
        float dx = (float) (w - 2) / (float) total;
        int value = getValue();
        int progress;
        if (value == max) {
            progress = w - 1;
        } else {
            progress = (int) (dx * getValue());
        }

        g2d.setColor(progressColor);
        g2d.fillRect(1, 1, progress, h - 1);

        // dividing bars
        if (barsVisible) {
            float p = (float) (w - 1) / (float) getMaximum();

            for (float i = p; i < w; i += p) {
                int e = Math.round(i);
                g2d.setComposite(veryTransparent);
                g2d.setColor(Color.GRAY);
                g2d.drawLine(e, 1, e, h - 1);
                g2d.setColor(Color.WHITE);
                g2d.drawLine(e + 1, 1, e + 1, h - 1);
            }
        }

        g2d.dispose();
    }

    public void setStringVisible(boolean stringVisible) {
        this.stringVisible = stringVisible;
    }

    public void setBarsVisible(boolean barsVisible) {
        this.barsVisible = barsVisible;
    }

    @Override
    public void setMaximum(int n) {
        super.setMaximum(n);
        maxString = Integer.toString(n, 10);
    }

    public void setProgressColor(Color progressColor) {
        this.progressColor = progressColor;
    }
}

