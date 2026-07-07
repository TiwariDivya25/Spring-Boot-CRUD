package dev.tiwaridivya.springbootcrud.service;

import dev.tiwaridivya.springbootcrud.entity.Student;
import dev.tiwaridivya.springbootcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student studentReq) {
        studentReq.setDeleted(false);
        Student studentResp = studentRepository.save(studentReq);
        return studentResp;
    }

    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndIsDeletedIsFalse(id);
        if(studentResp.isPresent()) {
            return studentResp.get();
        }
        return null;

    }

    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findByIsDeletedIsFalse();
        return studentList;
    }

    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndIsDeletedIsFalse(id);
        if(existingStudent.isEmpty()) {
            return null;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setSubject(studentReq.getEmail());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setSubject(studentReq.getSubject());

        studentToSave.setDeleted(false);
        return studentRepository.save(studentToSave);
    }

    public Boolean deleteStudent(Long id) {
        Boolean isStudent = studentRepository.existsById(id);
        if(!isStudent) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public Boolean deleteStudentSoftly(Long id) {
        Optional<Student> existingStudent = studentRepository.findByIdAndIsDeletedIsFalse(id);
        if(existingStudent.isEmpty()) {
            return false;
        }
        //get student
        Student studentToSave = existingStudent.get();
        //update IsDeleted
        studentToSave.setDeleted(true);
        //save
        studentRepository.save(studentToSave);
        return true;
    }
}
