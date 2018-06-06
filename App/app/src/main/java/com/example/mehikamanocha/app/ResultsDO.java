package com.example.mehikamanocha.app;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "myapplication-mobilehub-362600887-Results")

public class ResultsDO {
    private String _userId;
    private Double _1stRunTaps;
    private Double _2ndRunTaps;
    private Double _3rdRunTaps;
    private Double _age;
    private String _avgTaps;
    private String _dateofDiagnosis;
    private String _gender;
    private Boolean _havePD;
    private Double _insideTapsA;
    private Double _insideTapsB;
    private String _medication;
    private Double _outsideTaps;
    private Boolean _takeEBS;
    private String _timeofMedication;
    private String _whichHand;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }

    @DynamoDBAttribute(attributeName = "1stRunTaps")
    public Double get1stRunTaps() {
        return _1stRunTaps;
    }

    public void set1stRunTaps(final Double _1stRunTaps) {
        this._1stRunTaps = _1stRunTaps;
    }

    @DynamoDBAttribute(attributeName = "2ndRunTaps")
    public Double get2ndRunTaps() {
        return _2ndRunTaps;
    }

    public void set2ndRunTaps(final Double _2ndRunTaps) {
        this._2ndRunTaps = _2ndRunTaps;
    }

    @DynamoDBAttribute(attributeName = "3rdRunTaps")
    public Double get3rdRunTaps() {
        return _3rdRunTaps;
    }

    public void set3rdRunTaps(final Double _3rdRunTaps) {
        this._3rdRunTaps = _3rdRunTaps;
    }

    @DynamoDBAttribute(attributeName = "Age")
    public Double getAge() {
        return _age;
    }

    public void setAge(final Double _age) {
        this._age = _age;
    }

    @DynamoDBAttribute(attributeName = "AvgTaps")
    public String getAvgTaps() {
        return _avgTaps;
    }

    public void setAvgTaps(final String _avgTaps) {
        this._avgTaps = _avgTaps;
    }

    @DynamoDBAttribute(attributeName = "DateofDiagnosis")
    public String getDateofDiagnosis() {
        return _dateofDiagnosis;
    }

    public void setDateofDiagnosis(final String _dateofDiagnosis) {
        this._dateofDiagnosis = _dateofDiagnosis;
    }

    @DynamoDBAttribute(attributeName = "Gender")
    public String getGender() {
        return _gender;
    }

    public void setGender(final String _gender) {
        this._gender = _gender;
    }

    @DynamoDBAttribute(attributeName = "HavePD")
    public Boolean getHavePD() {
        return _havePD;
    }

    public void setHavePD(final Boolean _havePD) {
        this._havePD = _havePD;
    }

    @DynamoDBAttribute(attributeName = "InsideTapsA")
    public Double getInsideTapsA() {
        return _insideTapsA;
    }

    public void setInsideTapsA(final Double _insideTapsA) {
        this._insideTapsA = _insideTapsA;
    }

    @DynamoDBAttribute(attributeName = "InsideTapsB")
    public Double getInsideTapsB() {
        return _insideTapsB;
    }

    public void setInsideTapsB(final Double _insideTapsB) {
        this._insideTapsB = _insideTapsB;
    }

    @DynamoDBAttribute(attributeName = "Medication")
    public String getMedication() {
        return _medication;
    }

    public void setMedication(final String _medication) {
        this._medication = _medication;
    }

    @DynamoDBAttribute(attributeName = "OutsideTaps")
    public Double getOutsideTaps() {
        return _outsideTaps;
    }

    public void setOutsideTaps(final Double _outsideTaps) {
        this._outsideTaps = _outsideTaps;
    }

    @DynamoDBAttribute(attributeName = "TakeEBS")
    public Boolean getTakeEBS() {
        return _takeEBS;
    }

    public void setTakeEBS(final Boolean _takeEBS) {
        this._takeEBS = _takeEBS;
    }

    @DynamoDBAttribute(attributeName = "TimeofMedication")
    public String getTimeofMedication() {
        return _timeofMedication;
    }

    public void setTimeofMedication(final String _timeofMedication) {
        this._timeofMedication = _timeofMedication;
    }

    @DynamoDBAttribute(attributeName = "WhichHand")
    public String getWhichHand() {
        return _whichHand;
    }

    public void setWhichHand(final String _whichHand) {
        this._whichHand = _whichHand;
    }

}
