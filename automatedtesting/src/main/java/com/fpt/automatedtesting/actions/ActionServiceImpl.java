package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.*;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.params.Param;
import com.fpt.automatedtesting.admins.AdminRepository;
import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import com.fpt.automatedtesting.params.dtos.ParamTypeDTO;
import com.fpt.automatedtesting.paramtypes.ParamType;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeResponseDto;
import com.fpt.automatedtesting.subjects.SubjectRepository;
import com.fpt.automatedtesting.subjects.Subject;
import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;
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

        List<Action> actions = actionRepository.findAllByActiveIsTrue();

        if (actions != null && actions.size() > 0) {

            // list action dto
            List<ActionResponseDto> listActionDto = new ArrayList<>();
            ActionResponseDto actionResponseDto = null;

            for (Action action : actions) {

                // Get list of subject - action entities
                List<SubjectAction> subjectActions = action.getSubjectActions();

                // list of subject - action dto
                List<SubjectActionResponseDto> listSubjectActionDto = null;

                if (subjectActions != null && subjectActions.size() > 0) {

                    listSubjectActionDto = new ArrayList<>();

                    for (SubjectAction subjectAction : subjectActions) {

                        // retrieve list of subject - action - param entities
                        List<SubjectActionParam> subjectActionParamEntities = subjectAction.getSubjectActionParams();

                        // list of subject - action - param dto
                        List<SubjectActionParamResponseDto> listSubjectActionParamDto = null;

                        if (subjectActionParamEntities != null && subjectActionParamEntities.size() > 0) {

                            listSubjectActionParamDto = new ArrayList<>();

                            for (SubjectActionParam sap : subjectActionParamEntities) {

                                ParamResponseDto paramDto = MapperManager.map(sap.getParam(), ParamResponseDto.class);
                                ParamTypeResponseDto paramTypeDto = MapperManager.map(sap.getParamType(), ParamTypeResponseDto.class);

                                SubjectActionParamResponseDto subjectActionParamDto = new SubjectActionParamResponseDto(sap.getId(), paramDto, paramTypeDto);

                                listSubjectActionParamDto.add(subjectActionParamDto);
                            }
                        }

                        SubjectResponseDto subjectDto = MapperManager.map(subjectAction.getSubject(), SubjectResponseDto.class);

                        SubjectActionResponseDto subjectActionDto = new SubjectActionResponseDto();
                        subjectActionDto.setId(subjectAction.getId());
                        subjectActionDto.setSubject(subjectDto);
                        subjectActionDto.setSubjectActionParams(listSubjectActionParamDto);

                        listSubjectActionDto.add(subjectActionDto);
                    }
                }

                actionResponseDto = new ActionResponseDto();
                actionResponseDto.setId(action.getId());
                actionResponseDto.setName(action.getName());
                actionResponseDto.setCode(action.getCode());
                actionResponseDto.setSubjectActions(listSubjectActionDto);

                listActionDto.add(actionResponseDto);
            }
            return listActionDto;
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any action");
        }
    }

    @Override
    public ActionResponseDto insertAction(ActionRequestDto dto) {
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
    public List<ActionParamDTO> getAllActionBySubject(int subjectId) {
        Subject subject = subjectRepository
                .findByIdAndActiveIsTrue(subjectId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Subject is not found with Id " + subjectId));
//        List<Action> actions = actionRepository.findAllBySubjectAndActiveIsTrue(subject.getId());
//        List<ActionResponseSubjectIdDto> response = new ArrayList<>();
//        if (actions.size() > 0) {
//            response = MapperManager.mapAll(actions, ActionResponseSubjectIdDto.class);
//            List<Integer> subjects = new ArrayList<>();
//            subjects.add(subjectId);
//            response.forEach(element -> element.setSubjectId(subjects));
//        }
        List<ActionParamDTO> response = new ArrayList<>();
        List<SubjectAction> subjectActions = subject.getSubjectActions();
        for (SubjectAction item : subjectActions) {
            Action action = item.getAction();
            ActionParamDTO actionParamDTO = MapperManager.map(action, ActionParamDTO.class);
            List<SubjectActionParam> subjectActionParam = item.getSubjectActionParams();
            for (SubjectActionParam element : subjectActionParam) {
                Param param = element.getParam();
                ParamType typeEntity = element.getParamType();
                ParamTypeDTO paramTypeDTO = new ParamTypeDTO();
                paramTypeDTO.setId(param.getId());
                paramTypeDTO.setName(param.getName());
                ParamTypeResponseDto type = MapperManager.map(typeEntity, ParamTypeResponseDto.class);
                // paramTypeDTO.setType(type);
                paramTypeDTO.setType(type.getName());
                actionParamDTO.getParams().add(paramTypeDTO);
            }
            actionParamDTO.getSubjectId().add(subjectId);
            response.add(actionParamDTO);
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
