package at.lugmaner.examflow.model;

import java.time.LocalDate;
import java.time.LocalTime ;

public record StudyBlock(LocalDate date, LocalTime startsAt, LocalTime endsAt,
                         Exam exam) implements TimeSlot {
}
