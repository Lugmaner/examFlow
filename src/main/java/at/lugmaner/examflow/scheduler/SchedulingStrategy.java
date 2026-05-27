package at.lugmaner.examflow.scheduler;

import at.lugmaner.examflow.model.Exam;
import at.lugmaner.examflow.model.StudyBlock;
import at.lugmaner.examflow.model.TimeSlot;

import java.util.*;

public interface SchedulingStrategy {
    List<StudyBlock> getStudyBlocks(Collection<Exam> exams, Collection<TimeSlot> timeSlots);
}
