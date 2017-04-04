package com.tianzeng.react.moudel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 教工数据
 *
 */
@Entity
@Table(name = "staff_data")
public class StaffDataMoudel implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String teacherName; // 教师姓名

    @Column
    private String jobNumber; // 教师工号

    @Column
    private String department; // 教师部门

    @Column
    private String contactInformation; // 联系方式

    @Column
    private String nativePlace; // 籍贯




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
}
