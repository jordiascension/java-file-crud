package com.emc.repository;

import static com.emc.util.FileUtil.createFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.emc.exceptions.StudentNotFoundException;
import com.emc.model.Student;

public class StudentRepository {

	static final Logger logger = Logger.getLogger(StudentRepository.class);
	static Properties prop = null;
	static InputStream input = null;

	static {
		prop = new Properties();
		try {
			// https://howtoprogram.xyz/2017/01/17/read-file-and-resource-in-junit-test/
			// Lee el fichero de properties de src/main/resources
			// Si el programa se ejecuta desde el main
			// Lee el fichero de properties de src/test/resources
			// Si el programa se ejecuta desde el test
			input = StudentRepository.class
					.getResourceAsStream("/config.properties");
			prop.load(input);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ExceptionInInitializerError(e); // wrapping exceptions
		}
	}

	public Student add(Student student)
			throws IOException, StudentNotFoundException {

		String fileName = prop.getProperty("filename");

		createFile(fileName);

		try (FileWriter fileWriter = new FileWriter(fileName, true);
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
			bufferWriter.write(student.toString());
			bufferWriter.write(System.lineSeparator());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		}

		return getLastStudentByID(student.getIdStudent());

	}

	public Student update(Student student)
			throws StudentNotFoundException, IOException {

		String oldContent = "";

		BufferedReader reader = null;

		FileWriter writer = null;

		try {
			reader = new BufferedReader(
					new FileReader(prop.getProperty("filename")));

			// Reading all the lines of input text file into oldContent

			String line = reader.readLine();

			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();

				line = reader.readLine();
			}

			// Replacing oldString with newString in the oldContent
			Student oldStudent = getLastStudentByID(student.getIdStudent());
			String newContent = oldContent.replace(oldStudent.toString(),
					student.toString());

			// Rewriting the input text file with newContent

			writer = new FileWriter(prop.getProperty("filename"));

			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources

				reader.close();

				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return getLastStudentByID(student.getIdStudent());
	}

	public boolean delete(int studentId) {
		throw new UnsupportedOperationException(
				"The method is not yet implemented");
	}

	public List<Student> getAll() throws IOException {

		BufferedReader buffredReader = null;
		Student student = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			buffredReader = new BufferedReader(
					new FileReader(prop.getProperty("filename")));
			String linea;

			while ((linea = buffredReader.readLine()) != null) {

				String[] datos = linea.split(",", 4);

				student = new Student();
				student.setIdStudent(Integer.parseInt(datos[0]));
				student.setName(datos[1]);
				student.setSurname(datos[2]);
				student.setAge(Integer.parseInt(datos[3]));
				studentList.add(student);
			}

		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		} finally {

			if (buffredReader != null)
				buffredReader.close();
		}

		return studentList;

	}

	private Student getLastStudentByID(int id)
			throws IOException, StudentNotFoundException {

		BufferedReader buffredReader = null;
		Student student = null;

		try {
			buffredReader = new BufferedReader(
					new FileReader(prop.getProperty("filename")));
			String linea;
			boolean found = false;
			while ((linea = buffredReader.readLine()) != null) {

				String[] datos = linea.split(",", 4);
				if (datos[0].equals(String.valueOf(id))) {
					found = true;
					student = new Student();
					student.setIdStudent(id);
					student.setName(datos[1]);
					student.setSurname(datos[2]);
					student.setAge(Integer.parseInt(datos[3]));
					break;
				}
			}

			if (found == false) {
				throw new StudentNotFoundException("Student not found");
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		} finally {

			if (buffredReader != null)
				buffredReader.close();
		}

		return student;
	}

}
