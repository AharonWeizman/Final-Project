package org.ecocheck.ecocheck.dto;

import org.ecocheck.ecocheck.FactoryProcess;

import java.io.Serializable;

/**
 * Created by Ron on 24/04/2016.
 */
public class Process implements Serializable
{
    int reportNumber;
    //department field
    String department;

    //assignment fields
    String assignment, numOfEmployee;

    // process field
    String process,processComment,processDuration,processMethod, processRecommendation,processAmount;
    boolean processBodyExposure,warm,closed;

    //chemical exposure fields
    String notChemicalExposure,inhalantChemicalExposure;

    //personal protective fields
    String personalProtectiveEquip,descPersonalControl;

    // field of process control
    String typeProcessControl,descProcessControl;

    //materials associated tasks field
    String materialCommercialName, frequencyUsingMaterial;
    boolean linked, tinnyAmount,msds;

    //components and elements fields
    String fastChoice,factor,policyLevel,regularityType,unit,numOfSample,ingredients,search_add,materialComponentPercent;

    //define the process table
    public static final String TAG = FactoryProcess.class.getSimpleName();

    public static final String TABLE = "Process";
    public static final String KEY_ReportId = "ReportNumber";
    public static final String KEY_Name_Department = "department";
    public static final String COLUMN_PROCESS_Assignment = "assignment";
    public static final String COLUMN_PROCESS_NumOfEmployee = "numOfEmployee";
    public static final String COLUMN_PROCESS_Process = "process";
    public static final String COLUMN_PROCESS_ProcessComment = "processComment";
    public static final String COLUMN_PROCESS_ProcessDuration = "processDuration";
    public static final String COLUMN_PROCESS_ProcessMethod = "processMethod";
    public static final String COLUMN_PROCESS_ProcessBodyExposure = "processBodyExposure";
    public static final String COLUMN_PROCESS_ProcessRecommendation = "processRecommendation";
    public static final String COLUMN_PROCESS_Warm = "warm";
    public static final String COLUMN_PROCESS_Closed = "closed";
    public static final String COLUMN_PROCESS_ProcessAmount = "processAmount";
    public static final String COLUMN_PROCESS_NotChemicalExposure = "notChemicalExposure";
    public static final String COLUMN_PROCESS_InhalantChemicalExposure = "inhalantChemicalExposure";
    public static final String COLUMN_PROCESS_PersonalProtectiveEquip = "personalProtectiveEquip";
    public static final String COLUMN_PROCESS_DescPersonalControl = "descPersonalControl";
    public static final String COLUMN_PROCESS_TypeProcessControl = "typeProcessControl";
    public static final String COLUMN_PROCESS_DescProcessControl = "descProcessControl";
    public static final String COLUMN_PROCESS_MaterialCommercialName = "materialCommercialName";
    public static final String COLUMN_PROCESS_FrequencyUsingMaterial = "frequencyUsingMaterial";
    public static final String COLUMN_PROCESS_Linked = "linked";
    public static final String COLUMN_PROCESS_TinnyAmount = "tinnyAmount";
    public static final String COLUMN_PROCESS_Msds = "msds";
    public static final String COLUMN_PROCESS_FastChoice = "fastChoice";
    public static final String COLUMN_PROCESS_factor = "factor";
    public static final String COLUMN_PROCESS_PolicyLevel = "policyLevel";
    public static final String COLUMN_PROCESS_RegularityType = "regularityType";
    public static final String COLUMN_PROCESS_Unit = "unit";
    public static final String COLUMN_PROCESS_NumOfSample = "numOfSample";
    public static final String COLUMN_PROCESS_Ingredients = "ingredients";
    public static final String COLUMN_PROCESS_Search_add = "search_add";
    public static final String COLUMN_PROCESS_MaterialComponentPercent = "materialComponentPercent";


    /*
    *   get and set for all the fields
    */
    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getNumOfEmployee() {
        return numOfEmployee;
    }

