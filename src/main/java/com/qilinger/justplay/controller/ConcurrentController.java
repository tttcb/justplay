package com.qilinger.justplay.controller;

import com.qilinger.justplay.service.ConcurrentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("concurrent")
public class ConcurrentController {

    @Autowired
    public ConcurrentService concurrentService;

    @GetMapping("/test")
    @ApiOperation("多并发测试")
    public String concurrentTest1(){
        return concurrentService.concurrentTest1();
    }


}
