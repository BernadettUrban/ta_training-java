package googlecalculatortest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public final String BASE_URL_FOR_GOOGLE = "https://cloud.google.com/";
    public final String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";
    public final String BASE_URL_FOR_EMAIL = "https://yopmail.com/";

    public final String NUMBER_OF_INSTANCES = "4";

    public String regexForUSD(String toValidate) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("([0-9]{1,3},([0-9]{3},)*[0-9]{3}|[0-9]+)(.[0-9][0-9])?");

        Matcher matcher = pattern.matcher(toValidate);
        if (matcher.find()) {
            sb.append(matcher.group(0));
        }
        return sb.toString();
    }

    public String splitEstimateLines(String line, String delimeter, Integer splitpart) {
        String[] estimateElements = line.split(delimeter);
        return estimateElements[splitpart];
    }

}
