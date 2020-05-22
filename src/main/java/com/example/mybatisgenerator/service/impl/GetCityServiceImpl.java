package com.example.mybatisgenerator.service.impl;

import com.example.mybatisgenerator.dao.CityMapper;
import com.example.mybatisgenerator.entity.City;
import com.example.mybatisgenerator.service.GetCityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetCityServiceImpl implements GetCityService {


    @Resource
    private CityMapper cityMapper;

    @Override
    public List<City> getCityList() {
        System.out.println("====================");
        List<City> cityList = null;
        try {
            cityList = cityMapper.getCityList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(cityList);
        System.out.println("----------------");
        return cityList;
    }
}
