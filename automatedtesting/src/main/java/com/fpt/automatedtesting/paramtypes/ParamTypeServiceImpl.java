package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.params.Param;
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
            List<ParamTypeResponseDto> paramTypeResponseDtos = MapperManager.mapAll(paramTypes, ParamTypeResponseDto.class);

            if (paramTypeResponseDtos != null && paramTypeResponseDtos.size() > 0) {
                for (int index = 0; index < paramTypes.size(); index++) {

                    List<Param> params = paramTypes.get(index).getParams();
                    List<String> paramNames = new ArrayList<>();

                    if (params != null && params.size() > 0) {
                        for (Param paramEntity : params) {
                            paramNames.add(paramEntity.getName());
                        }
                    }
                    paramTypeResponseDtos.get(index).setListParams(paramNames);
                }
                return paramTypeResponseDtos;
            } else {
                throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param type.");
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param type.");
        }
    }

    @Transactional
    @Override
    public String insertParamType(ParamTypeRequestDto dto) {

        List<ParamType> saveEntities;
        List<String> subjectCodes = dto.getSubjectCode();

        if (subjectCodes != null && subjectCodes.size() > 0) {

            saveEntities = new ArrayList<>();

            for (String subjectCode : subjectCodes) {

                // check the existence of a pair values (name - subject code)
                ParamType checkExistedEntity = paramTypeRepository.findByNameAndSubjectCode(dto.getName(), subjectCode);

                if (checkExistedEntity != null) {

                    ParamType saveParamTypeEntity = new ParamType();
                    saveParamTypeEntity.setName(dto.getName());
                    saveParamTypeEntity.setSubjectCode(subjectCode);
                    saveParamTypeEntity.setActive(true);

                    // if active status is false -> set it back to true
                    if (!checkExistedEntity.getActive()) {

                        saveParamTypeEntity.setId(checkExistedEntity.getId());

                        saveEntities.add(saveParamTypeEntity);
                    }// else active status is true -> do nothing
                } else {

                    // if name - subject code does not exist in DB -> create new row in DB
                    ParamType saveParamTypeEntity = new ParamType();
                    saveParamTypeEntity.setName(dto.getName());
                    saveParamTypeEntity.setSubjectCode(subjectCode);
                    saveParamTypeEntity.setActive(true);

                    saveEntities.add(saveParamTypeEntity);
                }
            }

            List<ParamType> paramTypeEntities = paramTypeRepository.saveAll(saveEntities);

            if (paramTypeEntities == null || paramTypeEntities.size() < 1) {
                return "Create param type failed.";
            } else {
                return "Create param type successfully.";
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any subject.");
        }
    }

    @Transactional
    @Override
    public String updateParamType(ParamTypeUpdateRequestDto dto) {

        List<String> subjectCodes = dto.getSubjectCodes();
        List<ParamType> updateEntities;

        if (subjectCodes != null && subjectCodes.size() > 0) {

            updateEntities = new ArrayList<>();
            // get param type by id to retrieve the old data
            ParamType paramType = paramTypeRepository.findById(dto.getId())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param type for id " + dto.getId()));

            // check if list subjects contains the subject of old param type
            if (subjectCodes.contains(paramType.getSubjectCode())) {

                //check if name - subject existed
                ParamType checkExistedEntity = paramTypeRepository.findByNameAndSubjectCode(dto.getName(), paramType.getSubjectCode());

                // if name - subject not existed
                if (checkExistedEntity == null) {

                    // update the old param type's name
                    paramType.setName(dto.getName());
                    updateEntities.add(paramType);

                } else { // if name - subject existed

                    // check if existed data's active status is false
                    if (!checkExistedEntity.getActive()) {

                        // set active status of old param type above to false
                        paramType.setActive(false);
                        updateEntities.add(paramType);

                        // set existed data's active status to true
                        checkExistedEntity.setActive(true);
                        updateEntities.add(checkExistedEntity);
                    }
                }

                // remove the subject in the list subjects if there are more than 1 subject in the list subjects
                if (subjectCodes.size() > 1) {
                    subjectCodes.remove(paramType.getSubjectCode());
                }
            } else { // if list subjects does not contains the subject of old param type -> set active status to false
                paramType.setActive(false);
                updateEntities.add(paramType);
            }

            for (String subjectCode : subjectCodes) {

                // check the existence of a pair values (name - subject)
                ParamType checkExistedEntity = paramTypeRepository.findByNameAndSubjectCode(dto.getName(), subjectCode);

                // if pair values existed
                if (checkExistedEntity != null) {

                    // check if the active status of pair values (name - subject) is false -> set back to true
                    if (!checkExistedEntity.getActive()) {
                        checkExistedEntity.setActive(true);
                        updateEntities.add(checkExistedEntity);
                    }
                } else { // if pair values not existed -> create new param type
                    ParamType paramTypeEntity = new ParamType();
                    paramTypeEntity.setName(dto.getName());
                    paramTypeEntity.setSubjectCode(subjectCode);
                    paramTypeEntity.setActive(true);

                    updateEntities.add(paramTypeEntity);
                }
            }

            if (updateEntities.size() < 1) {
                return "Update param type successfully.";
            }

            List<ParamType> paramTypeEntities = paramTypeRepository.saveAll(updateEntities);

            if (paramTypeEntities == null || paramTypeEntities.size() < 1) {
                return "Update param type failed.";
            } else {
                return "Update param type successfully.";
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any subject.");
        }
    }

    @Override
    public String deleteParamType(Integer id) {
        ParamType paramTypeEntity = paramTypeRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param type for id " + id));
        paramTypeEntity.setActive(false);
        if (paramTypeRepository.save(paramTypeEntity) != null) {
            return "Delete param type successfully.";
        } else
            return "Delete param type failed.";
    }


}
