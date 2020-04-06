package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.params.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
                for (int i = 0; i < paramTypes.size(); i++) {
                    List<Param> params = paramTypes.get(i).getParams();
                    List<String> paramNames = new ArrayList<>();
                    if (params != null && params.size() > 0) {
                        for (Param paramEntity : params) {
                            paramNames.add(paramEntity.getName());
                        }
                    }
                    paramTypeResponseDtos.get(i).setListParams(paramNames);
                }
                return paramTypeResponseDtos;
            } else {
                throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param type.");
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param type.");
        }
    }

    @Override
    public String insert(ParamTypeRequestDto dto) {
        ParamType entity = MapperManager.map(dto, ParamType.class);

        return null;
    }
}
