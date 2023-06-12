package assessment.abstraction;

import java.io.IOException;
import java.util.List;

import assessment.model.OrderItem;

/**
 * 
 * @author Kripal
 *
 * Interface for CSV Utility methods
 *
 */
public interface CsvUtility {
	
	public List<OrderItem> csvReader(String csvPath) throws IOException;
	
	public void csvWriter(String outputCSV, List<OrderItem> contents) throws IOException;
	
}
