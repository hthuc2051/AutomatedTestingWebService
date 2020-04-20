package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.*;
import com.fpt.automatedtesting.admins.Admin;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.params.Param;
import com.fpt.automatedtesting.admins.AdminRepository;
import com.fpt.automatedtesting.params.ParamRepository;
import com.fpt.automatedtesting.params.dtos.ParamTypeDto;
import com.fpt.automatedtesting.paramtypes.ParamType;
import com.fpt.automatedtesting.paramtypes.ParamTypeRepository;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeResponseDto;
import com.fpt.automatedtesting.subjects.SubjectRepository;
import com.fpt.automatedtesting.subjects.Subject;
import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<ActionResponseDto> getAllActions() {

        List<Action> actionEntities = actionRepository.findAllByActiveIsTrue();
        List<ActionResponseDto> listActionResponseDto;

        if (actionEntities != null && actionEntities.size() > 0) {
            listActionResponseDto = new ArrayList<>();
            ActionResponseDto actionDto;

            for (Action actionEntity : actionEntities) {

                actionDto = new ActionResponseDto();
                actionDto.setId(actionEntity.getId());
                actionDto.setName(actionEntity.getName());
                actionDto.setCode(actionEntity.getCode());

                SubjectResponseDto subjectDto = MapperManager.map(actionEntity.getSubject(), SubjectResponseDto.class);
                actionDto.setSubject(subjectDto);

                List<ActionParam> actionParamEntities = actionEntity.getActionParams();
                if (actionParamEntities != null && actionParamEntities.size() > 0) {
                    List<ActionParamResponseDto> listActionParamDto = MapperManager.mapAll(actionEntities, ActionParamResponseDto.class);
                    actionDto.setActionParams(listActionParamDto);
                }

                listActionResponseDto.add(actionDto);
            }
            return listActionResponseDto;
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any action.");
        }
    }

    @Transactional
    @Override
    public String createAction(ActionRequestDto dto) {

        Admin admin = adminRepository.findByIdAndActiveIsTrue(dto.getAdminId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found admin with id " + dto.getAdminId()));

        // create new Action
        Action action = new Action();
        action.setName(dto.getName());
        action.setCode(dto.getCode());
        action.setActive(true);
        action.setAdmin(admin);

        // get subject from id of subject dto
        Subject subject = subjectRepository.findByIdAndActiveIsTrue(dto.getSubject().getId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject with id " + dto.getSubject().getId()));

        action.setSubject(subject);

        // get list action - param from dto
        List<ActionParamRequestDto> listActionParamDto = dto.getActionParams();
        List<ActionParam> actionParamEntities;

        // if action - param is not null and size > 0
        if (listActionParamDto != null && listActionParamDto.size() > 0) {

            // create list action - param entities
            actionParamEntities = new ArrayList<>();

            for (ActionParamRequestDto actionParamDto : listActionParamDto) {
                // Map param entity and param type entity from dto
                Param paramEntity = MapperManager.map(actionParamDto.getParam(), Param.class);
                ParamType paramType = MapperManager.map(actionParamDto.getParamType(), ParamType.class);

                // create action - param entity and add to the list action - param entities
                ActionParam actionParamEntity = new ActionParam();
                actionParamEntity.setParam(paramEntity);
                actionParamEntity.setParamType(paramType);

                actionParamEntities.add(actionParamEntity);
            }

            // set list action - param entities to action
            action.setActionParams(actionParamEntities);
        }

        if (actionRepository.saveAndFlush(action) != null)
            return CustomConstant.CREATE_ACTION_SUCCESS;
        else
            return CustomConstant.CREATE_ACTION_FAIL;
    }

    @Transactional
    @Override
    public String updateAction(ActionRequestDto dto) {
        Action action = actionRepository.findByIdAndActiveIsTrue(dto.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found action with id " + dto.getId()));

        action.setCode(dto.getCode());
        action.setName(dto.getName());

        Subject subject = subjectRepository.findByIdAndActiveIsTrue(dto.getSubject().getId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject with id " + dto.getId()));

        action.setSubject(subject);

        List<ActionParamRequestDto> listActionParamDto = dto.getActionParams();

        List<ActionParam> actionParamEntities;

        if (listActionParamDto != null && listActionParamDto.size() > 0) {

            actionParamEntities = new ArrayList<>();
            ActionParam actionParam;

            for (ActionParamRequestDto actionParamDto : listActionParamDto) {
                Param param = MapperManager.map(actionParamDto.getParam(), Param.class);
                ParamType paramType = MapperManager.map(actionParamDto.getParamType(), ParamType.class);

                actionParam = new ActionParam();
                actionParam.setParam(param);
                actionParam.setParamType(paramType);

                actionParamEntities.add(actionParam);
            }

            action.setActionParams(actionParamEntities);
        }

        if (actionRepository.saveAndFlush(action) != null)
            return CustomConstant.UPDATE_ACTION_SUCCESS;
        else
            return CustomConstant.UPDATE_ACTION_FAIL;
    }

    @Override
    public List<ActionParamDto> getAllActionBySubject(int subjectId) {

        Subject subject = subjectRepository
                .findByIdAndActiveIsTrue(subjectId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject with id " + subjectId));

        // get list actions by subject
        List<Action> actionEntities = actionRepository.findAllBySubjectAndActiveIsTrue(subject.getId());
        List<ActionParamDto> response = new ArrayList<>();

        if (actionEntities != null && actionEntities.size() > 0) {
            for (Action action : actionEntities) {

                ActionParamDto actionParamDto = MapperManager.map(action, ActionParamDto.class);
                List<ActionParam> subjectActionParam = action.getActionParams();

                for (ActionParam element : subjectActionParam) {

                    Param param = element.getParam();
                    ParamType typeEntity = element.getParamType();
                    ParamTypeDto paramTypeDto = new ParamTypeDto();
                    paramTypeDto.setId(param.getId());
                    paramTypeDto.setName(param.getName());

                    ParamTypeResponseDto type = MapperManager.map(typeEntity, ParamTypeResponseDto.class);
                    paramTypeDto.setType(type.getName());

                    actionParamDto.getParams().add(paramTypeDto);
                }

                actionParamDto.setSubjectCode(subject.getCode());
                response.add(actionParamDto);
            }
        }

        return response;
    }

    @Override
    public String deleteAction(int id) {
        Action action = actionRepository
                .findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found action with id " + id));

        action.setActive(false);

        if (actionRepository.saveAndFlush(action) == null)
            return CustomConstant.DELETE_ACTION_FAIL;
        else
            return CustomConstant.DELETE_ACTION_SUCCESS;
    }
}
