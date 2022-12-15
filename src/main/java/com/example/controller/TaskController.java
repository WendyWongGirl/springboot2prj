package com.example.controller;

import com.example.task.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务测试类
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TaskController {

    private final ScheduleTask scheduleTask;

    @Value("${printTime.cron}")
    private String cron;

    @Autowired
    public TaskController(ScheduleTask scheduleTask) {
        this.scheduleTask = scheduleTask;
    }

    @GetMapping("/updateCron")
    public String updateCron() {
        log.info("new cron :{}", cron);
        scheduleTask.setCron(cron);
        return "ok";
    }
}
