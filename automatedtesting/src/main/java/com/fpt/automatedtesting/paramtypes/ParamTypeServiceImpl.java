package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.actions.ActionParam;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParamTypeServiceImpl implements ParamTypeService {

    private final ParamTypeRepository paramTypeRepository;

    @Autowired
    public ParamTypeServiceImpl(ParamTypeRepository paramTypeRepository) {
        this.paramTypeRepository = paramTypeRepository;
    }

    @Override
    public List<ParamTypeResponseDto> getAllParamType() {

        List<ParamType> paramTypes = paramTypeRepository.findAllByActiveIsTrue();

        if (paramTypes != null && paramTypes.size() > 0) {
            List<ParamTypeResponseDto> responseDtos = MapperManager.mapAll(paramTypes, ParamTypeResponseDto.class);

            if (responseDtos != null && responseDtos.size() > 0) {
                return responseDtos;
            } else {
                throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param type.");
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param type.");
        }
    }

    @Transactional
    @Override
    public String createParamType(ParamTypeRequestDto dto) {

        List<ParamType> saveEntities;
        List<String> subjectCodes = dto.getSubjectCode();
        int duplicatedTypeCounter = 0;

        if (subjectCodes != null && subjectCodes.size() > 0) {

            saveEntities = new ArrayList<>();

            for (String subjectCode : subjectCodes) {

                // check the existence of a pair values (name - subject code)
                ParamType checkExistedEntity = paramTypeRepository.findByNameAndSubjectCode(dto.getName(), subjectCode);
                ParamType saveParamTypeEntity;

                if (checkExistedEntity == null) {

                    // if name - subject code does not exist in DB -> create new row in DB
                    saveParamTypeEntity = new ParamType();
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
                return "Param type is already existed.";
            }

            List<ParamType> paramTypeEntities = paramTypeRepository.saveAll(saveEntities);

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

        ParamType paramTypeEntity = paramTypeRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param type for id " + id));

        List<ActionParam> actionParams = paramTypeEntity.getActionParams();

        if (actionParams == null || actionParams.size() <= 0) {
            paramTypeEntity.setActive(false);

            if (paramTypeRepository.save(paramTypeEntity) != null) {
                return CustomConstant.DELETE_PARAM_TYPE_SUCCESS;
            } else
                return CustomConstant.DELETE_PARAM_TYPE_FAIL;
        } else
            return "Param type is already in use.";
    }
}
