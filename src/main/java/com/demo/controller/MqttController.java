package com.demo.controller;

import com.demo.dto.UserDto;
import com.demo.mqtt.config.MqttGateway;
import com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: LiuYong
 * @Date:2020/04/26 13:22
 * @Description: TODO 描述
 */
@Controller
@RequestMapping
public class MqttController {
    @Autowired
    private IUserService userService;

    @Autowired
    private MqttGateway mqttGateway;

    /**
     * 主题人数统计
     */
    private static Map<String, Long> map = new ConcurrentHashMap();
    /**
     * 房间人数统计
     */
    private static Map<String, Long> room = new ConcurrentHashMap();

    /**
     * 房间入口
     *
     * @param modelMap
     * @param userId   用户id
     * @param roomName 房间标识
     * @return
     */
    @GetMapping("mqtt/{roomName}/{userId}")
    public String mqtt(ModelMap modelMap, @PathVariable("userId") String userId, @PathVariable("roomName") String roomName) {
        UserDto user = userService.getUser(userId);
        modelMap.put("user", user);
        modelMap.put("roomName", roomName);
        return "mqtt";
    }

    /**
     * 房间人数统计请求页
     *
     * @param modelMap
     * @param userId
     * @param roomName
     * @return
     */
    @GetMapping("count/{roomName}/{userId}")
    public String count(ModelMap modelMap, @PathVariable("userId") String userId, @PathVariable("roomName") String roomName) {
        modelMap.put("roomName", roomName);
        modelMap.put("userId", userId);

        return "count";
    }

    /**
     * 增加房间人数
     *
     * @param topic
     */
    @ResponseBody
    @PostMapping("add-count")
    public void addCount(String topic, String roomName) {
        Long aLong = 0L;
        if (!map.containsKey(topic)) {
            aLong = 1L;
            map.put(topic, aLong);
            room.put(roomName, aLong);
        } else {
            aLong = map.get(topic);
            aLong = aLong + 1;
            map.put(topic, aLong);
            room.put(roomName, aLong);
        }
        mqttGateway.sendToMqtt("房间人数：" + aLong, topic);
    }

    /**
     * 减少房间人数
     *
     * @param topic
     */
    @ResponseBody
    @PostMapping("subtract-count")
    public void subtractCount(String topic, String roomName) {
        Long aLong = map.get(topic);
        aLong = aLong - 1;
        aLong = aLong >= 0 ? aLong : 0;
        map.put(topic, aLong);
        room.put(roomName, aLong);
        mqttGateway.sendToMqtt("房间人数：" + aLong, topic);
    }



    @GetMapping("room")
    public String room(ModelMap map) {
        map.put("room",room);
        return "room";
    }


}
