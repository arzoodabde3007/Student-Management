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
    private Long studentId;

    private String studentName;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;


}
