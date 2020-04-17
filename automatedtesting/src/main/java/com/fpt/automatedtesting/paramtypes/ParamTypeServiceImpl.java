package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.actions.SubjectActionParam;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.params.Param;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeDetailsResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeRequestDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeUpdateRequestDto;
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
    public List<ParamTypeDetailsResponseDto> getAllParamType() {

        List<ParamType> paramTypes = paramTypeRepository.findAllByActiveIsTrue();

        if (paramTypes != null && paramTypes.size() > 0) {
            List<ParamTypeDetailsResponseDto> responseDtos = MapperManager.mapAll(paramTypes, ParamTypeDetailsResponseDto.class);

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
                    duplicatedTypeCounter++;
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

    @Transactional
    @Override
    public String updateParamType(ParamTypeUpdateRequestDto dto) {

        if (dto.getName() != null && dto.getName().length() > 0) {

            // get param type by id
            ParamType updateParamType = paramTypeRepository.findById(dto.getId())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param type for id " + dto.getId()));

            if (dto.getName().equals(updateParamType.getName())) {
                return CustomConstant.UPDATE_PARAM_TYPE_SUCCESS;
            }

            //check if name - subject from dto existed
            if (paramTypeRepository.findByNameAndSubjectCode(dto.getName(), updateParamType.getSubjectCode()) != null) {

                return "Param type is already existed.";
            } else { // if name - subject not existed

                // update the old param type's name
                updateParamType.setName(dto.getName());
                if (paramTypeRepository.save(updateParamType) != null) {
                    return CustomConstant.UPDATE_PARAM_TYPE_SUCCESS;
                } else {
                    return CustomConstant.UPDATE_PARAM_TYPE_FAIL;
                }
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found param type.");
        }
    }

    @Override
    public String deleteParamType(Integer id) {

        ParamType paramTypeEntity = paramTypeRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param type for id " + id));

        List<SubjectActionParam> subjectActionParams = paramTypeEntity.getSubjectActionParams();

        if (subjectActionParams == null || subjectActionParams.size() <= 0) {
            paramTypeEntity.setActive(false);

            if (paramTypeRepository.save(paramTypeEntity) != null) {
                return CustomConstant.DELETE_PARAM_TYPE_SUCCESS;
            } else
                return CustomConstant.DELETE_PARAM_TYPE_FAIL;
        } else
            return "Param type is already in use.";
    }


}
