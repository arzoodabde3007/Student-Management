package com.example.springproject.studentmanagement.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    private String studentName;
    private String email;
    private String password;

    private String course;

//    @ManyToOne
//    @JoinColumn(name="student_id")
//    private Course course;


}
