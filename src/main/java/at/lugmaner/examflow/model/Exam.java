package at.lugmaner.examflow.model;

import java.time.LocalDate;

public class Exam implements Schedulable{
    private String name;
    private LocalDate deadLine;
    private Difficulty difficulty;
    private long totalStudyHours;

    public Exam(LocalDate deadLine, Difficulty difficulty, long estimatedStudyHours){
        name = "unnamed";
        this.deadLine = deadLine;
        this.difficulty = difficulty;
        this.totalStudyHours = estimatedStudyHours;
    }

    public Exam(String name, LocalDate deadLine, Difficulty difficulty, long estimatedStudyHours){
        this.name = name;
        this.deadLine = deadLine;
        this.difficulty = difficulty;
        this.totalStudyHours = estimatedStudyHours;
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
    public long getTotalStudyHours(){
        return totalStudyHours;
    }


}
