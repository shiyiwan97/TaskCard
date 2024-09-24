package com.shiyiwan.taskcard.component;

import com.shiyiwan.taskcard.GlobalComponentMap;

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
    private LocalDateTime now = LocalDateTime.now();;

    public CardTitle() {

        todayRemainBar = new JProgressBar();
        todayRemainBar.setMaximum(100);
        todayRemainBar.setMinimum(0);
        todayRemainBar.setStringPainted(true);
        this.setLayout(new BorderLayout());

        JButton addBtn = new JButton("+");
        addBtn.addActionListener(e -> {
            JPanel taskContainer = GlobalComponentMap.getTaskContainer();
            creatTask(taskContainer);
            taskContainer.revalidate();
            taskContainer.repaint();
        });
        this.add(addBtn,BorderLayout.EAST);

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout(FlowLayout.LEFT));
        timeLabel = new JLabel();
        center.add(todayRemainBar);
        center.add(timeLabel);
        this.add(center,BorderLayout.CENTER);
        update();
    }

    private void update(){
        new Timer(1000,e -> {
            now = LocalDateTime.now();
            String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
            timeLabel.setText(currentTime);
        }).start();
        setTodayRemain();
    }

    private void creatTask(JPanel container){
        TaskItem taskItem = new TaskItem("tn", "td", 6000);
        container.add(taskItem);
    }

    private void setTodayRemain(){
        LocalDateTime endOfDay = now.toLocalDate().atTime(LocalTime.MAX);
        long secondsRemaining = ChronoUnit.SECONDS.between(now, endOfDay);
        long totalSecondsInDay = ChronoUnit.SECONDS.between(LocalTime.MIN, LocalTime.MAX) + 1;
        double todayRemain = (double) (totalSecondsInDay - secondsRemaining) / totalSecondsInDay * 100;
        todayRemainBar.setValue((int)todayRemain);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(600,800);
        CardTitle cardTitle = new CardTitle();
        jFrame.add(cardTitle);
        jFrame.setVisible(true);
        jFrame.pack();
    }
}
