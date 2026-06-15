-- Departments
INSERT INTO department (department_name) VALUES
('Computer Science'),
('Mechanical Engineering'),
('Business Administration');

-- Courses
INSERT INTO course (course_name, course_hod, department_id) VALUES
('Java Programming', 'Dr. Smith', 1),
('Data Structures', 'Dr. Adams', 1),
('Thermodynamics', 'Dr. Brown', 2),
('Marketing Management', 'Dr. Clark', 3);

-- Addresses
INSERT INTO address (house_no, city, state, pincode) VALUES
('101', 'Pune', 'Maharashtra', '411001'),
('202', 'Mumbai', 'Maharashtra', '400001'),
('303', 'Bangalore', 'Karnataka', '560001');

-- Students
INSERT INTO student (student_name, email, password, role, course_id, address_id) VALUES
('Arzoo Dabde', 'arzoo@gmail.com', 'arzoo123', 'ROLE_USER', 1, 1),
('Rahul Sharma', 'rahul@gmail.com', 'rahul123', 'ROLE_USER', 2, 2),
('Priya Patel', 'priya@gmail.com', 'priya123', 'ROLE_USER', 3, 3);
