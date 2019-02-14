package com.lambdaschool.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j // for logging
@Service

public class CityMessageListener
{
    @RabbitListener(queues =
            {
                    CitiesApplication.QUEUE_NAME_CITIES1,
                    CitiesApplication.QUEUE_NAME_CITIES2,
                    CitiesApplication.QUEUE_NAME_SECRET})
    public void receiveMessage(CityMessage cm)
    {
        log.info("Received Message: {} ", cm.toString());
    }
}
