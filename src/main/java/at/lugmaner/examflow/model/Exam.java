package at.lugmaner.examflow.model;

import java.time.LocalDate;

public class Exam implements Schedulable{
    private String name;
    private LocalDate deadLine;
    private Difficulty difficulty;
    private int estimatedStudyHours;

    public Exam(LocalDate deadLine, Difficulty difficulty, int estimatedStudyHours){
        name = "unnamed";
        this.deadLine = deadLine;
        this.difficulty = difficulty;
        this.estimatedStudyHours = estimatedStudyHours;
    }

    public Exam(String name, LocalDate deadLine, Difficulty difficulty, int estimatedStudyHours){
        this.name = name;
        this.deadLine = deadLine;
        this.difficulty = difficulty;
        this.estimatedStudyHours = estimatedStudyHours;
    }

    public String getName() {
        return name;
    }
    public LocalDate getDeadline() {
        return deadLine;
    }
    public Difficulty getDifficultyLevel(){
        return difficulty;
    }
    public int getEstimatedStudyHours(){
        return estimatedStudyHours;
    }
}
