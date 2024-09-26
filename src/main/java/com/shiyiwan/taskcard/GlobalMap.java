package com.shiyiwan.taskcard;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * @Classname GlobalComponentMap
 * @Description TODO
 * @Date 2024/9/24 22:51
 * @Created by shiyiwan
 */
public class GlobalMap {

    private GlobalMap() {
    }

    @Getter
    @Setter
    private static JPanel taskContainer;

    @Getter
    @Setter
    private static Integer startTimerCount = 0;
}
