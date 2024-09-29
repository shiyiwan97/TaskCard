package com.shiyiwan.taskcard.component;

import com.shiyiwan.taskcard.GlobalMap;
import com.shiyiwan.taskcard.config.Config;
import com.shiyiwan.taskcard.persistent.PersistenceManager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Classname CardTitle
 * @Description TODO
 * @Date 2024/9/24 21:00
 * @Created by shiyiwan
 */
public class CardTitle extends JPanel {

    private JProgressBar todayRemainBar;
    JLabel timeLabel;
    private LocalDateTime now = LocalDateTime.now();

    public CardTitle() {

        todayRemainBar = new JProgressBar();
        todayRemainBar.setMaximum(100);
        todayRemainBar.setMinimum(0);
        todayRemainBar.setStringPainted(true);
        this.setLayout(new BorderLayout());

        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("+");
        addBtn.addActionListener(e -> {
            JPanel taskContainer = GlobalMap.getTaskContainer();
            creatTask(taskContainer);
            taskContainer.revalidate();
            taskContainer.repaint();
        });

        ImageIcon originalIcon = new ImageIcon("src/main/resources/icon/download16.png");
        JButton saveBtn = new JButton(originalIcon);
        saveBtn.addActionListener(e -> onClickSave());

        operationPanel.add(addBtn);
        operationPanel.add(saveBtn);

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout(FlowLayout.LEFT));
        timeLabel = new JLabel();
        center.add(todayRemainBar);
        center.add(timeLabel);
        this.add(center, BorderLayout.CENTER);
        this.add(operationPanel, BorderLayout.EAST);
        update();
    }

    private void update() {
        new Timer(1000, e -> {
            now = LocalDateTime.now();
            String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
            timeLabel.setText(currentTime);
            setTodayRemain();
        }).start();
    }

    private void creatTask(JPanel container) {
        TaskItem taskItem = new TaskItem(Config.DEFAULT_TASK_NAME, Config.DEFAULT_TASK_DESC, Config.DEFAULT_TASK_TIME);
        TaskItem addItem = taskItem.clickAdd();
        if (addItem != null) {
            container.add(addItem);
        }
    }

    private void setTodayRemain() {
        LocalDateTime endOfDay = now.toLocalDate().atTime(LocalTime.MAX);
        long secondsRemaining = ChronoUnit.SECONDS.between(now, endOfDay);
        long totalSecondsInDay = ChronoUnit.SECONDS.between(LocalTime.MIN, LocalTime.MAX) + 1;
        double todayRemain = (double) (totalSecondsInDay - secondsRemaining) / totalSecondsInDay * 100;
        int value = todayRemainBar.getValue();
        if (value != (int) todayRemain) {
            todayRemainBar.setValue((int) todayRemain);
        }
    }

    private void onClickSave() {
        JDialog jDialog;
        String dialogTitle;
        int result = JOptionPane.showConfirmDialog(
                GlobalMap.getTaskContainer(),
                "Are you sure you want to save data?",
                "Save",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.OK_OPTION) {
            PersistenceManager.saveData();
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(600, 800);
        CardTitle cardTitle = new CardTitle();
        jFrame.add(cardTitle);
        jFrame.setVisible(true);
        jFrame.pack();
    }
}
