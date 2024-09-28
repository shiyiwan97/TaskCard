package com.shiyiwan.taskcard.config;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Date;

/**
 * @Classname Config
 * @Description TODO
 * @Date 9/22/24 12:55 PM
 * @Created by shiyiwan
 */
public class Config {

    // UI
    public static final Boolean Opaque = false;
    public static final Boolean StringPainted = true;

    // Task
    public static final Integer DEFAULT_TASK_TIME = 600;
    public static final String DEFAULT_TASK_NAME = "taskName";
    public static final String DEFAULT_TASK_DESC = "taskDesc";
    public static final Boolean ALLOW_MULTI_TASK_START = true;

    //State Border Color
    public static final Color CREATE_TASK_COLOR = Color.GRAY;
    public static final Color START_TASK_COLOR = Color.GREEN;
    public static final Color PAUSE_TASK_COLOR = Color.ORANGE;
    public static final Color FINISH_TASK_COLOR = Color.BLUE;

    // Overtime Strategy
    public static final int OVERTIME_STRATEGY_CONTINUE = 0;
    public static final int OVERTIME_STRATEGY_STOP = 1;

    @Getter
    @Setter
    // Save And Sync
    public static Date lastModifiedDate;
    public static Boolean saveBeforeStopTimer = false;
    public static boolean saveBeforeProcessKilled = false;


}
