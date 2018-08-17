package com.lokesh.resumeapp.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by kishumewara on 11/08/18.
 */

public class CreateBioModel implements Serializable{

    String userImg;
    String etName;
    String etMobileStr;
    String etFatherName;
    String etFatherOccupation;
    String etMotherName;
    String etMotherOcp;
    String etDob;
    String etTob;
    String etBrother;
    String etSister;
    String etWeight;
    String etHeight;
    String etGotra;
    String etMamaGotra;
    String etComplexion;
    String etManglic;
    String etSex;
    String etMarried;
    String etEducation;
    String etOccuption;
    String etAddress;


    String etState;
    String etCity;
    String etChilds;
    String etDescription;

    public CreateBioModel() {

    }



    public CreateBioModel(String userImg, String etName, String etMobileStr, String etFatherName, String etFatherOccupation, String etMotherName, String etMotherOcp, String etDob, String etTob, String etBrother, String etSister, String etWeight, String etHeight, String etGotra, String etMamaGotra, String etComplexion, String etManglic, String etSex, String etMarried, String etEducation, String etOccuption, String etAddress, String etState, String etCity, String etChilds, String etDescription) {
        this.userImg = userImg;
        this.etName = etName;
        this.etMobileStr = etMobileStr;
        this.etFatherName = etFatherName;
        this.etFatherOccupation = etFatherOccupation;
        this.etMotherName = etMotherName;
        this.etMotherOcp = etMotherOcp;
        this.etDob = etDob;
        this.etTob = etTob;
        this.etBrother = etBrother;
        this.etSister = etSister;
        this.etWeight = etWeight;
        this.etHeight = etHeight;
        this.etGotra = etGotra;
        this.etMamaGotra = etMamaGotra;
        this.etComplexion = etComplexion;
        this.etManglic = etManglic;
        this.etSex = etSex;
        this.etMarried = etMarried;
        this.etEducation = etEducation;
        this.etOccuption = etOccuption;
        this.etAddress = etAddress;
        this.etState = etState;
        this.etCity = etCity;
        this.etChilds = etChilds;
        this.etDescription = etDescription;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getEtName() {
        return etName;
    }

    public void setEtName(String etName) {
        this.etName = etName;
    }

    public String getEtMobileStr() {
        return etMobileStr;
    }

    public void setEtMobileStr(String etMobileStr) {
        this.etMobileStr = etMobileStr;
    }

    public String getEtFatherName() {
        return etFatherName;
    }

    public void setEtFatherName(String etFatherName) {
        this.etFatherName = etFatherName;
    }

    public String getEtFatherOccupation() {
        return etFatherOccupation;
    }

    public void setEtFatherOccupation(String etFatherOccupation) {
        this.etFatherOccupation = etFatherOccupation;
    }

    public String getEtMotherName() {
        return etMotherName;
    }

    public void setEtMotherName(String etMotherName) {
        this.etMotherName = etMotherName;
    }

    public String getEtMotherOcp() {
        return etMotherOcp;
    }

    public void setEtMotherOcp(String etMotherOcp) {
        this.etMotherOcp = etMotherOcp;
    }

    public String getEtDob() {
        return etDob;
    }

    public void setEtDob(String etDob) {
        this.etDob = etDob;
    }

    public String getEtTob() {
        return etTob;
    }

    public void setEtTob(String etTob) {
        this.etTob = etTob;
    }

    public String getEtBrother() {
        return etBrother;
    }

    public void setEtBrother(String etBrother) {
        this.etBrother = etBrother;
    }

    public String getEtSister() {
        return etSister;
    }

    public void setEtSister(String etSister) {
        this.etSister = etSister;
    }

    public String getEtWeight() {
        return etWeight;
    }

    public void setEtWeight(String etWeight) {
        this.etWeight = etWeight;
    }

    public String getEtHeight() {
        return etHeight;
    }

    public void setEtHeight(String etHeight) {
        this.etHeight = etHeight;
    }

    public String getEtGotra() {
        return etGotra;
    }

    public void setEtGotra(String etGotra) {
        this.etGotra = etGotra;
    }

    public String getEtMamaGotra() {
        return etMamaGotra;
    }

    public void setEtMamaGotra(String etMamaGotra) {
        this.etMamaGotra = etMamaGotra;
    }

    public String getEtComplexion() {
        return etComplexion;
    }

    public void setEtComplexion(String etComplexion) {
        this.etComplexion = etComplexion;
    }

    public String getEtManglic() {
        return etManglic;
    }

    public void setEtManglic(String etManglic) {
        this.etManglic = etManglic;
    }

    public String getEtSex() {
        return etSex;
    }

    public void setEtSex(String etSex) {
        this.etSex = etSex;
    }

    public String getEtMarried() {
        return etMarried;
    }

    public void setEtMarried(String etMarried) {
        this.etMarried = etMarried;
    }

    public String getEtEducation() {
        return etEducation;
    }

    public void setEtEducation(String etEducation) {
        this.etEducation = etEducation;
    }

    public String getEtOccuption() {
        return etOccuption;
    }

    public void setEtOccuption(String etOccuption) {
        this.etOccuption = etOccuption;
    }

    public String getEtAddress() {
        return etAddress;
    }

    public void setEtAddress(String etAddress) {
        this.etAddress = etAddress;
    }

    public String getEtState() {
        return etState;
    }

    public void setEtState(String etState) {
        this.etState = etState;
    }

    public String getEtCity() {
        return etCity;
    }

    public void setEtCity(String etCity) {
        this.etCity = etCity;
    }

    public String getEtChilds() {
        return etChilds;
    }

    public void setEtChilds(String etChilds) {
        this.etChilds = etChilds;
    }

    public String getEtDescription() {
        return etDescription;
    }

    public void setEtDescription(String etDescription) {
        this.etDescription = etDescription;
    }
}