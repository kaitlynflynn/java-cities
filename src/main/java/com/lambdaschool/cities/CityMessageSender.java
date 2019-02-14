package com.lambdaschool.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Random;

@Slf4j // logging

public class CityMessageSender
{
    // fields
    private final RabbitTemplate ct;
    private final CityRepository cityrepos;

    // constructor
    public CityMessageSender(RabbitTemplate ct, CityRepository cityrepos)
    {
        this.ct = ct;
        this.cityrepos = cityrepos;
    }

    // send a mssg every 3 secs
    @Scheduled(fixedDelay = 3000L)
    public void sendMessage()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c: cities)
        {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            if (rand <= 6)
            {
                log.info("Sending Message CITIES1");
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES1, message);
            }
            else
            {
                log.info("Sending Message CITIES2");
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES2, message);
            }
        }
    }
}
