package at.lugmaner.examflow.model;

import java.time.LocalDate;
import java.time.LocalTime ;

public class StudyBlock {
    private final String blockID;
    private final LocalDate startingDay;
    private final LocalTime startsAt;
    private final LocalTime endsAt;
    private final Exam exam;

    public StudyBlock(String blockID, LocalDate startingDay, LocalTime startsAt, LocalTime endsAt, Exam exam) {
        this.blockID = blockID;
        this.startingDay = startingDay;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.exam = exam;
    }

    public String getBlockID() {
        return blockID;
    }

    public LocalDate getStartingDay() {
        return startingDay;
    }

    public LocalTime getStartsAt() {
        return startsAt;
    }

    public LocalTime getEndsAt() {
        return endsAt;
    }

    public Exam getExam() {
        return exam;
    }
}
