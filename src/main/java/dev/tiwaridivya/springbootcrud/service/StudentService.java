package dev.tiwaridivya.springbootcrud.service;

import dev.tiwaridivya.springbootcrud.dto.CreateStudentRequestDto;
import dev.tiwaridivya.springbootcrud.dto.CreateStudentResponseDto;
import dev.tiwaridivya.springbootcrud.dto.UpdateStudentRequestDto;
import dev.tiwaridivya.springbootcrud.dto.UpdateStudentResponseDto;
import dev.tiwaridivya.springbootcrud.entity.Student;
import dev.tiwaridivya.springbootcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto createStudentRequestDto) {
        Student student = mapToEntity(createStudentRequestDto);

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);

    }

    public CreateStudentResponseDto getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndIsDeletedIsFalse(id);
        if(studentResp.isPresent()) {
            return mapToDto((studentResp.get()));
        }
        return null;

    }

    public List<CreateStudentResponseDto> getAllStudent() {
        List<Student> studentList = studentRepository.findByIsDeletedIsFalse();

        return studentList.stream().
                map(this::mapToDto).
                toList();
    }

    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {
        Optional<Student> existingStudent =
                studentRepository.findByIdAndIsDeletedIsFalse(id);
        if(existingStudent.isEmpty()) {
            return null;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setUpdatedAt(LocalDateTime.now());

        studentToSave.setDeleted(false);
        Student savedStudent = studentRepository.save(studentToSave);

        return mapToUpdateDto(savedStudent);
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

    private Student mapToEntity(CreateStudentRequestDto createStudentRequestDto) {
        Student student = new Student();

        student.setName(createStudentRequestDto.getName());
        student.setAge(createStudentRequestDto.getAge());
        student.setEmail(createStudentRequestDto.getEmail());
        student.setRollNo(createStudentRequestDto.getRollNo());
        student.setSubject(createStudentRequestDto.getSubject());

        student.setDeleted(false);

        return student;
    }

    private CreateStudentResponseDto mapToDto(Student student) {

        CreateStudentResponseDto responseDto = new CreateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student saved successfully");
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;
    }

    private UpdateStudentResponseDto mapToUpdateDto(Student student) {
        UpdateStudentResponseDto responseDto = new UpdateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student saved successfully");
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;

    }
}
