package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.dto.request.ActionRequestDto;
import com.fpt.automatedtesting.dto.response.ActionResponseDto;
import com.fpt.automatedtesting.entity.*;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.ActionRepository;
import com.fpt.automatedtesting.repository.AdminRepository;
import com.fpt.automatedtesting.repository.SubjectRepository;
import com.fpt.automatedtesting.service.ActionService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {


    private final ActionRepository actionRepository;
    private final AdminRepository adminRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository, AdminRepository adminRepository, SubjectRepository subjectRepository) {
        this.actionRepository = actionRepository;
        this.adminRepository = adminRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<ActionResponseDto> getAll() {
        List<ActionResponseDto> listResponse = MapperManager.mapAll(actionRepository.findAll(), ActionResponseDto.class);
        return listResponse;
    }

    @Override
    public ActionResponseDto insert(ActionRequestDto dto) {
        Admin admin = adminRepository
                .findById(dto.getAdminId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Admin is not found with Id "+dto.getAdminId()));
        Action action = MapperManager.map(dto, Action.class);
        action.setAdmin(admin);
        List<Param> params = MapperManager.mapAll(dto.getParams(), Param.class);
        params.forEach(param -> param.setAction(action));
        action.setParams(params);
        Subject subject = subjectRepository.getOne(dto.getSubjectId());
        action.setActive(true);
        action.setSubject(subject);
        return MapperManager.map(actionRepository.save(action), ActionResponseDto.class);
    }

    @Override
    public ActionResponseDto update(ActionRequestDto dto) {

        if (findById(dto.getId()) != null) {
            Action response = MapperManager.map(dto, Action.class);
            List<Param> requestParam = MapperManager.mapAll(dto.getParams(), Param.class);
            requestParam.forEach(param -> param.setAction(response));
            response.setParams(requestParam);
            Admin admin = adminRepository.getOne(dto.getAdminId());
            Subject subject = subjectRepository.getOne(dto.getSubjectId());
            response.setActive(true);
            response.setAdmin(admin);
            response.setSubject(subject);
            return MapperManager.map(actionRepository.save(response), ActionResponseDto.class);
        }
        return null;
    }

    @Override
    public ActionResponseDto findById(int id) {
        ActionResponseDto response = MapperManager.map(actionRepository
                .findById(id)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"Action is not found with Id "+id))
                , ActionResponseDto.class);
        return response;
    }

    @Override
    public boolean delete(int id) {
        Action getAction = actionRepository
                .findById(id)
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"Action is not found with Id "+id));

        if(getAction != null)
        {
            getAction.setActive(false);
            actionRepository.save(getAction);
            return true;
        }
        return false;
    }
}
