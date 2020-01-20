package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.dto.request.ActionRequestDto;
import com.fpt.automatedtesting.dto.response.ActionResponseDto;
import com.fpt.automatedtesting.entity.Action;
import com.fpt.automatedtesting.entity.Admin;
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
        List<Action> actionList = actionRepository.findAll();
        if (actionList == null && actionList.size() > 0) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Actions not found");
        }
        return MapperManager.mapAll(actionList, ActionResponseDto.class);
    }

    @Override
    public ActionResponseDto insert(ActionRequestDto dto) {
        Admin admin = adminRepository
                .findById(dto.getAdminId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Admin"));
        Action action = MapperManager.map(dto, Action.class);
        action.setAdmin(admin);
        List<Param> params = MapperManager.mapAll(dto.getParams(), Param.class);
        params.forEach(param -> param.setAction(action));
        action.setParams(params);
        return MapperManager.map(actionRepository.save(action), ActionResponseDto.class);
    }

    @Override
    public ActionResponseDto update(ActionRequestDto dto) {
        Action action = actionRepository
                .findById(dto.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Action not found"));
        MapperManager.map(dto, Action.class);
        List<Param> params = MapperManager.mapAll(dto.getParams(), Param.class);
        params.forEach(param -> param.setAction(action));
        action.setParams(params);
        return MapperManager.map(actionRepository.save(action), ActionResponseDto.class);
    }

    @Override
    public ActionResponseDto findById(int id) {
        Action action = actionRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Action not found"));
        ActionResponseDto response = MapperManager.map(action, ActionResponseDto.class);
        return response;
    }

    @Override
    public boolean delete(int id) {
        // Delete set active
        return true;
    }
}
