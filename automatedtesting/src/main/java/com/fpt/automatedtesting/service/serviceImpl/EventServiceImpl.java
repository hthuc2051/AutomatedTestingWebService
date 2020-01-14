package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.dto.request.ActionRequestDto;
import com.fpt.automatedtesting.dto.response.ActionResponseDto;
import com.fpt.automatedtesting.entity.Action;
import com.fpt.automatedtesting.entity.Param;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.ActionRepository;
import com.fpt.automatedtesting.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private final ActionRepository actionRepository;

    public EventServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }
    @Override
    public List<ActionResponseDto> getAllEvent() {
        List<ActionResponseDto> listResponse = MapperManager.mapAll(actionRepository.findAll(),ActionResponseDto.class);
        return listResponse;
    }
    @Override
    public ActionResponseDto insertNewEvent(ActionRequestDto dto) {
        Action response = MapperManager.map(dto,Action.class);
        List<Param> requestParam = MapperManager.mapAll(dto.getParams(),Param.class);
        response.setParams(requestParam);
        return MapperManager.map(actionRepository.save(response), ActionResponseDto.class);
    }

    @Override
    public ActionResponseDto updateEvent(ActionRequestDto dto) {
        if(getEventById(dto.getId())!= null)
        {
            Action response = MapperManager.map(dto,Action.class);
            List<Param> requestParam = MapperManager.mapAll(dto.getParams(),Param.class);
            response.setParams(requestParam);
            return MapperManager.map(actionRepository.save(response), ActionResponseDto.class);
        }
        return null;
    }

    @Override
    public ActionResponseDto getEventById(int id) {
        ActionResponseDto response = MapperManager.map(actionRepository.findById(id),ActionResponseDto.class);
        return response;
    }

    @Override
    public boolean deleteEventById(int id) {
        try{
            actionRepository.deleteById(id);
            return true;
        }catch(Exception ex)
        {
            return false;
        }
    }
}
