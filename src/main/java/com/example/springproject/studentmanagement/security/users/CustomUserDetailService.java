package com.example.springproject.studentmanagement.security.users;

//import com.example.springproject.studentmanagement.Entities.Student;
//import com.example.springproject.studentmanagement.repository.StudentRepository;
//import com.example.springproject.studentmanagement.security.utils.AppConstants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;

//@Component
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Student student =  studentRepository
//                .findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException(AppConstants.USER_NOT_FOUND));
//        return new CustomUserDetails(student);
//    }
//}
