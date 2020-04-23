package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.*;
import com.fpt.automatedtesting.admins.Admin;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.parameters.Parameter;
import com.fpt.automatedtesting.admins.AdminRepository;
import com.fpt.automatedtesting.parameters.dtos.ParameterResponseDto;
import com.fpt.automatedtesting.parameters.dtos.ParameterTypeDto;
import com.fpt.automatedtesting.parametertypes.ParameterType;
import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeResponseDto;
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
    private final ActionParameterRepository actionParameterRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository, AdminRepository adminRepository, SubjectRepository subjectRepository, ActionParameterRepository actionParameterRepository) {
        this.actionRepository = actionRepository;
        this.adminRepository = adminRepository;
        this.subjectRepository = subjectRepository;
        this.actionParameterRepository = actionParameterRepository;
    }

    @Override
    public List<ActionResponseDto> getAllActionsBySubjectId(int subjectId) {

        List<Action> actionEntities = actionRepository.findAllBySubjectAndActiveIsTrue(subjectId);
        List<ActionResponseDto> listActionResponseDto = new ArrayList<>();

        if (actionEntities != null && actionEntities.size() > 0) {

            ActionResponseDto actionDto;

            for (Action actionEntity : actionEntities) {

                actionDto = new ActionResponseDto();
                actionDto.setId(actionEntity.getId());
                actionDto.setName(actionEntity.getName());
                actionDto.setCode(actionEntity.getCode());

                SubjectResponseDto subjectDto = MapperManager.map(actionEntity.getSubject(), SubjectResponseDto.class);
                actionDto.setSubject(subjectDto);

                List<ActionParameter> actionParamEntities = actionEntity.getActionParameters();
                List<ActionParameterResponseDto> listActionParamDto = new ArrayList<>();

                if (actionParamEntities != null && actionParamEntities.size() > 0) {

                    for (ActionParameter actionParam : actionParamEntities) {
                        ParameterResponseDto paramDto = MapperManager.map(actionParam.getParameter(), ParameterResponseDto.class);
                        ParameterTypeResponseDto paramTypeDto = MapperManager.map(actionParam.getParameterType(), ParameterTypeResponseDto.class);

                        ActionParameterResponseDto actionParamDto = new ActionParameterResponseDto();
                        actionParamDto.setId(actionParam.getId());
                        actionParamDto.setParam(paramDto);
                        actionParamDto.setParamType(paramTypeDto);

                        listActionParamDto.add(actionParamDto);
                    }
                }

                actionDto.setActionParams(listActionParamDto);

                listActionResponseDto.add(actionDto);
            }
            return listActionResponseDto;
        } else {
            return listActionResponseDto;
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

        // get list action - parameter from dto
        List<ActionParameterRequestDto> listActionParamDto = dto.getActionParams();
        List<ActionParameter> actionParamEntities;

        // if action - parameter is not null and size > 0
        if (listActionParamDto != null && listActionParamDto.size() > 0) {

            // create list action - parameter entities
            actionParamEntities = new ArrayList<>();

            for (ActionParameterRequestDto actionParamDto : listActionParamDto) {
                // Map parameter entity and parameter type entity from dto
                Parameter paramEntity = MapperManager.map(actionParamDto.getParam(), Parameter.class);
                ParameterType paramType = MapperManager.map(actionParamDto.getParamType(), ParameterType.class);

                // create action - parameter entity and add to the list action - parameter entities
                ActionParameter actionParamEntity = new ActionParameter();
                actionParamEntity.setParameter(paramEntity);
                actionParamEntity.setParameterType(paramType);
                actionParamEntity.setAction(action);

                actionParamEntities.add(actionParamEntity);
            }

            // set list action - parameter entities to action
            action.setActionParameters(actionParamEntities);
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

        // get subject entity by id
        Subject subject = subjectRepository.findByIdAndActiveIsTrue(dto.getSubject().getId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject with id " + dto.getId()));

        action.setSubject(subject);

        List<ActionParameterRequestDto> listActionParamDto = dto.getActionParams();

        List<ActionParameter> actionParamEntities = null;

        if (listActionParamDto != null && listActionParamDto.size() > 0) {

            actionParamEntities = new ArrayList<>();

            ActionParameter actionParam;

            for (ActionParameterRequestDto actionParamDto : listActionParamDto) {
                Parameter param = MapperManager.map(actionParamDto.getParam(), Parameter.class);
                ParameterType paramType = MapperManager.map(actionParamDto.getParamType(), ParameterType.class);

                // create new action - parameter
                actionParam = new ActionParameter();
                actionParam.setParameter(param);
                actionParam.setParameterType(paramType);
                actionParam.setAction(action);

                actionParamEntities.add(actionParam);
            }
        }

        // remove all action - parameter - type relation
        for (ActionParameter actionParamDelete : action.getActionParameters()) {
            actionParameterRepository.delete(actionParamDelete);
        }

        // set list action - parameter for action
        action.setActionParameters(actionParamEntities);

        if (actionRepository.saveAndFlush(action) != null)
            return CustomConstant.UPDATE_ACTION_SUCCESS;
        else
            return CustomConstant.UPDATE_ACTION_FAIL;
    }

    @Override
    public List<ActionParameterDto> getAllActionBySubject(int subjectId) {

        Subject subject = subjectRepository
                .findByIdAndActiveIsTrue(subjectId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject with id " + subjectId));

        // get list actions by subject
        List<Action> actionEntities = actionRepository.findAllBySubjectAndActiveIsTrue(subject.getId());
        List<ActionParameterDto> response = new ArrayList<>();

        if (actionEntities != null && actionEntities.size() > 0) {

            for (Action action : actionEntities) {

                ActionParameterDto actionParamDto = MapperManager.map(action, ActionParameterDto.class);
                List<ActionParameter> subjectActionParam = action.getActionParameters();

                for (ActionParameter element : subjectActionParam) {

                    Parameter param = element.getParameter();
                    ParameterType typeEntity = element.getParameterType();
                    ParameterTypeDto paramTypeDto = new ParameterTypeDto();
                    paramTypeDto.setId(param.getId());
                    paramTypeDto.setName(param.getName());

                    ParameterTypeResponseDto type = MapperManager.map(typeEntity, ParameterTypeResponseDto.class);
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
