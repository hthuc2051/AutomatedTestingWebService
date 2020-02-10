package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.dto.request.ActionRequestDto;
import com.fpt.automatedtesting.dto.response.ActionResponseDto;
import com.fpt.automatedtesting.entity.Action;
import com.fpt.automatedtesting.entity.User;
import com.fpt.automatedtesting.entity.Param;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.ActionRepository;
import com.fpt.automatedtesting.repository.AdminRepository;
import com.fpt.automatedtesting.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {


    private final ActionRepository actionRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository, AdminRepository adminRepository) {
        this.actionRepository = actionRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public List<ActionResponseDto> getAll() {
        List<ActionResponseDto> listResponse = MapperManager.mapAll(actionRepository.findAll(), ActionResponseDto.class);
        return listResponse;
    }

    @Override
    public ActionResponseDto insert(ActionRequestDto dto) {
        User user = adminRepository
                .findById(dto.getAdminId())
                .orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"Admin"));
        Action action = MapperManager.map(dto, Action.class);
        action.setUser(user);
        List<Param> params = MapperManager.mapAll(dto.getParams(), Param.class);
        params.forEach(param -> param.setAction(action));
        action.setParams(params);
        return MapperManager.map(actionRepository.save(action), ActionResponseDto.class);
    }

    @Override
    public ActionResponseDto update(ActionRequestDto dto) {
        if (findById(dto.getId()) != null) {
            Action response = MapperManager.map(dto, Action.class);
            List<Param> requestParam = MapperManager.mapAll(dto.getParams(), Param.class);
            requestParam.forEach(param -> param.setAction(response));
            response.setParams(requestParam);
            return MapperManager.map(actionRepository.save(response), ActionResponseDto.class);
        }
        return null;
    }

    @Override
    public ActionResponseDto findById(int id) {
        ActionResponseDto response = MapperManager.map(actionRepository.findById(id), ActionResponseDto.class);
        return response;
    }

    @Override
    public boolean delete(int id) {
        // Delete set active
        return true;
    }
}
