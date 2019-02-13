package com.lambdaschool.cities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity

public class City
{
    // fields
    private @Id @GeneratedValue Long id;
    private String cityState;
    private double homePrice;
    private int affordIndex;

    public City()
    {
        // default constructor
    }

    // constructor

    public City(String cityState, double homePrice, int affordIndex)
    {
        this.cityState = cityState;
        this.homePrice = homePrice;
        this.affordIndex = affordIndex;
    }
}
