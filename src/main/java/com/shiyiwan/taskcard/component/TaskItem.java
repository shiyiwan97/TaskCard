package com.shiyiwan.taskcard.component;

import com.shiyiwan.taskcard.config.Config;

import javax.swing.*;
import java.awt.*;

/**
 * @Classname TaskItem
 * @Description TODO
 * @Date 9/22/24 12:43 PM
 * @Created by shiyiwan
 */
public class TaskItem extends JPanel {

    private String taskName;

    private JLabel taskNameLabel;

    private String taskDesc;

    private Integer remainTime;

    private String text;

    private JLabel textLabel;

    private Timer timer;

    public TaskItem(){
        this(Config.DEFAULT_TASK_NAME,Config.DEFAULT_TASK_DESC,Config.DEFAULT_TASK_TIME);
    }

    //todo override constructor
    public TaskItem(String taskName,String taskDesc,Integer remainTime){
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.remainTime = remainTime;

        this.setLayout(new BorderLayout());
        textLabel = new JLabel(taskName + " | " + taskDesc + " | " + remainTime);
        JButton startBtn = new JButton("s");
        startBtn.addActionListener(e -> startTimer());
        JButton endBtn = new JButton("e");
        endBtn.addActionListener(e -> pauseTimer());
        JPanel operatePanel = new JPanel();
        operatePanel.setLayout(new GridLayout());
        operatePanel.add(startBtn);
        operatePanel.add(endBtn);
        this.add(textLabel,BorderLayout.CENTER);
        this.add(operatePanel,BorderLayout.EAST);
    }



    private void startTimer(){
        timer = new Timer(1000, e -> {
            remainTime -= 1;
            textLabel.setText(taskName + " | " + taskDesc + " | " + remainTime);
        });
        timer.start();

    }
    
    private void pauseTimer(){
        if(timer != null && timer.isRunning()){
            timer.stop();
        }
    }

    public static void main(String[] args) {
        TaskItem taskItem = new TaskItem();
        taskItem.taskName = "tn";
        taskItem.taskDesc = "td";
        JFrame frame = new JFrame();
        frame.add(taskItem);
        frame.setSize(300,600);
        frame.setVisible(true);
    }

}
