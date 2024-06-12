package ch01.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	private int id;
	private String name;
	private String department;
	private int salary;
	private String hiredate;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public int getSalary() {
		return salary;
	}
	public String getHiredate() {
		return hiredate;
	}

	@Override
	public String toString() {
		return "USER ID : " + id + " || Name : " + name + " || Department : " + department +  " || Salary : " + salary + "\n" + " || Hire-Date : " + hiredate + "\n";
	}

}
