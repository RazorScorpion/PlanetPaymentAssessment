package assessment.config;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Kripal
 *
 * Country listing based on Phone number prefix
 *
 */
public class CountryCodeConfiguration {
	
	final static Map<Country, String> countryCodeRegexMap = new HashMap<Country, String>() {
		private static final long serialVersionUID = -7585997266279130882L;
		{
			put(Country.Cameroon, "^237\\ ?[2368]\\d{7,8}$");	// "\\(237\\)\\ ?[2368]\\d{7,8}$");
			put(Country.Ethiopia, "^251\\ ?[1-59]\\d{8}$");		// "\\(251\\)\\ ?[1-59]\\d{8}$");
			put(Country.Morocco, "^212\\ ?[5-9]\\d{8}$");		// "\\(212\\)\\ ?[5-9]\\d{8}$");
			put(Country.Mozambique, "^258\\ ?[28]\\d{7,8}$");	// "\\(258\\)\\ ?[28]\\d{7,8}$");
			put(Country.Uganda, "^256\\ ?\\d{9}$");				// "\\(256\\)\\ ?\\d{9}$");
		}
	};
	
	public static Country getCountry(String phoneNumber) {
		
		Country country = null;
		Pattern pattern = null;
		Matcher matcher = null;
		for(Map.Entry<Country, String> countryCodeRegex : countryCodeRegexMap.entrySet()) {
			pattern = Pattern.compile(countryCodeRegex.getValue());
			matcher = pattern.matcher(phoneNumber);
			if (matcher.find())
			{
				return countryCodeRegex.getKey();
			}
		}
		return country;
	}
}
