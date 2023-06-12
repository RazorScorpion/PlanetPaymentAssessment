# PlanetPaymentAssessment
Assessment project from Planet payment

*******************************************
*********** Problem Statement *************
*******************************************

We have a list of orders to be processed which needs to be imported, split by country and stored. This list arrives in the form of a CSV file[link].
Create a Service providing an interface to handle a CSV file, read its data and then persist it.

The country needs to be determined based on the client phone number. You should consider the following regex to determine the country:

Cameroon | Country code: +237 | Regex = \(237\)\ ?[2368]\d{7,8}$
Ethiopia  | Country code: +251 |  Regex = \(251\)\ ?[1-59]\d{8}$ 
Morocco | Country code: +212 | Regex = \(212\)\ ?[5-9]\d{8}$
Mozambique  | Country code: +258 | Regex = \(258\)\ ?[28]\d{7,8}$
Uganda | Country code: +256 | Regex = \(256\)\ ?\d{9}$

Make sure you include a README file describing your decisions and thought process.

Topics to take into account
-  Code standards/clean code
-  Abstraction and modularity
-  Performance and memory usage
-  Unit Tests
-  Documentation

Hints
- Don’t be shy on expressing and commenting on your decisions - It’s important that we understand your ideas
- It is required to exercise Java (no specific version), however, you’re free to choose any framework/architecture to suit best the problem

**********************************
*********** Solution *************
**********************************

Using Java 11 Maven project

1. As I didn't had MS-Excel Licensed version, so wasn't able to open test_file_2.xlsx and convert to CSV using MS-Excel.
2. Created a Service ExcelUtilityService.java class for XLSX to CSV conversion.
	Using Apache POI 5.0.0 library.
	/*
	 * Reading 1007616 records in 28s
	 * 
	 * Writing 1007616 records in 3min approx
	 */
3. Created POJO OrderItem.java for holding individual record information from CSV file.
	long id;
	String email;
	String phone_number;
	double weight;
4. Created Enum Country.java to hold Country names.
	Cameroon,
	Ethiopia,
	Morocco,
	Mozambique,
	Uganda
5. Created CountryCodeConfiguration.java, which would return Enum Country for respective phone_number matching the regex.
	Need to modify the provided regex as follows:
	Used	^237\ ?[2368]\d{7,8}$	instead of	\(237\)\ ?[2368]\d{7,8}$
	Used	^251\ ?[1-59]\d{8}$		instead of	\(251\)\ ?[1-59]\d{8}$
	Used	^212\ ?[5-9]\d{8}$		instead of	\(212\)\ ?[5-9]\d{8}$
	Used	^258\ ?[28]\d{7,8}$		instead of	\(258\)\ ?[28]\d{7,8}$
	Used	^256\ ?\d{9}$			instead of	\(256\)\ ?\d{9}$
	The Regex provided in problem statement would only work for the phone number format
	(XXX) XXXXXXXXX
	and wouldn't work on the phone numbers provided in test_file_2.xlsx which are in format
	XXX XXXXXXXXX
	Thus the Regex modification.
6. Created CsvUtility.java Interface providing following methods to implement
	csvReader(String csvPath)
	csvWriter(String outputCSV, List<OrderItem> contents)
7. Created CsvUtilityService.java Singleton class which is implementing CsvUtility.java interface
	csvReader(String csvPath) and returning List<OrderItem>
	csvWriter(String outputCSV, List<OrderItem> contents) to create outputCSV from the contents array
8. Created MainApplication.java with main() method
	a. Reading from XLSX and writing to CSV (Commented out as it is time consuming and required one time only)
	b. Reading from CSV
	c. Using Stream API and Lambda to group by Enum Country
	d. Writing List of Country records to respective Country JSON files
9. Exception handling by throwing IOException from CsvUtility
	Catching exceptions in the MainApplication
