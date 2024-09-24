package com.shiyiwan.taskcard.component;

import com.shiyiwan.taskcard.GlobalComponentMap;
import com.shiyiwan.taskcard.config.Config;
import com.shiyiwan.taskcard.util.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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

    private String remainTimeDesc;

    private String text;

    private JLabel textLabel;

    private Timer timer;

    public TaskItem() {
        this(Config.DEFAULT_TASK_NAME, Config.DEFAULT_TASK_DESC, Config.DEFAULT_TASK_TIME);
    }

    //todo override constructor
    public TaskItem(String taskName, String taskDesc, Integer remainTime) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.remainTime = remainTime;
        this.remainTimeDesc = Utils.secondsToTime(remainTime);

        this.setLayout(new BorderLayout());

        textLabel = new JLabel(taskName + " | " + taskDesc + " | " + remainTimeDesc);
        JButton startBtn = new JButton("▶");
        startBtn.addActionListener(e -> startTimer());
        JButton endBtn = new JButton("⏸");
        endBtn.addActionListener(e -> pauseTimer());
        JButton editBtn = new JButton("✎");
        editBtn.addActionListener(e -> clickEdit());
        JPanel operatePanel = new JPanel();
        operatePanel.setLayout(new GridLayout());
        operatePanel.add(editBtn);
        operatePanel.add(startBtn);
        operatePanel.add(endBtn);
        this.add(textLabel, BorderLayout.CENTER);
        this.add(operatePanel, BorderLayout.EAST);
        textLabel.setVerticalTextPosition(JLabel.CENTER);

        Border lineBorder = new LineBorder(Color.gray, 1);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, "#1");
        this.setBorder(titledBorder);
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            remainTime -= 1;
            textLabel.setText(taskName + " | " + taskDesc + " | " + Utils.secondsToTime(remainTime));
        });
        timer.start();

    }

    private void pauseTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void addSubTask() {
        //todo
    }

    private void clickEdit() {
        EditDialog editDialog = new EditDialog();
        int result = JOptionPane.showConfirmDialog(GlobalComponentMap.getTaskContainer(), editDialog, "Edit",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION){
            String taskName = editDialog.taskName.getText();
            String taskDesc = editDialog.taskDesc.getText();
            int remainTime = Integer.parseInt(editDialog.remainTime.getText());
            this.taskName = taskName;
            this.taskDesc = taskDesc;
            this.remainTime = remainTime;
            textLabel.setText(taskName + " | " + taskDesc + " | " + Utils.secondsToTime(remainTime));
        }
    }

    class EditDialog extends JPanel {

        private JTextField taskName;
        private JTextField taskDesc;
        private JTextField remainTime;

        public EditDialog() {
            this.setLayout(new GridLayout(3, 2, 5, 5));
            JLabel label1 = new JLabel("taskName");
            taskName = new JTextField();
            JLabel label2 = new JLabel("taskDesc");
            taskDesc = new JTextField();
            JLabel label3 = new JLabel("remainTime");
            remainTime = new JTextField();

            this.add(label1);
            this.add(taskName);
            this.add(label2);
            this.add(taskDesc);
            this.add(label3);
            this.add(remainTime);
        }
    }

    public static void main(String[] args) {
        TaskItem taskItem = new TaskItem("tn", "td", 6000);
        JFrame frame = new JFrame();
        frame.add(taskItem);
        frame.setSize(300, 600);
        frame.setVisible(true);
    }

}
