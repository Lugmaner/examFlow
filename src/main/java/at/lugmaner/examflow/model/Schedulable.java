package at.lugmaner.examflow.model;

import java.time.LocalDate;

public interface Schedulable{
    String getName();
    LocalDate getDeadline();
    Difficulty getDifficultyLevel();
    int getEstimatedStudyHours();
}
