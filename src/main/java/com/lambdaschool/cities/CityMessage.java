package com.lambdaschool.cities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data

public class CityMessage implements Serializable
{
    // fields
    private final String text;
    private final int priority; // is this mssg a priority?
    private final boolean secret; // true or false - is it a secret?

    // constructor
    public CityMessage(@JsonProperty("Text") String text,
                       @JsonProperty("priority") int priority,
                       @JsonProperty("secret") boolean secret)
    {
        this.text = text;
        this.priority = priority;
        this.secret = secret;
    }
}
