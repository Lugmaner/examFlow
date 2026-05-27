package at.lugmaner.examflow.scheduler;

import at.lugmaner.examflow.model.Exam;
import at.lugmaner.examflow.model.StudyBlock;
import at.lugmaner.examflow.model.TimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class GreedyScheduler implements SchedulingStrategy {
    private final LocalTime dayStartsAt;
    private final LocalTime dayEndsAt;
    private final long sessionDurationHours;
    private final long breakTime;

    public GreedyScheduler(LocalTime dayStartsAt, LocalTime dayEndsAt, long breakTime, long sessionDurationHours) {
        this.dayStartsAt = dayStartsAt;
        this.dayEndsAt = dayEndsAt;
        this.breakTime = breakTime;
        this.sessionDurationHours = sessionDurationHours;
    }

    private void insertBlock(List<StudyBlock> studyBlocks,StudyBlock block, Map<LocalDate, List<TimeSlot>> occupiedSlots){
        studyBlocks.add(block);
        occupiedSlots.computeIfAbsent(block.date(), k -> new ArrayList<>()).add(block);
    }

    private List<LocalTime> getFreeTimeSlot(long duration, List<TimeSlot> occupiedSlots){
        occupiedSlots.sort(Comparator.comparing(TimeSlot::startsAt));
        List<LocalTime> freeSlots = new ArrayList<>();
        LocalTime current = dayStartsAt;

        while (!current.plusHours(duration).isAfter(dayEndsAt)){
            LocalTime potentialEnd = current.plusHours(sessionDurationHours + breakTime);

            boolean overlaps = false;

            for(TimeSlot slot : occupiedSlots){
                if(current.isBefore(slot.endsAt()) && potentialEnd.isAfter(slot.startsAt())){
                    overlaps = true;
                    break;
                }
            }
            if(!overlaps){
                freeSlots.add(current);
            }
            current = current.plusHours(1);
        }
        return freeSlots;
    }

    public List<StudyBlock> getStudyBlocks(List<Exam> exams, Map<LocalDate, List<TimeSlot>> occupiedSlots) throws FailedToScheduleExamException {
        List<StudyBlock> studyBlocks = new ArrayList<>();
        exams.sort(Comparator.comparing(Exam::getDeadline));

        LocalDate start = LocalDate.now();
        for(Exam e : exams){
            long sessionCount = 0;
            for (int i = 0; start.plusDays(i).isBefore(e.getDeadline()); i++) {
                if(sessionCount >= Math.ceil(e.getTotalStudyHours() / (double)sessionDurationHours)){break;}
                LocalDate day = start.plusDays(i);

                if(!occupiedSlots.containsKey(day)){
                    StudyBlock toInsert = new StudyBlock(day,dayStartsAt,
                            dayStartsAt.plusHours(sessionDurationHours),e);
                    insertBlock(studyBlocks, toInsert, occupiedSlots);
                    sessionCount++;
                }else{
                    List<LocalTime> freeTimeSlots = getFreeTimeSlot(sessionDurationHours,occupiedSlots.get(day));
                    if(!freeTimeSlots.isEmpty()){
                        StudyBlock toInsert = new StudyBlock(day,freeTimeSlots.getFirst(),
                                freeTimeSlots.getFirst().plusHours(sessionDurationHours),e);
                        insertBlock(studyBlocks, toInsert, occupiedSlots);
                        sessionCount++;
                    }
                }
            }
            if(sessionCount < Math.ceil(e.getTotalStudyHours() / (double)sessionDurationHours)){
                throw new FailedToScheduleExamException(e);
            }
        }
        return studyBlocks;
    }
}
