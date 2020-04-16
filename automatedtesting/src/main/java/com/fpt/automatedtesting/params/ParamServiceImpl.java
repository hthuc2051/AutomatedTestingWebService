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

        boolean checkExistedParam = false;

        if (dto.getName() == null || dto.getName().length() <= 0)
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any param name.");
        else {
            // find param by case-sensitive name
            Param saveParamEntity = paramRepository.findParamByName(dto.getName());

            // if param found with given name
            if (saveParamEntity != null) {

                // check if active status is false -> set to true
                if (!saveParamEntity.getActive()) {
                    saveParamEntity.setActive(true);
                } // else do nothing

            } else {
                // not found any param with the given name -> create new param
                saveParamEntity = new Param();
                saveParamEntity.setName(dto.getName());
                saveParamEntity.setActive(true);
            }

            // save new param to Database
            if (paramRepository.save(saveParamEntity) != null)
                return "Create param successfully.";
            else
                return "Create param failed.";
        }
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

        Param deleteParamEntity = paramRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found param for id " + id));

        deleteParamEntity.setActive(false);
        if (paramRepository.save(deleteParamEntity) != null)
            return "Delete param successfully.";
        else
            return "Delete param failed.";
    }


}
