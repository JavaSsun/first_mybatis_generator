package com.example.mybatisgenerator.dao;

import com.example.mybatisgenerator.entity.City;

import java.util.List;


/**
 * @author 101
 */
public interface CityMapper {

    /**
     * 获取城市列表数据
     *
     * @return
     */
    List<City> getCityList();
}