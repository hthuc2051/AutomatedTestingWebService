package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.*;
import com.fpt.automatedtesting.admins.Admin;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.params.Param;
import com.fpt.automatedtesting.admins.AdminRepository;
import com.fpt.automatedtesting.params.ParamRepository;
import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import com.fpt.automatedtesting.params.dtos.ParamTypeDto;
import com.fpt.automatedtesting.paramtypes.ParamType;
import com.fpt.automatedtesting.paramtypes.ParamTypeRepository;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeResponseDto;
import com.fpt.automatedtesting.subjects.SubjectRepository;
import com.fpt.automatedtesting.subjects.Subject;
import com.fpt.automatedtesting.subjects.dtos.SubjectRequestDto;
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
    private final ParamRepository paramRepository;
    private final ParamTypeRepository paramTypeRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository, AdminRepository adminRepository, SubjectRepository subjectRepository, ParamRepository paramRepository, ParamTypeRepository paramTypeRepository) {
        this.actionRepository = actionRepository;
        this.adminRepository = adminRepository;
        this.subjectRepository = subjectRepository;
        this.paramRepository = paramRepository;
        this.paramTypeRepository = paramTypeRepository;
    }

    @Override
    public List<ActionResponseDto> getAll() {

        return null;
    }

    @Override
    public String insertAction(ActionRequestDto dto) {

//        Admin admin = adminRepository.findByIdAndActiveIsTrue(dto.getAdminId())
//                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found admin with id " + dto.getAdminId()));
//
//        // create new Action
//        Action action = new Action();
//        action.setName(dto.getName());
//        action.setCode(dto.getCode());
//        action.setActive(true);
//        action.setAdmin(admin);
//
//        // create list of Subject - Action
//        List<SubjectAction> subjectActionEntities = new ArrayList<>();
//
//        // get Subject entity
//        SubjectRequestDto subjectDto = dto.getSubject();
//        Subject subjectEntity = subjectRepository.findById(subjectDto.getId())
//                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject with id " + subjectDto.getId()));
//
//        // create Subject - Action and add to list
//        SubjectAction subjectActionEntity = new SubjectAction();
//        subjectActionEntity.setSubject(subjectEntity);
//
//        // create list of Subject - Action - Param
//        List<ActionParamRequestDto> listSubjectActionParamDto = dto.getSubjectActionParams();
//        List<ActionParam> subjectActionParamEntities = new ArrayList<>();
//
//        if (listSubjectActionParamDto != null && listSubjectActionParamDto.size() > 0) {
//            for (ActionParamRequestDto sap : listSubjectActionParamDto) {
//                Param paramEntity = paramRepository.findById(sap.getParam().getId())
//                        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param with id " + sap.getParam().getId()));
//                ParamType paramTypeEntity = paramTypeRepository.findById(sap.getParamType().getId())
//                        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param type with id " + sap.getParamType().getId()));
//                ActionParam subjectActionParamEntity = new ActionParam();
//                subjectActionParamEntity.setParam(paramEntity);
//                subjectActionParamEntity.setParamType(paramTypeEntity);
//                subjectActionParamEntities.add(subjectActionParamEntity);
//            }
//        }
//
//        subjectActionEntity.setSubjectActionParams(subjectActionParamEntities);
//        subjectActionEntities.add(subjectActionEntity);
//
//        // set list of Subject - Action to new action
//        action.setSubjectActions(subjectActionEntities);
//
//        if (actionRepository.saveAndFlush(action) != null)
//            return CustomConstant.CREATE_ACTION_SUCCESS;
//        else
//            return CustomConstant.CREATE_ACTION_FAIL;
        return null;
    }

    @Override
    public String update(ActionRequestDto dto) {
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
    public List<ActionParamDto> getAllActionBySubject(int subjectId) {
        Subject subject = subjectRepository
                .findByIdAndActiveIsTrue(subjectId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Subject is not found with Id " + subjectId));
        List<Action> actions = actionRepository.findAllBySubjectAndActiveIsTrue(subject.getId());
        List<ActionParamDto> response = new ArrayList<>();
        for (Action action : actions) {
            ActionParamDto actionParamDto = MapperManager.map(action, ActionParamDto.class);
            List<ActionParam> subjectActionParam = action.getActionParams();
            for (ActionParam element : subjectActionParam) {
                Param param = element.getParam();
                ParamType typeEntity = element.getParamType();
                ParamTypeDto paramTypeDto = new ParamTypeDto();
                paramTypeDto.setId(param.getId());
                paramTypeDto.setName(param.getName());
                ParamTypeResponseDto type = MapperManager.map(typeEntity, ParamTypeResponseDto.class);
                // paramTypeDto.setType(type);
                paramTypeDto.setType(type.getName());
                actionParamDto.getParams().add(paramTypeDto);
            }
            actionParamDto.getSubjectId().add(subjectId);
            response.add(actionParamDto);
        }
        return response;
    }

    @Override
    public String delete(int id) {
        Action action = actionRepository
                .findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found action with id " + id));
        action.setActive(false);
        Action result = actionRepository.saveAndFlush(action);
        if (result == null) {
            throw new CustomException(HttpStatus.CONFLICT, "Delete action failed ! Please try later");
        }
        return "Delete action successfully";
    }
}
