package com.qilinger.justplay.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.qilinger.justplay.enem.Holiday;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class HolidaysService {
    public LocalDateTime datetime = LocalDateTime.now();

    public String Holidays(String type){
        String getToday = "1";
        String getHolidays="0";
        if(getToday.equals(type)){
            return getToday();
        }
        if(getHolidays.equals(type))
        {
            return getHolidays();
        }
        return "false";
    }

    private String getToday() {
        JSONObject result = new JSONObject();
        try {
            result.put("code", "200");
            result.put("msg", "success");
            result.put("newslist", Todayinfo());
        } catch (JSONException e) {
            result.put("code", "500");
            result.put("msg", "maybe error: " + e.getMessage());
        }

        return result.toString();
    }

    private JSONObject Todayinfo() {
        JSONObject jsonObject = new JSONObject();
        String daycode ="0";
        jsonObject.put("date",datetime.toLocalDate());
        jsonObject.put("cnweekday",datetime.getDayOfWeek());
        if(datetime.getDayOfWeek()== DayOfWeek.SATURDAY || datetime.getDayOfWeek()== DayOfWeek.SUNDAY)
        {
            daycode = "2";
        }
        Holiday[] days = Holiday.values();
        for (Holiday d :days) {
            if(datetime.getMonth().getValue()==d.GetMonth() && datetime.getDayOfMonth()==d.GetDay())
            {
                daycode = "1";
            }
        }
        jsonObject.put("daycode",daycode);
        return jsonObject;
    }


    public String getHolidays() {
        JSONObject result = new JSONObject();
        try {
            result.put("code", "200");
            result.put("msg", "success");
            result.put("newslist", holidays());
        } catch (JSONException | UnexpectedException e) {
            result.put("code", "500");
            result.put("msg", "maybe error: " + e.getMessage());
        }

        return result.toString();
    }

    private JSONArray holidays() throws JSONException, UnexpectedException {
        JSONObject holiday = new JSONObject();
        JSONArray holidays = new JSONArray();
        Holiday[] days = Holiday.values();
        for (Holiday day : days) {
            try {
                holiday = getholiday(day);
                holidays.add(holiday);
            } catch (JSONException | UnexpectedException e) {
                if(e instanceof UnexpectedException) {
                    throw new UnexpectedException(e.getMessage());
                }
                throw new JSONException("密麻麻石蜡,为什么节日日期出错了捏");
            }
        }
        return holidays;
    }

    private JSONObject getholiday(Holiday day) throws UnexpectedException {
        JSONObject jsonObject;
        if (day.GetIfSpecial()) {
            jsonObject = SpecialDate(day);
        } else {
            if (day.GetIfNongLi()) {
                jsonObject = calNongLiDate(day);
            } else {
                jsonObject = calYangLiDATE(day);
            }
        }
        return jsonObject;
    }

    private JSONObject calYangLiDATE(Holiday day) {
        JSONObject jsonObject = new JSONObject();
        LocalDate target = LocalDate.of(datetime.getYear(),day.GetMonth(),day.GetDay());
        jsonObject.put("date", target);
        jsonObject.put("holiday", day.GetMonth()+"月" + day.GetDay() + "号");
        jsonObject.put("name", day.name());
        jsonObject.put("tip", day.name()+"放假一共"+day.GetHowlong()+"天");
        return jsonObject;
    }

    private JSONObject calNongLiDate(Holiday day)  {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","密麻麻石蜡,开发人员正在学习农历如何计算时间");
        jsonObject.put("name", day.name());
        jsonObject.put("tip", day.name()+"放假一共"+day.GetHowlong()+"天");
        return jsonObject;

    }

    private JSONObject SpecialDate(Holiday day) {
        JSONObject jsonObject = new JSONObject();
        String qmj = "清明节";
        if (qmj.equals(day.name())) {
            int d = (int) (datetime.getYear() % 100 * 0.2422 + 4.81) - (datetime.getYear() % 100) / 4;
            LocalDate target = LocalDate.of(datetime.getYear(), day.GetMonth(), d);
            jsonObject.put("date", target);
            jsonObject.put("holiday", day.GetMonth()+"月" + d + "号");
            jsonObject.put("name", day.name());
            jsonObject.put("tip", "清明节放假一共"+day.GetHowlong()+"天");
        }else {
            LocalDate target = LocalDate.of(datetime.getYear(), day.GetMonth(),day.GetDay() );
            jsonObject.put("date", target);
            jsonObject.put("holiday", day.GetMonth()+"月" + day.GetDay() + "号");
            jsonObject.put("name", day.name());
            jsonObject.put("tip", day.name()+"放假一共"+day.GetHowlong()+"天");
        }
        return jsonObject;
    }
}
