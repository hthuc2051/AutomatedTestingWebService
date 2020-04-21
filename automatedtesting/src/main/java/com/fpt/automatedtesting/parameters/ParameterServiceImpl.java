package com.fpt.automatedtesting.parameters;

import com.fpt.automatedtesting.actions.ActionParameter;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.parameters.dtos.ParameterCreateRequestDto;
import com.fpt.automatedtesting.parameters.dtos.ParameterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    private final ParameterRepository parameterRepository;

    @Autowired
    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<ParameterResponseDto> getAllParam() {

        List<Parameter> paramEntities = parameterRepository.getAllByActiveIsTrue();
        List<ParameterResponseDto> responseDto = MapperManager.mapAll(paramEntities, ParameterResponseDto.class);
        if (responseDto != null || responseDto.size() > 0)
            return responseDto;
        else
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any parameter.");
    }

    @Override
    public String createParam(ParameterCreateRequestDto dto) {

        if (dto.getName() == null || dto.getName().length() <= 0)
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any parameter name.");
        else {
            // find parameter by case-sensitive name
            Parameter saveParamEntity = parameterRepository.findParamByName(dto.getName());

            // if parameter found with given name
            if (saveParamEntity != null) {

                return "Parameter name \"" + dto.getName() + "\" is already existed.";
            } else {
                // not found any parameter with the given name -> create new parameter
                saveParamEntity = new Parameter();
                saveParamEntity.setName(dto.getName());
                saveParamEntity.setActive(true);
            }

            // save new parameter to Database
            if (parameterRepository.save(saveParamEntity) != null)
                return CustomConstant.CREATE_PARAM_SUCCESS;
            else
                return CustomConstant.CREATE_PARAM_FAIL;
        }
    }

    @Override
    public String deleteParam(Integer id) {

        Parameter deleteParamEntity = parameterRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found parameter for id " + id));

        // get relationship of parameter
        List<ActionParameter> actionParams = deleteParamEntity.getActionParameters();

        // if parameter is not in use -> set active status to false
        if (actionParams == null || actionParams.size() <= 0) {
            deleteParamEntity.setActive(false);
            if (parameterRepository.save(deleteParamEntity) != null)
                return CustomConstant.DELETE_PARAM_SUCCESS;
            else
                return CustomConstant.DELETE_PARAM_FAIL;
        } else { // parameter is already in use
            return "Parameter name \"" + deleteParamEntity.getName() + "\" is already in use.";
        }
    }
}
