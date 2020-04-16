package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseSubjectIdDto;
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

import java.util.ArrayList;
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
//        List<Action> actions = actionRepository.findAllByActiveIsTrue();
//        if (actions != null && actions.size() > 0) {
//            List<ActionResponseDto> actionResponseDtos = MapperManager.mapAll(actions, ActionResponseDto.class);
//            for (int i = 0; i < actions.size(); i++) {
//                List<Subject> subjects = actions.get(i).getSubjects();
//                ArrayList<String> subjectNames = new ArrayList<>();
//                for (Subject subject : subjects) {
//                    subjectNames.add(subject.getName());
//                }
//                actionResponseDtos.get(i).setSubjectName(subjectNames);
//            }
//            return actionResponseDtos;
//        } else {
//            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any action");
//        }
        return null;
    }

    @Override
    public ActionResponseDto insert(ActionRequestDto dto) {
//        Admin admin = adminRepository
//                .findByIdAndActiveIsTrue(dto.getAdminId())
//                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Action is not found with Id " + dto.getAdminId()));
//        Action action = MapperManager.map(dto, Action.class);
//        action.setAdmin(admin);
//        List<Param> params = MapperManager.mapAll(dto.getParams(), Param.class);
//        action.setParams(params);
//        Subject subject = subjectRepository
//                .findByIdAndActiveIsTrue(dto.getSubjectId())
//                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Subject is not found with Id " + dto.getAdminId()));
//        List<Subject> subjects = action.getSubjects();
//        if (subjects == null) {
//            List<Subject> newSubjects = new ArrayList<>();
//            newSubjects.add(subject);
//            action.setSubjects(newSubjects);
//        } else {
//            action.getSubjects().add(subject);
//        }
//        for (Param param : params) {
//            List<Action> actions = param.getActions();
//            if (actions == null) {
//                List<Action> newActions = new ArrayList<>();
//                newActions.add(action);
//                param.setActions(newActions);
//            } else {
//                param.getActions().add(action);
//            }
//        }
//        Action result = actionRepository.saveAndFlush(action);
//        if (result == null) {
//            throw new CustomException(HttpStatus.CONFLICT, "Save new action failed ! Please try later");
//        }
//        return MapperManager.map(result, ActionResponseDto.class);
        return null;
    }

    @Override
    public ActionResponseDto update(ActionRequestDto dto) {
//        if (findById(dto.getId()) != null) {
//            Action action = MapperManager.map(dto, Action.class);
//            List<Param> params = MapperManager.mapAll(dto.getParams(), Param.class);
//            action.setParams(params);
//            Admin admin = adminRepository
//                    .findByIdAndActiveIsTrue(dto.getAdminId())
//                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Action is not found with Id " + dto.getAdminId()));
//            Subject subject = subjectRepository
//                    .findByIdAndActiveIsTrue(dto.getSubjectId())
//                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Subject is not found with Id " + dto.getAdminId()));
//            action.setActive(true);
//            action.setAdmin(admin);
//            action.getSubjects().add(subject);
//            params.forEach(param -> param.getActions().add(action));
//            Action result = actionRepository.saveAndFlush(action);
//            if (result == null) {
//                throw new CustomException(HttpStatus.CONFLICT, "Save new action failed ! Please try later");
//            }
//            return MapperManager.map(result, ActionResponseDto.class);
//        }
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
    public List<ActionResponseSubjectIdDto> getAllActionBySubject(int subjectId) {
        Subject subject = subjectRepository
                .findByIdAndActiveIsTrue(subjectId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Subject is not found with Id " + subjectId));
        List<Action> actions = actionRepository.findAllBySubjectAndActiveIsTrue(subject.getId());
        List<ActionResponseSubjectIdDto> response = new ArrayList<>();
        if (actions.size() > 0) {
            response = MapperManager.mapAll(actions, ActionResponseSubjectIdDto.class);
            List<Integer> subjects = new ArrayList<>();
            subjects.add(subjectId);
            response.forEach(element -> element.setSubjectId(subjects));
        }
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