    public void setNumOfEmployee(String numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProcessComment() {
        return processComment;
    }

    public void setProcessComment(String processComment) {
        this.processComment = processComment;
    }

    public String getProcessDuration() {
        return processDuration;
    }

    public void setProcessDuration(String processDuration) {
        this.processDuration = processDuration;
    }

    public String getProcessMethod() {
        return processMethod;
    }

    public void setProcessMethod(String processMethod) {
        this.processMethod = processMethod;
    }

    public String getProcessRecommendation() {
        return processRecommendation;
    }

    public void setProcessRecommendation(String processRecommendation) {
        this.processRecommendation = processRecommendation;
    }

    public String getProcessAmount() {
        return processAmount;
    }

    public void setProcessAmount(String processAmount) {
        this.processAmount = processAmount;
    }

    public boolean isProcessBodyExposure() {
        return processBodyExposure;
    }

    public void setProcessBodyExposure(boolean processBodyExposure) {
        this.processBodyExposure = processBodyExposure;
    }

    public boolean isWarm() {
        return warm;
    }

    public void setWarm(boolean warm) {
        this.warm = warm;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getNotChemicalExposure() {
        return notChemicalExposure;
    }

    public void setNotChemicalExposure(String notChemicalExposure) {
        this.notChemicalExposure = notChemicalExposure;
    }

    public String getInhalantChemicalExposure() {
        return inhalantChemicalExposure;
    }

    public void setInhalantChemicalExposure(String inhalantChemicalExposure) {
        this.inhalantChemicalExposure = inhalantChemicalExposure;
    }

    public String getPersonalProtectiveEquip() {
        return personalProtectiveEquip;
    }

    public void setPersonalProtectiveEquip(String personalProtectiveEquip) {
        this.personalProtectiveEquip = personalProtectiveEquip;
    }

    public String getDescPersonalControl() {
        return descPersonalControl;
    }

    public void setDescPersonalControl(String descPersonalControl) {
        this.descPersonalControl = descPersonalControl;
    }

    public String getTypeProcessControl() {
        return typeProcessControl;
    }

    public void setTypeProcessControl(String typeProcessControl) {
        this.typeProcessControl = typeProcessControl;
    }

    public String getDescProcessControl() {
        return descProcessControl;
    }

    public void setDescProcessControl(String descProcessControl) {
        this.descProcessControl = descProcessControl;
    }

    public String getMaterialCommercialName() {
        return materialCommercialName;
    }

    public void setMaterialCommercialName(String materialCommercialName) {
        this.materialCommercialName = materialCommercialName;
    }

    public String getFrequencyUsingMaterial() {
        return frequencyUsingMaterial;
    }

    public void setFrequencyUsingMaterial(String frequencyUsingMaterial) {
        this.frequencyUsingMaterial = frequencyUsingMaterial;
    }

    public boolean isLinked() {
        return linked;
    }

    public void setLinked(boolean linked) {
        this.linked = linked;
    }

    public boolean isMsds() {
        return msds;
    }

    public void setMsds(boolean msds) {
        this.msds = msds;
    }

    public boolean isTinnyAmount() {
        return tinnyAmount;
    }

    public void setTinnyAmount(boolean tinnyAmount) {
        this.tinnyAmount = tinnyAmount;
    }

    public String getFastChoice() {
        return fastChoice;
    }

    public void setFastChoice(String fastChoice) {
        this.fastChoice = fastChoice;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getPolicyLevel() {
        return policyLevel;
    }

    public void setPolicyLevel(String policyLevel) {
        this.policyLevel = policyLevel;
    }

    public String getRegularityType() {
        return regularityType;
    }

    public void setRegularityType(String regularityType) {
        this.regularityType = regularityType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String get_numOfSample() {
        return numOfSample;
    }

    public void set_numOfSample(String _numOfSample) {
        this.numOfSample = _numOfSample;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSearch_add() {
        return search_add;
    }

    public void setSearch_add(String search_add) {
        this.search_add = search_add;
    }

    public String getMaterialComponentPercent() {
        return materialComponentPercent;
    }

    public void setMaterialComponentPercent(String materialComponentPercent) {
        this.materialComponentPercent = materialComponentPercent;
    }
}
