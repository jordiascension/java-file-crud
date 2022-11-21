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
	public void testUpdate() throws IOException, StudentNotFoundException {
		StudentRepository studentRepository = new StudentRepository();

		Student student = new Student();
		student.setIdStudent(1);
		student.setName("Pepe");
		student.setSurname("Soto");
		student.setAge(44);

		Student student1 = new Student();
		student1.setIdStudent(2);
		student1.setName("Alberto");
		student1.setSurname("Madrigal");
		student1.setAge(43);

		studentRepository.add(student);
		studentRepository.add(student1);

		Student studentToUpdate = new Student();
		studentToUpdate.setIdStudent(2);
		studentToUpdate.setName("Jordi");
		studentToUpdate.setSurname("Prim");
		studentToUpdate.setAge(50);

		Student studentUpdated = studentRepository.update(studentToUpdate);
		assertTrue(studentUpdated.getName().equals("Jordi"));
	}

	@Test
	public void testDelete() {
		fail("The testDelete method is not implemented");
	}

	@Test
	public void testGetAll() throws IOException, StudentNotFoundException {
		StudentRepository studentRepository = new StudentRepository();

		Student student = new Student();
		student.setIdStudent(1);
		student.setName("Pepe");
		student.setSurname("Soto");
		student.setAge(44);

		Student student1 = new Student();
		student1.setIdStudent(2);
		student1.setName("Alberto");
		student1.setSurname("Madrigal");
		student1.setAge(43);

		Student expectedStudent = studentRepository.add(student);
		Student expectedStudent1 = studentRepository.add(student1);

		assertTrue(studentRepository.getAll().size() == 2);
		assertTrue(studentRepository.getAll().get(0).getName().equals("Pepe"));
	}

}
