package com.openclassroomsProject.Mediscreenclient.beans;

import com.openclassroomsProject.Mediscreenclient.constants.ValidationConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.sql.Date;

/**
 * Bean class representing a Patient.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
public class PatientBean {

    private int id;
    @NotNull(message = ValidationConstants.NOT_NULL)
    private String family;
    @NotNull(message = ValidationConstants.NOT_NULL)
    private String given;
    @NotNull(message = ValidationConstants.NOT_NULL)
    private Date dob;
    @Size(min = 1, max = 1, message = ValidationConstants.WRONG_FORMAT)
    @NotNull(message = ValidationConstants.NOT_NULL)
    private String sex;
    private String address;
    @Pattern(regexp = ValidationConstants.REGEX_PHONE_NUMBER, message = ValidationConstants.WRONG_FORMAT)
    private String phone;

    /**
     * Constructor for PatientBean.
     *
     * @param family   The family name of the patient.
     * @param given    The given name of the patient.
     * @param dob      The date of birth of the patient.
     * @param sex      The sex of the patient.
     * @param address  The address of the patient.
     * @param phone    The phone number of the patient.
     */
    public PatientBean(@NotNull String family, @NotNull String given, @NotNull Date dob, @NotNull String sex, String address, String phone) {
        this.family = family;
        this.given = given;
        this.dob = dob;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PatientBean{" +
                "id=" + id +
                ", family='" + family + '\'' +
                ", given='" + given + '\'' +
                ", dob=" + dob +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}