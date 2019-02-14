package com.lambdaschool.cities;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CitiesApplication
{
    public static final String EXCHANGE_NAME = "CityLambdaServer";
    public static final String QUEUE_NAME_CITIES1 = "Cities 1 Queue";
    public static final String QUEUE_NAME_CITIES2 = "Cities 2 Queue";
    public static final String QUEUE_NAME_SECRET = "Secret Queue";

    public static void main(String[] args)
    {
        SpringApplication.run(CitiesApplication.class, args);
    }

    @Bean
    public TopicExchange appExchange()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean //---------------------------------
    public Queue appQueueSecret()
    {
        return new Queue(QUEUE_NAME_SECRET);
    }

    @Bean
    public Binding declareBindSecret()
    {
        return BindingBuilder.bind(appQueueSecret()).to(appExchange()).with(QUEUE_NAME_SECRET);
    } //--------------------------------------

    @Bean
    public Queue appQueueNameCities1()
    {
        return new Queue(QUEUE_NAME_CITIES1);
    }

    @Bean
    public Binding declareBindingGreaterThan6()
    {
        return BindingBuilder.bind(appQueueNameCities1()).to(appExchange()).with(QUEUE_NAME_CITIES1);
    }

    @Bean
    public Queue appQueueNameCities2()
    {
        return new Queue(QUEUE_NAME_CITIES2);
    }

    @Bean
    public Binding declareBindingLessThan6()
    {
        return BindingBuilder.bind(appQueueNameCities2()). to(appExchange()).with(QUEUE_NAME_CITIES2);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

}

