package com.shiyiwan;

import com.formdev.flatlaf.FlatLightLaf;
import com.shiyiwan.taskcard.GlobalComponentMap;
import com.shiyiwan.taskcard.component.CardTitle;
import com.shiyiwan.taskcard.component.TaskItem;
import com.shiyiwan.taskcard.layout.CardLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2024/9/24 20:38
 * @Created by shiyiwan
 */
public class MainUI {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 600);
        JPanel taskContainer = new JPanel();
        GlobalComponentMap.setTaskContainer(taskContainer);
        jFrame.setLayout(new BorderLayout());

        CardTitle cardTitle = new CardTitle();

        taskContainer.setLayout(new CardLayout(10));
        TaskItem taskItem = new TaskItem("tn", "td", 6000);
        TaskItem taskItem2 = new TaskItem("tn2", "td2", 4000);
        taskContainer.add(taskItem);
        taskContainer.add(taskItem2);

        jFrame.add(taskContainer, BorderLayout.CENTER);
        jFrame.add(cardTitle,BorderLayout.NORTH);
        jFrame.setVisible(true);
    }

}
