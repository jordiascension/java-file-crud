package com.emc.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import com.emc.exceptions.StudentNotFoundException;
import com.emc.model.Student;
import com.emc.util.FileUtil;

public class StudentRepositoryTest {

	@After
	public void afterEachTestMethod() {
		FileUtil.deleteFile();
	}

	@Test
	public void testAdd() throws IOException, StudentNotFoundException {
		StudentRepository studentRepository = new StudentRepository();

		Student student = new Student();
		student.setIdStudent(1);
		student.setName("Pepe");
		student.setSurname("Soto");
		student.setAge(44);

		Student expectedStudent = studentRepository.add(student);

		assertTrue(student.equals(expectedStudent));
	}

	@Test
	public void testUpdate() {
		fail("The testUpdate method is not implemented");
	}

	@Test
	public void testDelete() {
		fail("The testDelete method is not implemented");
	}

	@Test
	public void testGetAll() {
		fail("The testGetAll method is not implemented");
	}

}
