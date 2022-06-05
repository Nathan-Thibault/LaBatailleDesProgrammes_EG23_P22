package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerDisplay extends JPanel {
    public TimerDisplay(int timeInSeconds, Runnable onEnd) {
        JLabel timeText = new JLabel("Temps restant: ");
        timeText.setForeground(Color.WHITE);
        timeText.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));

        AtomicInteger secondsLeft = new AtomicInteger(timeInSeconds);
        String str = String.format("%02d:%02d", (secondsLeft.get() % 3600) / 60, (secondsLeft.get() % 60));//mm:ss
        BlinkLabel timeLeft = new BlinkLabel(str, 150);
        timeLeft.setBlinking(false);
        timeLeft.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));
        timeLeft.setForeground(Color.WHITE);
        final Timer timer = new Timer(1000, e -> {
            secondsLeft.addAndGet(-1);
            String s = String.format("%02d:%02d", (secondsLeft.get() % 3600) / 60, (secondsLeft.get() % 60));//mm:ss
            if (secondsLeft.get() < 60 && !timeLeft.getForeground().equals(Color.RED)) {
                timeLeft.setForeground(Color.RED);
                timeLeft.setBlinking(true);
            }

            timeLeft.setText(s);
            timeLeft.revalidate();
            timeLeft.repaint();

            if (secondsLeft.get() <= 0) {
                ((Timer) e.getSource()).stop();
                onEnd.run();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();

        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        setBackground(null);
        add(timeText);
        add(timeLeft);
    }
}
