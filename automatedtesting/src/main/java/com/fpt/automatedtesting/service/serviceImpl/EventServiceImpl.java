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
        List<ParamResponseDto> params = new ArrayList<>();
        params.add(new ParamResponseDto("txtUsername", "thanhtd"));
        params.add(new ParamResponseDto("txtPassword", "123456"));
        dto.setListParams(params);
        listEvents.add(dto);

        dto = new EventResponseDto();
        dto.setName("sendKeysToElementByName");
        dto.setCode("driver.findElement(By.name(paramName)).clear(); <br/>" +
                "driver.findElement(By.name(paramName)).sendKeys(paramValue); ");
        params.clear();
        params.add(new ParamResponseDto("txtUsername", "thanhtd"));
        params.add(new ParamResponseDto("txtPassword", "123456"));
        dto.setListParams(params);
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
            for (ParamRequestDto paramRequest : dto.getListParams()) {
                requestParam.add(new ParamResponseDto(paramRequest.getParamName(), paramRequest.getParamType()));
            }
        }
        response.setListParams(requestParam);
        response.setSubject(dto.getSubject());

        return response;
    }

}
