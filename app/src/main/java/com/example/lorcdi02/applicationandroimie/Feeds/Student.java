package com.example.lorcdi02.applicationandroimie.Feeds;

import java.io.Serializable;

/**
 * Created by Latlanh on 01/08/2015.
 */
public class Student implements Serializable {

    private Integer studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentMail;
    private Integer studentAge;
    private Integer studentRetard;
    private String studentCommentary;
    private byte[] studentImage;
    private String studentTelephonNumber;
    private String nameFormer;
    private Promotion idPromotion;
    private Promotion idOldPromotion;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public Integer getStudentRetard() {
        return studentRetard;
    }

    public void setStudentRetard(Integer studentRetard) {
        this.studentRetard = studentRetard;
    }

    public String getStudentCommentary() {
        return studentCommentary;
    }

    public void setStudentCommentary(String studentCommentary) {
        this.studentCommentary = studentCommentary;
    }

    public byte[] getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(byte[] studentImage) {
        this.studentImage = studentImage;
    }

    public String getStudentTelephonNumber() {
        return studentTelephonNumber;
    }

    public void setStudentTelephonNumber(String studentTelephonNumber) {
        this.studentTelephonNumber = studentTelephonNumber;
    }

    public String getNameFormer() {
        return nameFormer;
    }

    public void setNameFormer(String nameFormer) {
        this.nameFormer = nameFormer;
    }

    public Promotion getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(Promotion idPromotion) {
        this.idPromotion = idPromotion;
    }

    public Promotion getIdOldPromotion() {
        return idOldPromotion;
    }

    public void setIdOldPromotion(Promotion idOldPromotion) {
        this.idOldPromotion = idOldPromotion;
    }
}
