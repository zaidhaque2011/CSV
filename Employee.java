import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

public class Employee implements Comparable<Employee>{
	@PositiveOrZero(message = "ID has to be positive numbers")
	public int employeeID;
	@Pattern(regexp = "[a-zA-Z' '''-]*")
	public String fName;
	@Pattern(regexp = "[a-zA-Z0-9' '''-]*", message = "idiot")
	public String lName;
	@Pattern(regexp = "[a-zA-Z0-9' '-]*")
	public String jobTitle;

	public Employee(int employeeID, String fName, String lName, String jobTitle) {
		//super();
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