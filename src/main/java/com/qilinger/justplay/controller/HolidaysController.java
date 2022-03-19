package com.qilinger.justplay.controller;

import com.qilinger.justplay.service.HolidaysService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolidaysController {

    @Autowired
    public HolidaysService holidaysService;

    @GetMapping("/ホリデー")
    @ApiOperation("给小蜜蜂的节假日接口")
    public String ホリデー(
            @ApiParam("类型：0为节假日列表，1为今天是否放假") @RequestParam(defaultValue = "0") String type
    ){
        return holidaysService.Holidays(type);
    }
}
