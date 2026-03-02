package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentMapper;
import com.example.springproject.studentmanagement.dto.StudentRequestDTO;
import com.example.springproject.studentmanagement.dto.StudentResponseDTO;
import com.example.springproject.studentmanagement.exceptions.StudentNotFoundException;
import com.example.springproject.studentmanagement.repository.AddressRepository;
import com.example.springproject.studentmanagement.repository.CourseRepository;
import com.example.springproject.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private AddressRepository addressRepository;

    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository,
                          AddressRepository addressRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.addressRepository = addressRepository;
    }

    // get all students
    public List<StudentResponseDTO> getAllStudent(){
        List<Student> students = studentRepository.findAll();

        List<StudentResponseDTO> studentResponseDTOList = students.stream()
                .map(StudentMapper::studentEntityToResponse)
                .toList();
        return studentResponseDTOList;

    }

    // Add student
    @Transactional
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO){

        Course course = courseRepository.findById(studentRequestDTO.getCourseId()).orElseThrow(
                () -> new RuntimeException("Course doesn't exist")
        );

        System.out.println(course);

        Address address = addressRepository.findById(studentRequestDTO.getAddressId())
                .orElseThrow(
                        () -> new RuntimeException("Address doesn't exist")
                );

        Student student = StudentMapper.requestDTOToEntity(studentRequestDTO);
        student.setCourse(course);
        student.setAddress(address);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.studentEntityToResponse(savedStudent);
    }


    // Update student
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO updatedStudent){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student of ID : " + id + " doesn't exist : ")
        );

        student.setStudentName(updatedStudent.getStudentName());
        student.setEmail(updatedStudent.getEmail());
        student.setPassword(updatedStudent.getPassword());

        Student savedStudent = studentRepository.save(student);
        return StudentMapper.studentEntityToResponse(savedStudent);

    }

    //get by ID
    public StudentResponseDTO getStudentById(Long id){

        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found of Id: " + id));
        System.out.println("Student found : "+ student);
        return StudentMapper.studentEntityToResponse(student);


    }

    // delete student
    public void deleteStudents(Long id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student not found with ID : "+id)
        );
        studentRepository.deleteById(id);
    }


}
