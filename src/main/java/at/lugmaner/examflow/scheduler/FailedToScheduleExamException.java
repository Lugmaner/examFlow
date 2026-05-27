package at.lugmaner.examflow.scheduler;

import at.lugmaner.examflow.model.Exam;

public class FailedToScheduleExamException extends RuntimeException {
    public FailedToScheduleExamException(Exam e) {
        super(String.format("No free Date to schedule %s before deadline",e.getName()));
    }
}
