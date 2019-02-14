package com.lambdaschool.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j // logging
@RestController
public class CityController
{
    // fields
    private final CityRepository cityrepos;
    private final RabbitTemplate ct;

    // constructor
    public CityController(CityRepository cityrepos, RabbitTemplate ct)
    {
        this.cityrepos = cityrepos;
        this.ct = ct;
    }

    // -------------------------------------------------------------------
    // http://localhost:8080/cities
//    @GetMapping("/cities")
//    public void findSome()
//    {
//        ArrayList<City> cities = new ArrayList<City>();
//        cities.addAll(cityrepos.findAll());
//
//        for (City c: cities)
//        {
//            int rand = new Random().nextInt(10);
//            boolean randBool = new Random().nextBoolean();
//            final CityMessage message = new CityMessage(c.toString(), rand, randBool);
//
//            log.info("Sending Message...");
//            if (rand <= 6)
//            {
//                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES1, message);
//            } else
//            {
//                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES2, message);
//            }
//        }
//    }

    // -------------------------------------------------------------------
    public CityMessage makeMssg(City c)
    {
        int rand = new Random().nextInt(10);
        boolean randBool = new Random().nextBoolean();
        return new CityMessage(c.toString(), rand, randBool);
    }

    // http://localhost:8080/cities/afford
    @GetMapping("/cities/afford")
    public void findAfford()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities)
        {
            final CityMessage message = makeMssg(c);
            log.info("Sending Message...");

            if (message.isSecret())
            {
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_SECRET, message);
            } else if (c.getAffordIndex() < 6)

            {
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES1, message);
            } else

            {
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES2, message);
            }
        }
    }

    // -------------------------------------------------------------------
    // http://localhost:8080/cities/homes
    @GetMapping("/cities/homes")
    public void findHomes()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities)
        {
            final CityMessage message = makeMssg(c);

            log.info("Sending Message...");
            if (message.isSecret())
            {
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_SECRET, message);
            } else if (c.getHomePrice() < 200000)
            {
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES1, message);
            } else
            {
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES2, message);
            }
        }
    }

    // -------------------------------------------------------------------
    // http://localhost:8080/cities/names
    @GetMapping("/cities/names")
    public void findNames()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities)
        {
            final CityMessage message = makeMssg(c);
            log.info("Sending Message...");

            if (message.isSecret())
            {
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_SECRET, message);
            } else
            {
                ct.convertAndSend(CitiesApplication.QUEUE_NAME_CITIES1, message);
            }
        }
    }
}
