public class Employee implements Comparable<Employee>{
	public int employeeID;
	public String fName, lName, jobTitle;

	public Employee(int employeeID, String fName, String lName, String jobTitle) {
		super();
		this.employeeID = employeeID;
		this.fName = fName;
		this.lName = lName;
		this.jobTitle = jobTitle;
	}
	
	public int getEmployeeID() {
		return employeeID;
		}
	public String getfName() {
		return fName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public String getadress() {
		return jobTitle;
		}
	//Override
	public int compareTo (Employee e) {
		if(this.fName.compareTo(e.fName) == 0)									//Bonus - sorting by Last Name if First name is same
			return this.lName.compareTo(e.lName);
		else
			return this.fName.compareTo(e.fName);
		}
	//Override
	public String toString() {
		return "Employee{" + "employeeID=" + employeeID + ", fName=" + fName + ", lName=" + lName + ", jobTitle=" + jobTitle + '}';
	}
}