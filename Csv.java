import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Csv {
	public static String fileName = "\\Users\\Zaid\\Desktop\\data.csv";
	public static String targetFile = "\\Users\\Zaid\\Desktop\\target.csv";
	public static ArrayList<String> csvLines = new ArrayList<String>();
	public static String header = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stu
		read(fileName);															//read from CSV file
	    System.out.println("Unsorted:\n");										//print unsorted csv file
	    for(String parse: csvLines){
	        System.out.println(parse +"\n");
	    }
	    
	    ArrayList<Employee> employees = convertAndSort(csvLines);				//converts array list of String to arraylist of Employee objects 
		System.out.println("SORTED:\n");										//and sorts them.
		System.out.println(header);
	    for(Employee employee : employees)										//Printing Sorted data
	        System.out.println(employee.toString());
	    
	    write(targetFile, employees);											//write to targetFile
	}
	public static ArrayList<String> read(String fieName){
		File file = new File(fileName);
		try {
			Scanner sc = new Scanner(file);
			String line = "";
			header = sc.nextLine();												//header line
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				csvLines.add(line);
				}
			sc.close();
			}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return csvLines;
		}
	public static ArrayList<Employee> convertAndSort(ArrayList<String> employeesStrings) {
		ArrayList<Employee> employees = new ArrayList<>();
		employeesStrings.remove(0);
		for(String employeesString : employeesStrings) {
			String[] parts = employeesString.split(",");
	        int employeeID = Integer.valueOf(parts[0]);						//each column saved as object member to retain correct data type
	        String fName = parts[1];										//for future applications.
	        String lName = parts[2];
	        String jobTitle = parts[3];
	        employees.add(new Employee(employeeID, fName, lName, jobTitle));
		}
		Collections.sort(employees, new Comparator<Employee>() {			//Using comparator
	    //Override
	    public int compare(Employee o1, Employee o2) {						//overiding compareTo() #polymorphism
	    	return o1.compareTo(o2);
	        }
	    });
		return employees;
	}
	
	public static void write(String targetFile, ArrayList<Employee> employees) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(targetFile);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf(header+"\n");
			for (Employee parse : employees){
				printWriter.printf(parse.employeeID + "," + parse.fName + "," + parse.lName + "," + parse.jobTitle + "\n");
			}
			printWriter.close();
			System.out.println("Write Successful");
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
}