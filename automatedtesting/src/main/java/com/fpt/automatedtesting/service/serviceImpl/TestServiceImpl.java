package com.fpt.automatedtesting.service.serviceImpl;


import com.fpt.automatedtesting.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String dummyString() {
        return "String";
    }
}
