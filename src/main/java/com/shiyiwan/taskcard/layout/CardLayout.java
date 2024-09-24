package com.shiyiwan.taskcard.layout;

import javax.swing.*;
import java.awt.*;

/**
 * @Classname CardLayout
 * @Description TODO
 * @Date 2024/9/20 16:18
 * @Created by admin
 */
public class CardLayout implements LayoutManager {

    private int vGap;

    public CardLayout(int vGap) {
        this.vGap = vGap;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            int width = 0;
            int height = vGap;

            for (int i = 0; i < parent.getComponentCount(); i++) {
                Component component = parent.getComponent(i);
                Dimension preferredSize = component.getPreferredSize();
                width = Math.max(width, preferredSize.width);
                height += preferredSize.height + vGap;
            }

            Insets insets = parent.getInsets();
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
            return new Dimension(width, height);
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int width = parent.getWidth() - insets.left - insets.right;
            int y = insets.top + vGap;

            for (int i = 0; i < parent.getComponentCount(); i++) {
                Component component = parent.getComponent(i);
                Dimension preferredSize = component.getPreferredSize();
                component.setBounds(insets.left, y, width, preferredSize.height);
                y += preferredSize.height + vGap;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setSize(400,400);
            frame.setUndecorated(true);  // 去掉窗口边框
            frame.setBackground(new Color(0, 0, 0, 0));  // 设置背景色为透明
            frame.getContentPane().setBackground(new Color(0, 0, 0, 0));  // 内容窗格背景色透明

            JPanel panel = new JPanel(new CardLayout(13)){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(new Color(255, 255, 255, 128));  // 半透明白色
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            panel.setOpaque(false);
            for (int i = 0; i < 5; i++) {
                JButton button = new JButton("Button " + i);
                button.setPreferredSize(new Dimension(500,30));
                panel.add(button);
            }

            frame.getContentPane().setBackground(new Color(0, 0, 0, 0));
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);

        });
    }
}
