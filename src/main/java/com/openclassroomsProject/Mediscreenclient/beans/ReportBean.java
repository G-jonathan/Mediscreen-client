package com.openclassroomsProject.Mediscreenclient.beans;

/**
 * Bean class representing a Report.
 * It contains information about a patient's family name, given name, age, and assessment.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
public class ReportBean {

    private String family;
    private String given;
    private int age;
    private String assessment;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "ReportBean{" +
                "family='" + family + '\'' +
                ", given='" + given + '\'' +
                ", age=" + age +
                ", assessment='" + assessment + '\'' +
                '}';
    }
}