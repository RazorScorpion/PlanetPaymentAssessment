package assessment.main;

import java.io.File;
//import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import assessment.abstraction.CsvUtility;
import assessment.config.Country;
import assessment.config.CountryCodeConfiguration;
import assessment.model.OrderItem;
import assessment.service.CsvUtilityService;
//import assessment.service.ExcelUtilityService;

public class MainApplication {

	public static void main(String[] args) {
		
		/*
		 * Uncomment the lines 25 - 35 to create CSV file from test_file_2.xlsx
		 */
//		String filePath = "../test_file_2.xlsx"; 
//		try {
			/*
			 * Reading 1007616 records in 28s
			 * 
			 * Writing 1007616 records in 3min approx
			 */
//			ExcelUtilityService.csvConverter(filePath, csvPath);
//		} catch(IOException ioe) {
//			System.out.println(ioe.getMessage());
//		}
		
		String csvPath = "../inputCSV/planet_payment_test.csv";
		String outputCsvDirectory = "../outputCSV/";
		try {
			CsvUtility csvUtility = CsvUtilityService.getInstance();
			List<OrderItem> orderList = csvUtility.csvReader(csvPath);

			// We can store List<OrderItem> to persist in DB
			Map<Country, List<OrderItem>> countryOrderListMap = orderList.stream()
					.collect(Collectors.groupingBy(
							o -> CountryCodeConfiguration.getCountry(o.getPhoneNumber()),
							Collectors.toList()
					));

			for (Map.Entry<Country, List<OrderItem>> countryOrderList : countryOrderListMap.entrySet()) {
				// CSV writer to create Country wise JSON files
				csvUtility.csvWriter(outputCsvDirectory + countryOrderList.getKey() + ".json", countryOrderList.getValue());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
