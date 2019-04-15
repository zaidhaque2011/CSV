import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

public class Csv {
	final static Logger logger = Logger.getLogger(Csv.class);
	public static String fileName = "\\Users\\Zaid\\Desktop\\data.csv";
	public static String targetFile = "\\Users\\Zaid\\Desktop\\target.csv";
	public static ArrayList<String> csvLines = new ArrayList<String>();
	public final static String header = "ID,FirstName,LastName,JobTitle";
	public static boolean hasHeader = false;
	public static void main(String[] args) {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
		read(fileName);															//read from CSV file
	    logger.debug("Unsorted:\n");										//print unsorted csv file
	    for(String parse: csvLines){
	        logger.info(parse +"\n");
	    }
	    
	    ArrayList<Employee> employees = convertAndSort(csvLines);				//converts array list of String to arraylist of Employee objects 
	    
	    for(Employee employee : employees)
	    	validate(validator, employee);
	    vf.close();
	    logger.debug("SORTED:\n");
		logger.info(header);
	    for(Employee employee : employees)										//Printing Sorted data
	        logger.info(employee.toString());
	    
	    write(targetFile, employees);											//write to targetFile
	}
	public static ArrayList<String> read(String fieName){
		File file = new File(fileName);
		try {
			Scanner sc = new Scanner(file);
			String line = "";
			if(sc.hasNextLine())
				line = sc.nextLine();												//header line
			if(line.equals(header)) {
				hasHeader = true;
				logger.debug("File has header");
			}
			else {
				csvLines.add(line);
				logger.debug("File header missing");
			}
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
		for(String employeesString : employeesStrings) {
			String[] parts = employeesString.split(",");
			int employeeID=-1;
			try {
				employeeID = Integer.valueOf(parts[0]);						//each column saved as object member to retain correct data type
			} catch(NumberFormatException e){
//				e.printStackTrace();
				logger.error("ID can only have integer values");
			}
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
			if(hasHeader)
				printWriter.printf(header+"\n");
			for (Employee parse : employees){
				printWriter.printf(parse.employeeID + "," + parse.fName + "," + parse.lName + "," + parse.jobTitle + "\n");
			}
			printWriter.close();
			logger.info("Write Successful");
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
    private static void validate(Validator validator, Employee employee) {
        // see below...
    	Set<ConstraintViolation<Employee>> violations
        = validator.validate(employee);
    if (violations.isEmpty()) {
        logger.info("Valid Employee: " + employee);
    } else {
        for (ConstraintViolation<Employee> violation : violations) {
            logger.error("Invalid employee: "
                 + violation.getRootBean());
            logger.warn("Offending property: "
                 + violation.getPropertyPath());
            logger.warn("Offending value: "
                 + violation.getInvalidValue());
            logger.error("Message: "
                 + violation.getMessage());
        }
        throw new ConstraintViolationException(violations);
    }
    }
}