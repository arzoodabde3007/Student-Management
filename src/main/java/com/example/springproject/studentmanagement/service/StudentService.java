package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentRequestDTO;
import com.example.springproject.studentmanagement.dto.StudentResponseDTO;
import com.example.springproject.studentmanagement.exceptions.StudentNotFoundException;
import com.example.springproject.studentmanagement.mappers.StudentMapper;
import com.example.springproject.studentmanagement.repository.AddressRepository;
import com.example.springproject.studentmanagement.repository.CourseRepository;
import com.example.springproject.studentmanagement.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private AddressRepository addressRepository;
    private StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository,
                          AddressRepository addressRepository,
                          StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.addressRepository = addressRepository;
        this.studentMapper = studentMapper;
    }

    // get all students
    public List<StudentResponseDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll();

        List<StudentResponseDTO> studentResponseDTOList = students.stream()
                .map(studentMapper::studentEntityToResponse)
                .toList();
        return studentResponseDTOList;

    }

    // Add student
    @Transactional
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {

        logger.info("Adding Student in Database.");
        Course course = courseRepository.findById(studentRequestDTO.getCourseId()).orElseThrow(
                () -> new RuntimeException("Course doesn't exist")
        );

        System.out.println(course);

        Address address = addressRepository.findById(studentRequestDTO.getAddressId())
                .orElseThrow(
                        () -> new RuntimeException("Address doesn't exist")
                );

        Student student = studentMapper.requestDTOToEntity(studentRequestDTO);

        student.setPassword(studentRequestDTO.getPassword());
        student.setCourse(course);
        student.setAddress(address);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.studentEntityToResponse(savedStudent);
    }


    // Update student
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO updatedStudent) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student of ID : " + id + " doesn't exist : ")
        );

        student.setStudentName(updatedStudent.getStudentName());
        student.setEmail(updatedStudent.getEmail());
        student.setPassword(updatedStudent.getPassword());

        Student savedStudent = studentRepository.save(student);
        return studentMapper.studentEntityToResponse(savedStudent);

    }

    //get by ID
    public StudentResponseDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found of Id: " + id));
        System.out.println("Student found : " + student);
        return studentMapper.studentEntityToResponse(student);


    }

    // delete student
    public void deleteStudents(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student not found with ID : " + id)
        );
        studentRepository.deleteById(id);
    }

    // Paging
    public Page<StudentResponseDTO> showPages(int pageNo, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Student> students = studentRepository.findAll(PageRequest.of(pageNo, size, sort));


        List<StudentResponseDTO> studentDTO = students.stream()
                .map(studentMapper::studentEntityToResponse)
                .toList();

        return new PageImpl<>(
                studentDTO,
                PageRequest.of(pageNo, size),
                students.getTotalElements()
        );

    }


}
