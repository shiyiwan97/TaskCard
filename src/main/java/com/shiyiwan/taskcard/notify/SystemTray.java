package com.shiyiwan.taskcard.notify;

import java.awt.*;

/**
 * @Classname SystemTray
 * @Description TODO
 * @Date 2024/9/29 17:37
 * @Created by shiyiwan
 */
public class SystemTray {

    public static void popMessage(String caption, String message, TrayIcon.MessageType messageType) {
        if (java.awt.SystemTray.isSupported()) {
            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("path/to/some/icon.png");
            TrayIcon trayIcon = new TrayIcon(image);
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Reminder");
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
            trayIcon.displayMessage(caption, message, messageType);

        } else {
            throw new RuntimeException("System tray not supported!");
        }
    }

    public static void popInfoMessage(String caption, String message) {
        popMessage(caption, message, TrayIcon.MessageType.INFO);
    }

    public static void popInfoMessage(String message) {
        popMessage("Time's up", message, TrayIcon.MessageType.INFO);
    }

    public static void main(String[] args) {
        SystemTray.popMessage("this is caption", "this is message", TrayIcon.MessageType.INFO);
    }

}
