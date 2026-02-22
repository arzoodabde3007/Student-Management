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
import com.example.springproject.studentmanagement.repository.DepartmentRepository;
import com.example.springproject.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO, Long courseId, Long addressId){

        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new RuntimeException("Course doesn't exist")
        );
        System.out.println(course);

        Address address = addressRepository.findById(addressId)
                .orElseThrow(
                        () -> new RuntimeException("Address doesn't exist")
                );
        System.out.println(address);
        studentRequestDTO.setCourse(course);
        studentRequestDTO.setAddress(address);
        Student student = StudentMapper.requestDTOToEntity(studentRequestDTO);
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
//        student.setCourse(updatedStudent.getCourse());
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
