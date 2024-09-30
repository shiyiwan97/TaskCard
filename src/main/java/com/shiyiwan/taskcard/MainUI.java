package com.shiyiwan.taskcard;

import com.formdev.flatlaf.FlatLightLaf;
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
        SwingUtilities.invokeLater(() -> {
            // fix white screen cause by Direct3D and DirectDraw
            // AMD cpu and integrated graphics may cause that
            System.setProperty("sun.java2d.d3d", "false");
            System.setProperty("sun.java2d.noddraw", "true");
            createUI();
        });
    }

    public static void createUI() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 600);
        JPanel taskContainer = new JPanel();
        GlobalMap.setTaskContainer(taskContainer);
        jFrame.setLayout(new BorderLayout());

        CardTitle cardTitle = new CardTitle();

        taskContainer.setLayout(new CardLayout(10));
        TaskItem taskItem = new TaskItem("tn", "td", 6000);
        TaskItem taskItem2 = new TaskItem("tn2", "td2", 4000);
        taskContainer.add(taskItem);
        taskContainer.add(taskItem2);

        jFrame.add(taskContainer, BorderLayout.CENTER);
        jFrame.add(cardTitle, BorderLayout.NORTH);
        jFrame.setVisible(true);
        System.out.println("start success");
    }

}
