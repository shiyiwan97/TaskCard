package com.shiyiwan.taskcard.persistent;

import com.shiyiwan.taskcard.GlobalMap;

import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Classname Persistor
 * @Description TODO
 * @Date 2024/9/26 14:36
 * @Created by shiyiwan
 */
public class PersistenceManager {

    public static void saveData() {

        int startTimerCount = GlobalMap.getStartTimerCount();
        if (startTimerCount != 0) {
            throw new RuntimeException("you can save only when all the timers are stop!");
        }
        try {
            Path jarPath = Paths.get(
                    PersistenceManager.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toAbsolutePath();
            File jarFile = jarPath.toFile();
            File parentDir = jarFile.getParentFile();
            File configFile = new File(parentDir, "taskCardData");

            Component[] components = GlobalMap.getTaskContainer().getComponents();
            if (components == null || components.length == 0) {
                return;
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        saveData();
    }

}
