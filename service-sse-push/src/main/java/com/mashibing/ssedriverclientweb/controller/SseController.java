package com.mashibing.ssedriverclientweb.controller;

import com.mashibing.common.request.PushRequest;
import com.mashibing.common.util.SsePrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: SseController
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/17
 **/
@RestController
@Slf4j
public class SseController {

    public static Map<String, SseEmitter> sseEmitterMap = new HashMap<>();

    /**
     * 建立连接
     * @param userId 用户id
     * @param identity 身份类型
     * @return
     */
    @GetMapping("/connect")
    public SseEmitter connect(@RequestParam Long userId, @RequestParam String identity){
        log.info("用户ID："+userId+",身份类型："+identity);
        SseEmitter sseEmitter = new SseEmitter(0L);

        String sseMapKey = SsePrefixUtils.generatorSseKey(userId,identity);

        sseEmitterMap.put(sseMapKey, sseEmitter);
        System.out.println("sseEmitterMap里是" + sseEmitterMap);

        return sseEmitter;
    }

    /**
     * 发送消息
     * @param pushRequest
     * @return
     */
    @PostMapping("/push")
    public String push(@RequestBody PushRequest pushRequest){

        Long userId = pushRequest.getUserId();
        String identity = pushRequest.getIdentity();
        String content = pushRequest.getContent();
        log.info("用户ID："+userId+",身份："+identity);
        String sseMapKey = SsePrefixUtils.generatorSseKey(userId,identity);
        try {
            if (sseEmitterMap.containsKey(sseMapKey)){
                sseEmitterMap.get(sseMapKey).send(content);
            }else {
                return "推送失败";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "给用户："+sseMapKey+",发送了消息："+content;
    }

    /**
     * 关闭连接
     * @param userId 用户ID
     * @param identity 身份类型
     * @return
     */
    @GetMapping("/close")
    public String close(@RequestParam Long userId, @RequestParam String identity){
        String sseMapKey = SsePrefixUtils.generatorSseKey(userId,identity);
        System.out.println("关闭连接："+sseMapKey);
        System.out.println("sseEmitterMap里是" + sseEmitterMap);
        if (sseEmitterMap.containsKey(sseMapKey)){
            sseEmitterMap.remove(sseMapKey);
        }
        return "close 成功";

    }
}