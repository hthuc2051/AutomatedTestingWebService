package com.fpt.automatedtesting.subjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.automatedtesting.practicalexams.dtos.PracticalInfo;
import com.fpt.automatedtesting.subjects.dtos.SubjectDetailsResponseDto;
import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.errors.UnmergedPathException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.fpt.automatedtesting.common.CustomConstant.PRACTICAL_INFO_FILE_NAME;

@RestController
@CrossOrigin(origins = "http://localhost:1998")
@RequestMapping("/api")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/test")
    public String test() {

        String s = "Java_439576449_DE02";
        System.out.println(s.substring(0,s.lastIndexOf("_")));
        try {
            String name = "headlecturer2020";
            String password = "Capstone12345678";
            String url = "https://github.com/headlecturer2020/SE1267_Practical.git";

            // credentials
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(name, password);
//            // clone
            File dir = new File("project");
//            CloneCommand cc = new CloneCommand()
//                    .setCredentialsProvider(cp)
//                    .setDirectory(dir)
//                    .setURI(url);

            Git git = Git.open(dir);
            CheckoutCommand checkoutServer = git.checkout();
            checkoutServer.setName("master");
            checkoutServer.call();
            CreateBranchCommand branchCommand = git.branchCreate();
            branchCommand.setName("student/PracticalExam_JavaWeb_IA1242/SE1234");
            branchCommand.call();
            CheckoutCommand checkout = git.checkout();
            checkout.setName("student/PracticalExam_JavaWeb_IA1242/SE1234");
            checkout.call();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(
                    new FileOutputStream("project/123414.json"),
                    "{\"studentCode\":\"SE140331\",\"listQuestions\":null,\"totalPoint\":null,\"submitTime\":null,\"evaluateTime\":null,\"codingConvention\":null,\"result\":null,\"errorMsg\":null}");
            AddCommand ac = git.add();
            ac.addFilepattern(".");
            try {
                ac.call();
            } catch (NoFilepatternException e) {
                e.printStackTrace();
            }

            // commit
            CommitCommand commit = git.commit();
            commit.setCommitter("TMall", "open@tmall.com")
                    .setMessage("push war");
            try {
                commit.call();
            } catch (NoHeadException e) {
                e.printStackTrace();
            } catch (NoMessageException e) {
                e.printStackTrace();
            } catch (ConcurrentRefUpdateException e) {
                e.printStackTrace();
            } catch (WrongRepositoryStateException e) {
                e.printStackTrace();
            }
            // push
            PushCommand pc = git.push();
            pc.setCredentialsProvider(cp)
                    .setForce(true)
                    .setPushAll();
            try {
                Iterator<PushResult> it = pc.call().iterator();
                if(it.hasNext()){
                    System.out.println(it.next().toString());
                }
            } catch (InvalidRemoteException e) {
                e.printStackTrace();
            }

            // cleanup
            dir.deleteOnExit();
        } catch (InvalidRemoteException e) {
            e.printStackTrace();
        } catch (TransportException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDetailsResponseDto>> getAllSubject() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAll());
    }

    @GetMapping("/subjects/{id}/classes-scripts")
    public ResponseEntity<SubjectDetailsResponseDto> getAllClassAndScriptsBySubjectId(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAllClassAndScriptsBySubjectId(id));
    }

    @GetMapping("/subjects/all")
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjectForParamType() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAllSubjectForParamType());
    }

}
