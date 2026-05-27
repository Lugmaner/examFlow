package at.lugmaner.examflow.scheduler;

import at.lugmaner.examflow.model.Exam;
import at.lugmaner.examflow.model.StudyBlock;
import at.lugmaner.examflow.model.TimeSlot;

import java.time.LocalDate;
import java.util.*;

public interface SchedulingStrategy {
    List<StudyBlock> getStudyBlocks(List<Exam> exams, Map<LocalDate, List<TimeSlot>> timeSlots) throws FailedToScheduleExamException;
}