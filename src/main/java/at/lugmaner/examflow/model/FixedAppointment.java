package at.lugmaner.examflow.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class FixedAppointment implements TimeSlot{
    private final LocalDate date;
    private final LocalTime startsAt;
    private final LocalTime endsAt;
    public FixedAppointment(LocalDate date, LocalTime startsAt, LocalTime endsAt) {
        this.date = date;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public LocalDate date() {
        return date;
    }

    public LocalTime startsAt() {
        return startsAt;
    }

    public LocalTime endsAt() {
        return endsAt;
    }
}
