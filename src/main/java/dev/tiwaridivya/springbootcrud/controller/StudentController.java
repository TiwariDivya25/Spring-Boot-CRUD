package dev.tiwaridivya.springbootcrud.controller;

import dev.tiwaridivya.springbootcrud.dto.CreateStudentRequestDto;
import dev.tiwaridivya.springbootcrud.dto.CreateStudentResponseDto;
import dev.tiwaridivya.springbootcrud.dto.UpdateStudentRequestDto;
import dev.tiwaridivya.springbootcrud.dto.UpdateStudentResponseDto;
import dev.tiwaridivya.springbootcrud.entity.Student;
import dev.tiwaridivya.springbootcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDto> createStudent(@RequestBody CreateStudentRequestDto createStudentRequestDto) {
        CreateStudentResponseDto createdStudent = studentService.createStudent(createStudentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/get")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@RequestParam Long id){
        CreateStudentResponseDto studentResp = studentService.getStudent(id);
        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent(){
        List<CreateStudentResponseDto> studentList = studentService.getAllStudent();
        if(studentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/update/")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(
            @RequestParam Long id,
            @RequestBody UpdateStudentRequestDto updateStudentRequestDto) {

        UpdateStudentResponseDto studentResp =
                studentService.updateStudent(id, updateStudentRequestDto);

        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteStudent(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/delete-soft")
    public ResponseEntity<Boolean> deleteStudentSoftly(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);
        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(true);
    }
}
