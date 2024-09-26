package com.shiyiwan.taskcard.component;

import com.shiyiwan.taskcard.GlobalMap;
import com.shiyiwan.taskcard.config.Config;
import com.shiyiwan.taskcard.util.Utils;
import lombok.Getter;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname TaskItem
 * @Description TODO
 * @Date 9/22/24 12:43 PM
 * @Created by shiyiwan
 */
public class TaskItem extends JPanel {

    @Getter
    private String taskName;

    private JLabel taskNameLabel;

    @Getter
    private String taskDesc;

    @Getter
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

    public TaskItem clickAdd() {
        return clickAddOrEdit(true);
    }

    private TaskItem clickEdit() {
        return clickAddOrEdit(false);
    }


    private TaskItem clickAddOrEdit(boolean isAdd) {
        EditDialog editDialog = new EditDialog(this);
        String dialogTitle;
        if (isAdd) {
            dialogTitle = "Add";
        } else {
            dialogTitle = "Edit";
        }
        int result = JOptionPane.showConfirmDialog(GlobalMap.getTaskContainer(), editDialog, dialogTitle,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String taskName = editDialog.taskName.getText();
            String taskDesc = editDialog.taskDesc.getText();
            int remainTime = Utils.parserRemainTime(editDialog.remainTime.getText());
            this.taskName = taskName;
            this.taskDesc = taskDesc;
            this.remainTime = remainTime;
            textLabel.setText(taskName + " | " + taskDesc + " | " + Utils.secondsToTime(remainTime));
            return this;
        }
        return null;
    }

    class EditDialog extends JPanel {

        private JTextField taskName;
        private JTextField taskDesc;
        private JTextField remainTime;

        public EditDialog(TaskItem taskItem) {
            this.setLayout(new GridLayout(3, 2, 5, 5));
            JLabel label1 = new JLabel("taskName");
            taskName = new JTextField();
            JLabel label2 = new JLabel("taskDesc");
            taskDesc = new JTextField();

            JPanel remainTimePanel = new JPanel();
            remainTimePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
            JLabel label3 = new JLabel("remainTime");
            JButton questionBtn = new JButton("?");
            questionBtn.putClientProperty("JButton.buttonType", "roundRect");
            questionBtn.setPreferredSize(new Dimension(15, 15));
            questionBtn.setEnabled(false);
            questionBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    questionBtn.setToolTipText("you can type like 1h23m45s");
                }
            });
            remainTimePanel.add(label3);
            remainTimePanel.add(Box.createRigidArea(new Dimension(5, 0)));
            remainTimePanel.add(questionBtn);

            remainTime = new JTextField();
            remainTime.setInputVerifier(new InputVerifier() {
                @Override
                public boolean verify(JComponent JComponent) {
                    String input = ((JTextField) JComponent).getText().toLowerCase();
                    String regex = "(\\d+h)?(\\d+m)?(\\d+s)?";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(input);
                    boolean matches = matcher.matches();
                    System.out.println("matches = " + matches);
                    if (!matches) {
                        JOptionPane.showMessageDialog(GlobalMap.getTaskContainer(),
                                "<html>input need matches regexp:<br>(\\d+h)?(\\d+m)?(\\d+s)?<br>example:<br>1h23m45s",
                                "remainTime format wrong",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    return matches;
                }
            });

            if (taskItem != null) {
                taskName.setText(taskItem.getTaskName());
                taskDesc.setText(taskItem.getTaskDesc());
                remainTime.setText(Utils.secondsToTime2(taskItem.getRemainTime()));
            }

            this.add(label1);
            this.add(taskName);
            this.add(label2);
            this.add(taskDesc);
            this.add(remainTimePanel);
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
