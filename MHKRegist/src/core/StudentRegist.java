/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author MyPC
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRegist {
    private String stName;
    private String stId;
    private String stContact;
    private String stEmail;
    private String stDepartment;
    private String campus;
    private final int mountainCode;
    private final int fee = 6000000;
    private static final List<StudentRegist> studentList = new ArrayList<>();
public static List<StudentRegist> getStudentList() {
    return new ArrayList<>(studentList); // Trả về bản sao để tránh sửa đổi trực tiếp
}

public StudentRegist(String stName, String stId, String stContact, String stEmail, String campus, int mountainCode, int fee) {
    this.stName = stName;
    this.stId = stId;
    this.stContact = stContact;
    this.stEmail = stEmail;
    // Giả sử bạn không cần trường stDepartment hoặc thiết lập giá trị mặc định
    this.stDepartment = "Default"; 
    this.campus = campus;
    this.mountainCode = mountainCode;

}

public StudentRegist(String stId, int dummy) {
    this.stId = stId;
    // Các thuộc tính còn lại có thể được gán giá trị mặc định (hoặc rỗng)
    this.stName = "";
    this.stContact = "";
    this.stEmail = "";
    this.stDepartment = "";
    this.campus = "";
    this.mountainCode = 0; // hoặc dummy nếu bạn muốn
}


    public int getMountainCode() {
        return this.mountainCode;
    }

    public int getFee() {
        return fee;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getStContact() {
        return stContact;
    }

    public void setStContact(String stContact) {
        stContact = stContact.trim();
        if (stContact.matches("\\d{10}")) {
            this.stContact = stContact;
        } else {
            throw new IllegalArgumentException("Invalid contact format. Must be 10 digits.");
        }
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        stEmail = stEmail.trim();
        if (stEmail.matches("\\w+@\\w+\\.com")) {
            this.stEmail = stEmail;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }
    
    public String getStDepartment() {
        return stDepartment;
    }

    public void setStDepartment(String stDepartment) {
        this.stDepartment = stDepartment;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public static void addStudent(StudentRegist student) {
        studentList.add(student);
    }

    public static void removeStudent(StudentRegist student) {
        studentList.remove(student);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Kiểm tra nếu là cùng một object
        if (obj == null || getClass() != obj.getClass()) return false; // Kiểm tra null và class
        StudentRegist reg = (StudentRegist) obj; // Ép kiểu an toàn
        return stId.equals(reg.stId); // So sánh theo stId
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.stId);
        return hash;
    }

    @Override
    public String toString() {
        return stName + "," + stId + "," + stContact + "," + stEmail + "," + campus + "," + mountainCode + "," + fee;
    }
    }

