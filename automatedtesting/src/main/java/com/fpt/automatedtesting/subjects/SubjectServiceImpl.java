package com.fpt.automatedtesting.subjects;

import com.fpt.automatedtesting.classes.ClassResponseDto;
import com.fpt.automatedtesting.classes.Class;
import com.fpt.automatedtesting.scripts.Script;
import com.fpt.automatedtesting.scripts.dtos.ScriptResponseDto;
import com.fpt.automatedtesting.subjectclasses.SubjectClass;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.subjectclasses.SubjectClassRepository;
import com.fpt.automatedtesting.subjects.dtos.SubjectDetailsResponseDto;
import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;
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
    public List<SubjectDetailsResponseDto> getAll() {
        List<SubjectDetailsResponseDto> result = null;
        List<Subject> subjectEntities = subjectRepository.findAllByActiveIsTrue();
        if (subjectEntities != null && !subjectEntities.isEmpty()) {
            result = MapperManager.mapAll(subjectEntities, SubjectDetailsResponseDto.class);
            if (result != null && !result.isEmpty()) {
                for (int i = 0; i < result.size(); i++) {
                    List<ClassResponseDto> classResponseDtos = new ArrayList<>();
                    List<SubjectClass> subjectClassEntities = subjectEntities.get(i).getSubjectClasses();
                    if (subjectClassEntities != null && !subjectClassEntities.isEmpty()) {
                        for (SubjectClass entity : subjectClassEntities) {
                            Integer subjectClassId = entity.getId();
                            Class classEntity = entity.getAClass();
                            Integer classId = classEntity.getId();
                            String classCode = classEntity.getClassCode();
                            classResponseDtos.add(new ClassResponseDto(classId, classCode, subjectClassId));
                        }
                    }
                    result.get(i).setClasses(classResponseDtos);
                }
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any subject");
        }
        return result;
    }

    @Override
    public SubjectDetailsResponseDto getAllClassAndScriptsBySubjectId(Integer subjectId) {

        Subject subject = subjectRepository.findByIdAndActiveIsTrue(subjectId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject with id" + subjectId));

        List<SubjectClass> subjectClassEntities = subject.getSubjectClasses();

        SubjectDetailsResponseDto result = MapperManager.map(subject, SubjectDetailsResponseDto.class);
        if (result == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Occur error ! Please try later");
        }
        List<ClassResponseDto> listResponse;
        if (subjectClassEntities == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any class");
        }
        if (!subjectClassEntities.isEmpty()) {
            listResponse = new ArrayList<>();
            for (SubjectClass entity : subjectClassEntities) {
                Integer subjectClassId = entity.getId();
                Class classEntity = entity.getAClass();
                Integer classId = classEntity.getId();
                String classCode = classEntity.getClassCode();
                listResponse.add(new ClassResponseDto(classId, classCode, subjectClassId));
            }
            result.setClasses(listResponse);
        }
        List<Script> scriptEntities = subject.getScripts();
        if (scriptEntities != null && !scriptEntities.isEmpty()) {
            List<ScriptResponseDto> scriptResponseDtos = MapperManager.mapAll(scriptEntities, ScriptResponseDto.class);
            if (scriptResponseDtos != null && !scriptResponseDtos.isEmpty()) {
                result.setScripts(scriptResponseDtos);
            }
        }
        return result;
    }

    @Override
    public List<SubjectResponseDto> getAllSubjectForParamType() {

        List<Subject> subjectEntities = subjectRepository.findAllByActiveIsTrue();
        List<SubjectResponseDto> subjects = MapperManager.mapAll(subjectEntities, SubjectResponseDto.class);

        if (subjects != null || subjects.size() > 0)
            return subjects;
        else
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found any subject.");
    }
}
