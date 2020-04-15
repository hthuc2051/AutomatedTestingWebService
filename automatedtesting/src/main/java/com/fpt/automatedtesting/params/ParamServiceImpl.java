package com.fpt.automatedtesting.params;

import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.params.dtos.ParamCreateRequestDto;
import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import com.fpt.automatedtesting.params.dtos.ParamUpdateRequestDto;
import com.fpt.automatedtesting.paramtypes.ParamType;
import com.fpt.automatedtesting.paramtypes.ParamTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService {

    private final ParamRepository paramRepository;
    private final ParamTypeRepository paramTypeRepository;

    @Autowired
    public ParamServiceImpl(ParamRepository paramRepository, ParamTypeRepository paramTypeRepository) {
        this.paramRepository = paramRepository;
        this.paramTypeRepository = paramTypeRepository;
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

        ParamType paramTypeEntity = paramTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param type for id " + dto.getTypeId()));

        paramTypeEntity.setId(dto.getTypeId());
        Param saveEntity = new Param();
        saveEntity.setName(dto.getName());
        saveEntity.setActive(true);
        //saveEntity.setType(paramTypeEntity);

        if (paramRepository.save(saveEntity) != null)
            return "Create param successfully.";
        else
            return "Create param failed.";
    }

    @Override
    public String updateParam(ParamUpdateRequestDto dto) {

//        Param updateEntity = paramRepository.findById(dto.getId())
//                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param for id " + dto.getId()));
//
//        ParamType paramTypeEntity = paramTypeRepository.findById(dto.getTypeId())
//                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param type for id " + dto.getTypeId()));
//
//        updateEntity.setName(dto.getName());
//        updateEntity.setType(paramTypeEntity);
//
//        if (paramRepository.save(updateEntity) != null)
//            return "Update param successfully.";
//        else
            return "Update param failed.";
    }

    @Override
    public String deleteParam(Integer id) {

        Param deleteEntity = paramRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param for id " + id));

        deleteEntity.setActive(false);
        if (paramRepository.save(deleteEntity) != null)
            return "Delete param successfully.";
        else
            return "Delete param failed.";
    }


}
