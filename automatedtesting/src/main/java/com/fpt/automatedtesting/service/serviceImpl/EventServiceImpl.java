package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.dto.response.EventResponseDto;
import com.fpt.automatedtesting.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Override
    public List<EventResponseDto> getAllEvent() {
        List<EventResponseDto> listEvents = new ArrayList<>();
        EventResponseDto dto = new EventResponseDto();
        dto.setName("sendKeysToElementById");
        dto.setCode("driver.findElement(By.id(paramName)).clear(); \n" +
                "driver.findElement(By.id(paramName)).sendKeys(paramValue); ");
        dto.setSubject("Java");
        listEvents.add(dto);

        dto.setName("sendKeysToElementByName");
        dto.setCode("driver.findElement(By.name(paramName)).clear(); \n" +
                "driver.findElement(By.name(paramName)).sendKeys(paramValue); ");
        dto.setSubject("C# .NET");
        listEvents.add(dto);

        return listEvents;
    }
}
