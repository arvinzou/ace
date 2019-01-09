package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.*;


public class EvaluationRstVo extends EvaluationRst {
private static final long serialVersionUID = 1L;
private Course course;
private ClassSchedule classSchedule;

private EvaluationRstContent evaluationRstContent;
private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }

    public EvaluationRstContent getEvaluationRstContent() {
        return evaluationRstContent;
    }

    public void setEvaluationRstContent(EvaluationRstContent evaluationRstContent) {
        this.evaluationRstContent = evaluationRstContent;
    }
}
