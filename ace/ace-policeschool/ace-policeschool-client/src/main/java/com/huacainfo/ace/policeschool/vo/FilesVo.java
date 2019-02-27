package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.Files;
import com.huacainfo.ace.policeschool.model.Student;
import com.huacainfo.ace.policeschool.model.Teacher;


public class FilesVo extends Files {
    private static final long serialVersionUID = 1L;
    private Student student;

    private Teacher teacher;

    /**
     * 发布者姓名
     */
    private String publisherName;

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
