package com.fpt.practical.java;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class TestResultLoggerExtension implements TestWatcher, AfterAllCallback {

    private Map<String, Integer> testResultsStatus = new HashMap<>();


    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("Test Disabled for test: with reason :- " +
                context.getDisplayName() +
                reason.orElse("No reason"));
        testResultsStatus.put(context.getDisplayName(), 0);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Test Successful for test: " + context.getDisplayName());
        testResultsStatus.put(context.getDisplayName(), 1);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("Test Aborted for test: " + context.getDisplayName());
        testResultsStatus.put(context.getDisplayName(), 0);

    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Test Aborted for test: " + context.getDisplayName());
        testResultsStatus.put(context.getDisplayName(), 0);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        File file = new File("mark-result.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file,true));
        double mark = 0;
        String resultText = "";
        for (Map.Entry<String, Integer> entry : testResultsStatus.entrySet()) {
            if (entry.getValue() == 1) {
                writer.println(resultText + entry.getKey() + ": Pass");
                mark += 2.5;
            } else {
                writer.println(resultText + entry.getKey() + ": Failed");
            }
        }
        writer.println("Total mark is :" + mark);
        writer.close();
    }


}