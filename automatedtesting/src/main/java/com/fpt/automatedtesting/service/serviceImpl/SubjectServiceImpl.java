package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.dto.response.ClassResponseDto;
import com.fpt.automatedtesting.dto.response.SubjectResponseDto;
import com.fpt.automatedtesting.entity.Class;
import com.fpt.automatedtesting.entity.Subject;
import com.fpt.automatedtesting.entity.SubjectClass;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.SubjectClassRepository;
import com.fpt.automatedtesting.repository.SubjectRepository;
import com.fpt.automatedtesting.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectClassRepository subjectClassRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectClassRepository subjectClassRepository) {
        this.subjectRepository = subjectRepository;
        this.subjectClassRepository = subjectClassRepository;
    }

    @Override
    public List<SubjectResponseDto> getAll() {
        List<Subject> listSubject = subjectRepository.findAllByActiveIsTrue();
        if (listSubject != null && !listSubject.isEmpty()) {
            return MapperManager.mapAll(listSubject, SubjectResponseDto.class);
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any subject");
        }
    }

    @Override
    public List<ClassResponseDto> getAllClassBySubjectIncludeSubjectClassId(Integer subjectId) {
        List<SubjectClass> listSubjectClass = subjectClassRepository.findAllBySubjectId(subjectId);
        List<ClassResponseDto> listResponse;

        if (listSubjectClass != null && !listSubjectClass.isEmpty()) {
            listResponse = new ArrayList<>();
            for (SubjectClass entity : listSubjectClass) {
                Integer subjectClassId = entity.getId();
                Class classEntity = entity.getAClass();
                Integer classId = classEntity.getId();
                String classCode = classEntity.getClassCode();
                listResponse.add(new ClassResponseDto(classId, classCode, subjectClassId));
            }
            return listResponse;
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any class");
        }
    }
}
