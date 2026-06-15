package com.example.springproject.studentmanagement.security.users;

//import com.example.springproject.studentmanagement.Entities.Student;
//import lombok.*;
//import org.jspecify.annotations.Nullable;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Getter
//@Setter
//@ToString
//public class CustomUserDetails implements UserDetails {
//
//    private final Student student;
//
//    public CustomUserDetails(Student student) {
//        this.student = student;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(student.getRole().name()));
//    }
//
//    @Override
//    public @Nullable String getPassword() {
//        return student.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return student.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
//}
