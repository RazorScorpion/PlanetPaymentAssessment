package assessment.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import assessment.abstraction.CsvUtility;
import assessment.model.OrderItem;

/**
 * CSV service Singleton Class
 */
public class CsvUtilityService implements CsvUtility {

	private static CsvUtilityService csvUtilityService = null;
	
	private CsvUtilityService() {}
	
	public static synchronized CsvUtilityService getInstance() {
		if(csvUtilityService == null) {
			csvUtilityService = new CsvUtilityService();
		}
		return csvUtilityService;
	}
	
	@Override
	public List<OrderItem> csvReader(String csvPath) throws IOException {
		List<OrderItem> records = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(csvPath));
		String line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			OrderItem order = new OrderItem(Long.valueOf(values[0].strip()), values[1].strip(), values[2].strip(), Double.valueOf(values[3].strip()));
			records.add(order);
		}
		br.close();
		return records;
	}

	@Override
	public void csvWriter(String outputCSV, List<OrderItem> contents) throws IOException {
		FileWriter fileWriter = new FileWriter(outputCSV);
		fileWriter.write("[");
		int counter = 0;
		for(OrderItem content : contents)
			fileWriter.write(content + (++counter < contents.size() ? ",\n" : ""));
		fileWriter.write("]");
		fileWriter.close();
	}
}
