package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;
import com.fpt.automatedtesting.admins.Admin;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.params.Param;
import com.fpt.automatedtesting.admins.AdminRepository;
import com.fpt.automatedtesting.subjects.SubjectRepository;
import com.fpt.automatedtesting.subjects.Subject;
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
        List<Action> actions = actionRepository.findAllByActiveIsTrue();
        if (actions != null && actions.size() > 0) {
            return MapperManager.mapAll(actions, ActionResponseDto.class);
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any action");
        }
    }

    @Override
    public ActionResponseDto insert(ActionRequestDto dto) {
        Admin admin = adminRepository
                .findByIdAndActiveIsTrue(dto.getAdminId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Action is not found with Id " + dto.getAdminId()));
        Action action = MapperManager.map(dto, Action.class);
        action.setAdmin(admin);
        List<Param> params = MapperManager.mapAll(dto.getParams(), Param.class);
        action.setParams(params);
        Subject subject = subjectRepository
                .findByIdAndActiveIsTrue(dto.getSubjectId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Subject is not found with Id " + dto.getAdminId()));
        action.setSubject(subject);
        params.forEach(param -> param.setAction(action));
        Action result = actionRepository.saveAndFlush(action);
        if (result == null) {
            throw new CustomException(HttpStatus.CONFLICT, "Save new action failed ! Please try later");
        }
        return MapperManager.map(result, ActionResponseDto.class);
    }

    @Override
    public ActionResponseDto update(ActionRequestDto dto) {
        if (findById(dto.getId()) != null) {
            Action action = MapperManager.map(dto, Action.class);
            List<Param> params = MapperManager.mapAll(dto.getParams(), Param.class);
            action.setParams(params);
            Admin admin = adminRepository
                    .findByIdAndActiveIsTrue(dto.getAdminId())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Action is not found with Id " + dto.getAdminId()));
            Subject subject = subjectRepository
                    .findByIdAndActiveIsTrue(dto.getSubjectId())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Subject is not found with Id " + dto.getAdminId()));
            action.setActive(true);
            action.setAdmin(admin);
            action.setSubject(subject);
            params.forEach(param -> param.setAction(action));
            Action result = actionRepository.saveAndFlush(action);
            if (result == null) {
                throw new CustomException(HttpStatus.CONFLICT, "Save new action failed ! Please try later");
            }
            return MapperManager.map(result, ActionResponseDto.class);
        }
        return null;
    }

    @Override
    public ActionResponseDto findById(int id) {
        ActionResponseDto response = MapperManager.map(actionRepository
                        .findByIdAndActiveIsTrue(id)
                        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Action is not found with Id " + id))
                , ActionResponseDto.class);
        return response;
    }

    @Override
    public List<ActionResponseDto> getAllActionBySubject(int subjectId) {
        Subject subject = subjectRepository
                .findByIdAndActiveIsTrue(subjectId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Subject is not found with Id " + subjectId));
        List<Action> actions = actionRepository.findAllBySubjectAndActiveIsTrue(subject);
        List<ActionResponseDto> response = MapperManager.mapAll(actions,ActionResponseDto.class);
        response.forEach(element -> element.setSubjectId(subjectId));
        return response;
    }

    @Override
    public String delete(int id) {
        Action action = actionRepository
                .findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Action is not found with Id " + id));
        action.setActive(false);
        Action result = actionRepository.saveAndFlush(action);
        if (result == null) {
            throw new CustomException(HttpStatus.CONFLICT, "Delete action failed ! Please try later");
        }
        return "Delete action successfully";
    }
}
