package fr.utt.eg23.labattailledesprogrammes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serial;
import javax.swing.JProgressBar;

/**
 * Modified code found here: https://stackoverflow.com/a/14036372
 */
public class CustomProgressBar extends JProgressBar {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DISABLED_STRING = " -- ";

    private static final Color gradientEndingColor = new Color(0xc0c0c0);
    private static final Color borderColor = new Color(0x736a60);
    private static final Color disabledBorderColor = new Color(0xbebebe);

    private static final Composite transparent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.45f);
    private static final Composite veryTransparent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f);

    private static GradientPaint gradient;

    private int oldWidth;
    private int oldHeight;

    private int displayWidth;
    private int displayHeight;

    private final int[] insets = new int[4];
    private static final int TOP_INSET = 0;
    private static final int LEFT_INSET = 1;
    private static final int BOTTOM_INSET = 2;
    private static final int RIGHT_INSET = 3;

    private static final int PREFERRED_STRING_MARGIN_WIDTH = 3;

    public static final Color PREFERRED_PROGRESS_COLOR = new Color(0x1869A6);

    private boolean stringVisible = true;

    private Color progressColor;

    private String maxString;

    public CustomProgressBar() {
        progressColor = PREFERRED_PROGRESS_COLOR;
    }

    public void updateGraphics() {
        update(getGraphics());
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w = displayWidth != 0 ? displayWidth - 1 : getWidth() - 1;
        int h = displayHeight != 0 ? displayHeight - 1 : getHeight() - 1;

        int x = insets[LEFT_INSET];
        int y = insets[TOP_INSET];
        w -= (insets[RIGHT_INSET] << 1);
        h -= (insets[BOTTOM_INSET] << 1);

        if (gradient == null) {
            gradient = new GradientPaint(0.0f, 0.0f, Color.WHITE, 0.0f, h, gradientEndingColor);
        }
        Graphics2D g2d = (Graphics2D) g;
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

            if (isEnabled()) {
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
            } else {
                stringW = fm.stringWidth(DISABLED_STRING);
                stringH = ((h - fm.getHeight()) / 2) + fm.getAscent();

                g2d.drawString(DISABLED_STRING, w - stringW, stringH);
            }
            w -= (stringW + PREFERRED_STRING_MARGIN_WIDTH);
        }

        // Control Border
        g2d.setColor(isEnabled() ? borderColor : disabledBorderColor);
        g2d.drawLine(1, 0, w - 1, 0);
        g2d.drawLine(1, h, w - 1, h);
        g2d.drawLine(0, 1, 0, h - 1);
        g2d.drawLine(w, 1, w, h - 1);

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

        // A gradient over the progress fill
        g2d.setPaint(gradient);
        g2d.setComposite(transparent);
        g2d.fillRect(1, 1, w - 1, (h >> 1));
        final float FACTOR = 0.20f;
        g2d.fillRect(1, h - (int) (h * FACTOR), w - 1, (int) (h * FACTOR));

        // dividing bars
        if (isEnabled()) {
            int p = getMaximum();
            for (int i = w / p; i < w; i += w / p) {
                g2d.setComposite(veryTransparent);
                g2d.setColor(Color.GRAY);
                g2d.drawLine(i, 1, i, h - 1);
                g2d.setColor(Color.WHITE);
                g2d.drawLine(i + 1, 1, i + 1, h - 1);
            }
        } else {
            for (int i = 0; i < w; i += h) {
                g2d.setComposite(veryTransparent);
                g2d.setColor(Color.RED);
                g2d.drawLine(i, h - 1, i + h, 1);
                g2d.setColor(Color.WHITE);
                g2d.drawLine(i + 1, h - 1, i + 1 + h, 1);
            }
        }
    }

    public void setInsets(int top, int left, int bottom, int right) {
        insets[TOP_INSET] = top;
        insets[LEFT_INSET] = left;
        insets[BOTTOM_INSET] = bottom;
        insets[RIGHT_INSET] = right;
    }

    public void setStringVisible(boolean stringVisible) {
        this.stringVisible = stringVisible;
    }

    @Override
    protected void paintBorder(Graphics g) {
    }

    @Override
    public void validate() {
        int w = getWidth();
        int h = getHeight();

        super.validate();
        if (oldWidth != w || oldHeight != h) {
            oldWidth = w;
            oldHeight = h;
            gradient = null;
        }
    }

    @Override
    public void setMaximum(int n) {
        super.setMaximum(n);
        maxString = Integer.toString(n, 10);
    }

    public void setDisplaySize(int width, int height) {
        displayWidth = width;
        displayHeight = height;
    }

    public Color getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(Color progressColor) {
        this.progressColor = progressColor;
    }
}

