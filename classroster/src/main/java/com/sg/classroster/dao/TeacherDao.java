/*
 * @author Salajrawi
 */

package com.sg.classroster.dao;

import com.sg.classroster.model.Teacher;
import java.util.List;

/**
 *
 * @author Salajrawi
 */
public interface TeacherDao {

    Teacher getTeacherById(int id);
    List<Teacher> getAllTeachers();
    Teacher addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
    
}
