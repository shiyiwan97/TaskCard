package com.shiyiwan.taskcard;

import javax.swing.*;

/**
 * @Classname GlobalComponentMap
 * @Description TODO
 * @Date 2024/9/24 22:51
 * @Created by shiyiwan
 */
public class GlobalComponentMap {

    private GlobalComponentMap() {
    }

    private static JPanel taskContainer;

    public static void setTaskContainer(JPanel taskItemContainer) {
        GlobalComponentMap.taskContainer = taskItemContainer;
    }

    public static JPanel getTaskContainer() {
        return taskContainer;
    }
}
