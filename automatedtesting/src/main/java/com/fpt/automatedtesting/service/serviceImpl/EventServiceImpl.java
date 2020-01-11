package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.dto.request.EventRequestDto;
import com.fpt.automatedtesting.dto.request.ParamRequestDto;
import com.fpt.automatedtesting.dto.response.EventResponseDto;
import com.fpt.automatedtesting.dto.response.ParamResponseDto;
import com.fpt.automatedtesting.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Override
    public List<EventResponseDto> getAllEvent() {

        // list all events
        List<EventResponseDto> listEvents = new ArrayList<>();

        EventResponseDto dto = new EventResponseDto();
        dto.setName("sendKeysToElementById");
        dto.setCode("driver.findElement(By.id(paramName)).clear(); <br/>" +
                "driver.findElement(By.id(paramName)).sendKeys(paramValue); ");
        dto.setSubject("Java");
        listEvents.add(dto);

        dto = new EventResponseDto();
        dto.setName("sendKeysToElementByName");
        dto.setCode("driver.findElement(By.name(paramName)).clear(); <br/>" +
                "driver.findElement(By.name(paramName)).sendKeys(paramValue); ");
        dto.setSubject("C# .NET");
        listEvents.add(dto);

        return listEvents;
    }


    @Override
    public EventResponseDto insertNewEvent(EventRequestDto dto) {

        EventResponseDto response = new EventResponseDto();
        response.setName(dto.getName());
        response.setCode(dto.getCode());
        List<ParamResponseDto> requestParam = new ArrayList<>();
        if (requestParam != null || !requestParam.isEmpty()) {
            for (ParamRequestDto paramRequest : dto.getParams()) {
                requestParam.add(new ParamResponseDto(paramRequest.getName(), paramRequest.getType()));
            }
        }
        response.setListParams(requestParam);
        response.setSubject(dto.getSubject());
        return response;
    }

}
