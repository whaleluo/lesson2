package com.lession.utils;

import com.lession.model.Student;

public class StudentUtil {

	public static Student dsToStudent(String data,double score) {
		Student stu = new Student();
		String[] split = data.split(",");
		stu.setId(split[0]);
		stu.setName(split[1]);
		stu.setBirthday(split[2]);
		stu.setDescription(split[3]);
		stu.setScore(score);
		return stu;
	}
}
