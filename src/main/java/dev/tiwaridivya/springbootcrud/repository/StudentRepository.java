package dev.tiwaridivya.springbootcrud.repository;

import dev.tiwaridivya.springbootcrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndIsDeletedIsFalse(Long id);
    List<Student> findByIsDeletedIsFalse();
}
