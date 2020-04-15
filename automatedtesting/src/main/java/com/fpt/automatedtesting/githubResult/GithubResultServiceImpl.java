package com.fpt.automatedtesting.githubResult;

import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeRequest;
import com.fpt.automatedtesting.githubResult.dtos.GitHubFileDuplicateDTO;
import com.fpt.automatedtesting.practicalexams.PracticalExam;
import com.fpt.automatedtesting.practicalexams.PracticalExamRepository;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GithubResultServiceImpl implements GithubResultService {

    private final GithubResultRepository githubResultRepository;
    private final PracticalExamRepository practicalExamRepository;
    Gson gson = new Gson();
    @Autowired
    public GithubResultServiceImpl(GithubResultRepository githubResultRepository, PracticalExamRepository practicalExamRepository) {
        this.githubResultRepository = githubResultRepository;
        this.practicalExamRepository = practicalExamRepository;
    }


    @Override
    public boolean create(int practicalExamId, String studentCode, Map<String, List<GitHubFileDuplicateDTO>> listDuplicate) {
        String json = gson.toJson(listDuplicate);
        Optional<PracticalExam> practicalExam = practicalExamRepository.findById(practicalExamId);
        if(practicalExam.isPresent()){
            GithubResult githubResultAvailable = githubResultRepository.findByPracticalExamCodeAndStudentCode(practicalExam.get().getCode(),studentCode);
            GithubResult githubResult = new GithubResult();
            if(githubResultAvailable == null){
                githubResult.setPracticalExam(practicalExam.get());
                githubResult.setStudentCode(studentCode);
                githubResult.setResult(json);
            }else{
                githubResult = githubResultAvailable;
                githubResult.setResult(json);
            }
            if (githubResultRepository.save(githubResult) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, List<GitHubFileDuplicateDTO>> getListByPracticalCodeAndStudentCode(DuplicatedCodeRequest request) {
        Map<String, List<GitHubFileDuplicateDTO>> result = new HashMap<>();
        GithubResult githubResult = githubResultRepository.findByPracticalExamCodeAndStudentCode(request.getPracticalExamCode(),request.getStudentCode());
        String json = githubResult.getResult();
        if(!"".equals(json)){
          result = gson.fromJson(json, new TypeToken<Map<String, List<GitHubFileDuplicateDTO>>>(){}.getType());
        }
        return result;
    }
}
