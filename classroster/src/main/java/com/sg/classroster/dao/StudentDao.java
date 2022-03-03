/*
 * @authorSalajrawi
 */

package com.sg.classroster.dao;

import com.sg.classroster.model.Student;
import java.util.List;

/**
 *
 * @author Salajrawi
 */
public interface StudentDao {

    Student getStudentById(int id);
    List<Student> getAllStudents();
    Student addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(int id);
}
