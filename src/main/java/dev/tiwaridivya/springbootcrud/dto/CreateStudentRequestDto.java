package dev.tiwaridivya.springbootcrud.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {
    @NotBlank(message = "Name cannot be null/empty/blank")
    @Size(min = 2, max = 50, message = "name should be withing 2 to 50 characters")
    private String name;
    @Min(value = 18, message = "Student must be at least 18 years old")
    private int age;
    @Email
    private String email;
    @NotEmpty
    private int rollNo;
    @NotBlank
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
