package com.fpt.automatedtesting.parametertypes;

import com.fpt.automatedtesting.actions.ActionParameter;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.exception.CustomException;

import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeRequestDto;

import com.fpt.automatedtesting.subjects.Subject;
import com.fpt.automatedtesting.subjects.SubjectRepository;

import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParameterTypeServiceImpl implements ParameterTypeService {

    private final ParameterTypeRepository parameterTypeRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public ParameterTypeServiceImpl(ParameterTypeRepository parameterTypeRepository, SubjectRepository subjectRepository) {
        this.parameterTypeRepository = parameterTypeRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<ParameterTypeResponseDto> getAllParamType() {

        List<ParameterType> paramTypes = parameterTypeRepository.findAllByActiveIsTrue();

        if (paramTypes != null && paramTypes.size() > 0) {
            List<ParameterTypeResponseDto> responseDtos = MapperManager.mapAll(paramTypes, ParameterTypeResponseDto.class);

            if (responseDtos != null && responseDtos.size() > 0) {
                return responseDtos;
            } else {
                throw new CustomException(HttpStatus.NOT_FOUND, "Not found any parameter type.");
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any parameter type.");
        }
    }

    @Override
    public List<ParameterTypeResponseDto> getParamTypeBySubjectId(Integer subjectId) {
        Optional<Subject> subject = subjectRepository.findByIdAndActiveIsTrue(subjectId);
        List<ParameterTypeResponseDto> result = new ArrayList<>();
        if(subject.isPresent()){
            String subjectCode = subject.get().getCode();
            List<ParameterType> paramTypes = parameterTypeRepository.findAllBySubjectCodeAndActiveIsTrue(subjectCode);
            result = MapperManager.mapAll(paramTypes, ParameterTypeResponseDto.class);
        }
        return result;
    }

    @Transactional
    @Override
    public String createParamType(ParameterTypeRequestDto dto) {

        List<ParameterType> saveEntities;
        List<String> subjectCodes = dto.getSubjectCode();
        int duplicatedTypeCounter = 0;

        if (subjectCodes != null && subjectCodes.size() > 0) {

            saveEntities = new ArrayList<>();

            for (String subjectCode : subjectCodes) {

                // check the existence of a pair values (name - subject code)
                ParameterType checkExistedEntity = parameterTypeRepository.findByNameAndSubjectCode(dto.getName(), subjectCode);
                ParameterType saveParamTypeEntity;

                if (checkExistedEntity == null) {

                    // if name - subject code does not exist in DB -> create new row in DB
                    saveParamTypeEntity = new ParameterType();
                    saveParamTypeEntity.setName(dto.getName());
                    saveParamTypeEntity.setSubjectCode(subjectCode);
                    saveParamTypeEntity.setActive(true);

                    saveEntities.add(saveParamTypeEntity);
                } else {

                    // if name - subject code existed -> check if active is false -> set to true
                    if (!checkExistedEntity.getActive()) {
                        checkExistedEntity.setActive(true);
                        saveEntities.add(checkExistedEntity);
                    } else {
                        // else do nothing
                        duplicatedTypeCounter++;
                    }
                }
            }

            if (duplicatedTypeCounter == subjectCodes.size()) {
                return "Parameter type is already existed.";
            }

            List<ParameterType> paramTypeEntities = parameterTypeRepository.saveAll(saveEntities);

            if (paramTypeEntities != null || paramTypeEntities.size() > 0) {
                return CustomConstant.CREATE_PARAM_TYPE_SUCCESS;
            } else {
                return CustomConstant.CREATE_PARAM_TYPE_FAIL;
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any subject.");
        }
    }

    @Override
    public String deleteParamType(Integer id) {

        ParameterType paramTypeEntity = parameterTypeRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found parameter type for id " + id));

        List<ActionParameter> actionParams = paramTypeEntity.getActionParameters();

        if (actionParams == null || actionParams.size() <= 0) {
            paramTypeEntity.setActive(false);

            if (parameterTypeRepository.save(paramTypeEntity) != null) {
                return CustomConstant.DELETE_PARAM_TYPE_SUCCESS;
            } else
                return CustomConstant.DELETE_PARAM_TYPE_FAIL;
        } else
            return "Parameter type is already in use.";
    }
}
