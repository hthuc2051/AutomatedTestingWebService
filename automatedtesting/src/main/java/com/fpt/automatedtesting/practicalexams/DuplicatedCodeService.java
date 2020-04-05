package com.fpt.automatedtesting.practicalexams;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.Interval;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fpt.automatedtesting.common.CustomConstant.*;
import static com.fpt.automatedtesting.common.PathConstants.*;
import static org.snt.inmemantlr.utils.FileUtils.loadFileContent;


@Service
public class DuplicatedCodeService {

    private static GenericParser gp = null;

    public void getListTree(String file, String subjectCode, String fileCode, Map<String, List<Double>> vectors) {
        System.out.println("Checking file :");
        System.out.println(file);
        File[] files = null;
        File parserFile = null;
        File lexerFile = null;
        switch (subjectCode) {
            case CODE_PRACTICAL_JAVA:
            case CODE_PRACTICAL_JAVA_WEB:
                files = new File[2];
                parserFile = new File(PATH_GRAMMAR_JAVA_PARSER);
                lexerFile = new File(PATH_GRAMMAR_JAVA_LEXER);
                files[0] = parserFile;
                files[1] = lexerFile;
                break;
            case CODE_PRACTICAL_C:
                files = new File[1];
                parserFile = new File(PATH_GRAMMAR_C_PARSER);
                files[0] = parserFile;
                break;
            case CODE_PRACTICAL_CSharp:
                files = new File[2];
                parserFile = new File(PATH_GRAMMAR_CSHARP_PARSER);
                lexerFile = new File(PATH_GRAMMAR_CSHARP_LEXER);
                files[0] = parserFile;
                files[1] = lexerFile;
                break;
        }

        if (gp == null) {
            try {
                gp = new GenericParser(files);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Not found parser");
            }

            try {
                gp.compile();
            } catch (CompilationException e) {
                e.printStackTrace();
                System.out.println("Compile error");
            }
        }
        if (gp != null) {
            // Get file content
            String s = loadFileContent(file);

            ParserRuleContext ctx = null;
            try {
                ctx = gp.parse(s, GenericParser.CaseSensitiveType.NONE);
            } catch (IllegalWorkflowException | ParsingException e) {
                e.printStackTrace();
                System.out.println("Parsing | Illegal error");
            }
            List<ParseTree> trees = ctx.children;
            // studentCode_fileName_tokenLineStart_tokenLineStop
            Map<String, ParseTree> methodsTree = new HashMap<>();
            // Lấy danh sách biến + methods
            for (ParseTree tree : trees) {
                getMethodNode(tree, methodsTree, fileCode);
            }

            for (Map.Entry<String, ParseTree> entry : methodsTree.entrySet()) {
                List<Double> vector = new ArrayList<>();
                walkAllNode(entry.getValue(), vector);
                vectors.put(entry.getKey(), vector);
            }
        }
    }

    private Map<String, ParseTree> getMethodNode(ParseTree node, Map<String, ParseTree> result, String fileCode) {
        String className = node.getClass().getName();
        ParserRuleContext parserRuleContext = null;
        String fileToken = fileCode;
        try {
            parserRuleContext = (ParserRuleContext) node;
        } catch (Exception e) {
        }
        boolean isClassBody = true;
        if (parserRuleContext != null) {
            int startToken = parserRuleContext.getStart().getLine();
            int stopToken = parserRuleContext.getStop().getLine();
            if (Math.abs((startToken - stopToken)) <= 1) {
                isClassBody = false;
            }
            fileToken += "_" + startToken + "-" + stopToken;
        }
        if (className.contains("ClassBodyDeclarationContext") && isClassBody) {
            result.put(fileToken, node);
        }
        int count = node.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                getMethodNode(node.getChild(i), result, fileCode);
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
