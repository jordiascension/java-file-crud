package com.emc.repository;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.emc.model.Student;

public class StudentRepositoryTest {

	@Test
	public void testAdd() throws IOException {
		StudentRepository studentRepository = new StudentRepository();

		Student student = new Student();
		student.setIdStudent(1);
		student.setName("Pepe");
		student.setSurname("Soto");
		student.setAge(44);

		Student expectedStudent = studentRepository.add(student);

		assertTrue(student.equals(expectedStudent));
	}

}
