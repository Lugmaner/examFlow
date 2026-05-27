package at.lugmaner.examflow.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class FixedAppointment implements TimeSlot{
    private final String blockID;
    private final LocalDate date;
    private final LocalTime startsAt;
    private final LocalTime endsAt;

    public FixedAppointment(String blockID, LocalDate date, LocalTime startsAt, LocalTime endsAt) {
        this.blockID = blockID;
        this.date = date;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public String blockID() {
        return blockID;
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
