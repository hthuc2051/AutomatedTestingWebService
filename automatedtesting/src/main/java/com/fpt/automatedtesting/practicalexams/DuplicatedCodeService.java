package com.fpt.automatedtesting.practicalexams;

import com.fpt.automatedtesting.exception.CustomException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.snt.inmemantlr.GenericParser;
import org.snt.inmemantlr.exceptions.CompilationException;
import org.snt.inmemantlr.exceptions.IllegalWorkflowException;
import org.snt.inmemantlr.exceptions.ParsingException;
import org.snt.inmemantlr.listener.DefaultTreeListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.fpt.automatedtesting.common.CustomConstant.*;
import static com.fpt.automatedtesting.common.PathConstants.*;
import static org.snt.inmemantlr.utils.FileUtils.loadFileContent;

@Service
public class DuplicatedCodeService {

    public List<List<Double>> getListTree(String file, int subjectCode) {
        File[] files = null;
        File parserFile = null;
        File lexerFile = null;
        switch (subjectCode) {
            case JAVA:
            case JAVA_WEB:
                files = new File[2];
                parserFile = new File(PATH_GRAMMAR_JAVA_PARSER);
                lexerFile = new File(PATH_GRAMMAR_JAVA_LEXER);
                files[0] = parserFile;
                files[1] = lexerFile;
                break;
            case C:
                files = new File[1];
                parserFile = new File(PATH_GRAMMAR_C_PARSER);
                files[0] = parserFile;
                break;
            case CSHARP:
                files = new File[2];
                parserFile = new File(PATH_GRAMMAR_CSHARP_PARSER);
                lexerFile = new File(PATH_GRAMMAR_CSHARP_LEXER);
                files[0] = parserFile;
                files[1] = lexerFile;
                break;
        }

        GenericParser gp = null;
        try {
            gp = new GenericParser(files);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Not found parser");
        }
        // Get file content
        String s = loadFileContent(file);
        DefaultTreeListener dlist = new DefaultTreeListener();
        gp.setListener(dlist);
        try {
            gp.compile();
        } catch (CompilationException e) {
            e.printStackTrace();
            System.out.println("Compile error");
        }
        ParserRuleContext ctx = null;
        try {
            ctx = gp.parse(s, GenericParser.CaseSensitiveType.NONE);
        } catch (IllegalWorkflowException | ParsingException e) {
            e.printStackTrace();
            System.out.println("Parsing | Illegal error");
        }
        List<ParseTree> trees = ctx.children;
        List<ParseTree> methodsTree = new ArrayList<>();
        for (ParseTree tree :
                trees) {
            getMethodNode(tree, methodsTree);
        }
        List<List<Double>> vectors = new ArrayList<>();
        for (ParseTree tree : methodsTree) {
            List<Double> vector = new ArrayList<>();
            walkAllNode(tree, vector);
            vectors.add(vector);
        }
        return vectors;
    }

    private List<ParseTree> getMethodNode(ParseTree node, List<ParseTree> result) {
        String className = node.getClass().getName();
        if (className.contains("ClassBodyDeclarationContext")) {
            result.add(node);
        }
        int count = node.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                getMethodNode(node.getChild(i), result);
            }
        }
        return result;
    }

    private void walkAllNode(ParseTree tree, List<Double> result) {
        String text = "";
        if (tree.getChildCount() == 0) {
            text = tree.getClass().getName() + "  -   " + processData(tree);
        } else {
            text = tree.getClass().getName();
        }
        result.add(Double.parseDouble(String.valueOf(text.hashCode())));
        int count = tree.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                walkAllNode(tree.getChild(i), result);
            }
        }
    }

    private String processData(ParseTree node) {
        String result = node.toString();
        switch (result) {
            case "do":
            case "while":
            case "if":
            case "for":
            case "switch":
            case "case":
            case "try":
            case "continue":
            case "return":
            case "throw":
                result = node.toString();
                break;
            default:
                result = node.toString();
        }
        return result;
    }
}
