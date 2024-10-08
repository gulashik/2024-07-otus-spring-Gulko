package org.gulash.dao.implemetation;


import lombok.RequiredArgsConstructor;

import org.gulash.config.TestFileProperties;
import org.gulash.dao.QuestionDao;
import org.gulash.domain.Question;
import org.gulash.exceptions.QuestionReadException;
import org.gulash.mapper.LineMapper;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@RequiredArgsConstructor
@Repository
public class CsvQuestionDao implements QuestionDao {
    private final TestFileProperties testFileProperties;

    private final LineMapper lineToQuestionMapper;

    @Override
    public List<Question> findAll() {

        List<Question> questions = new ArrayList<>();

        try (InputStream iStream = getResourceAsStreamOrException(testFileProperties.name())) {
            Scanner scanner = new Scanner(iStream);

            skipFirstLines(scanner, testFileProperties.skipLines());

            while (scanner.hasNextLine()) {
                questions.add(
                        lineToQuestionMapper.toQuestion(
                                scanner.nextLine(),
                                testFileProperties.question().tag(),
                                testFileProperties.answer().tag(),
                                testFileProperties.answer().splitter()
                        )
                );
            }
        } catch (Exception e) {
            throw new QuestionReadException(e.getMessage());
        }
        return questions;
    }

    private void skipFirstLines(Scanner scanner, int lineCount) {
        for (int i = 0; i < lineCount; i++) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }

    private InputStream getResourceAsStreamOrException(String fileName) {
        return Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream(fileName),
                String.format("Have no file %s", fileName)
        );
    }
}
