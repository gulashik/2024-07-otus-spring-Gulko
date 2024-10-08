package org.gulash.service.implemetation;

import lombok.RequiredArgsConstructor;
import org.gulash.service.ResultService;
import org.gulash.service.StudentService;
import org.gulash.service.TestRunnerService;
import org.gulash.service.TestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl
        implements TestRunnerService, CommandLineRunner {

    private final TestService testService;

    private final StudentService studentService;

    private final ResultService resultService;

    @Override
    public void run(String... args) {
        var student = studentService.determineCurrentStudent();
        var testResult = testService.executeTestFor(student);
        resultService.showResult(testResult);
    }
}
