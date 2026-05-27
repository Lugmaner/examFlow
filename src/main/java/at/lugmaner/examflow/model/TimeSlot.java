package at.lugmaner.examflow.model;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TimeSlot {
    LocalDate date();
    LocalTime startsAt();
    LocalTime endsAt();
}
