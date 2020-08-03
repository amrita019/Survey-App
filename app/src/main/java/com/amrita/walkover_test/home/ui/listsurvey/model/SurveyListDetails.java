package com.amrita.walkover_test.home.ui.listsurvey.model;

public class SurveyListDetails {

    private String id;
    private String name;
    private String address;
    private int age;
    private String gender;
    private String lastEducation;
    private String wantTopBe;

    public SurveyListDetails(String id, String name, String address, int age, String gender, String lastEducation, String wantTopBe) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.lastEducation = lastEducation;
        this.wantTopBe = wantTopBe;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getLastEducation() {
        return lastEducation;
    }

    public String getWantTopBe() {
        return wantTopBe;
    }
}
