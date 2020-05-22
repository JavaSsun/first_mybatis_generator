package com.example.mybatisgenerator.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mybatisgenerator.dao.CityMapper;
import com.example.mybatisgenerator.entity.City;
import com.example.mybatisgenerator.service.GetCityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("city")
public class CityController {


    @Autowired
    @Qualifier("getCityServiceImpl")
    private GetCityService getCityService;
    @Autowired
    private CityMapper cityMapper;

    private Logger logger = LoggerFactory.getLogger(CityController.class);


    @GetMapping("/getCity")
    public JSONObject getCity(){
        JSONObject jsonObject = new JSONObject();
        try{
            List<City> cityList = getCityService.getCityList();
            jsonObject.put("code","200");
            jsonObject.put("message","success");
            jsonObject.put("data",cityList);
        }catch (Exception e){
            logger.error("获取城市列表失败");
            jsonObject.put("code","400");
            jsonObject.put("message","failure");
        }
        return jsonObject;
    }

    @GetMapping("/getCity2")
    public JSONObject getCity2(){
        JSONObject jsonObject = new JSONObject();
        try{
            List<City> cityList = cityMapper.getCityList();
            jsonObject.put("code","200");
            jsonObject.put("message","success");
            jsonObject.put("data",cityList);
        }catch (Exception e){
            logger.error("获取城市列表失败");
            jsonObject.put("code","400");
            jsonObject.put("message","failure");
        }
        return jsonObject;
    }



}
