package com.fpt.automatedtesting.params;

import com.fpt.automatedtesting.actions.SubjectActionParam;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.params.dtos.ParamCreateRequestDto;
import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import com.fpt.automatedtesting.params.dtos.ParamUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService {

    private final ParamRepository paramRepository;

    @Autowired
    public ParamServiceImpl(ParamRepository paramRepository) {
        this.paramRepository = paramRepository;
    }

    @Override
    public List<ParamResponseDto> getAllParam() {

        List<Param> paramEntities = paramRepository.getAllByActiveIsTrue();
        List<ParamResponseDto> responseDto = MapperManager.mapAll(paramEntities, ParamResponseDto.class);
        if (responseDto != null || responseDto.size() > 0)
            return responseDto;
        else
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param.");
    }

    @Override
    public String createParam(ParamCreateRequestDto dto) {

        if (dto.getName() == null || dto.getName().length() <= 0)
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param name.");
        else {
            // find param by case-sensitive name
            Param saveParamEntity = paramRepository.findParamByName(dto.getName());

            // if param found with given name
            if (saveParamEntity != null) {

                return "Param name \"" + dto.getName() + "\" is already existed.";
            } else {
                // not found any param with the given name -> create new param
                saveParamEntity = new Param();
                saveParamEntity.setName(dto.getName());
                saveParamEntity.setActive(true);
            }

            // save new param to Database
            if (paramRepository.save(saveParamEntity) != null)
                return CustomConstant.CREATE_PARAM_SUCCESS;
            else
                return CustomConstant.CREATE_PARAM_FAIL;
        }
    }

    @Override
    public String updateParam(ParamUpdateRequestDto dto) {

        Param updateEntity = paramRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param for id " + dto.getId()));

        if (dto.getName().equals(updateEntity.getName()))
            return CustomConstant.UPDATE_PARAM_SUCCESS;

        // find param by case-sensitive name
        Param paramEntity = paramRepository.findParamByName(dto.getName());

        // if found param with the given name
        if (paramEntity != null) {

            // if found param is not update param
            if (paramEntity.getId() != updateEntity.getId()) {

                return "Param name \"" + dto.getName() + "\" is already existed.";
            } // else do nothing
        } else {
            updateEntity.setName(dto.getName());
        }

        if (paramRepository.save(updateEntity) != null)
            return CustomConstant.UPDATE_PARAM_SUCCESS;
        else
            return CustomConstant.UPDATE_PARAM_FAIL;
    }

    @Override
    public String deleteParam(Integer id) {

        Param deleteParamEntity = paramRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param for id " + id));

        // get relationship of param
        List<SubjectActionParam> subjectActionParams = deleteParamEntity.getSubjectActionParams();

        // if param is not in use -> set active status to false
        if (subjectActionParams == null || subjectActionParams.size() <= 0) {
            deleteParamEntity.setActive(false);
            if (paramRepository.save(deleteParamEntity) != null)
                return CustomConstant.DELETE_PARAM_SUCCESS;
            else
                return CustomConstant.DELETE_PARAM_FAIL;
        } else { // param is already in use
            return "Param name \"" + deleteParamEntity.getName() + "\" is already in use.";
        }
    }


}
