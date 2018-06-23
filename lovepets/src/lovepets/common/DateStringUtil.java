package lovepets.common;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateStringUtil {
	
	private static String valueOfString(String str, int len) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len - str.length(); i++) {
            sb.append("0");
        }
        return (sb.length() == 0) ? (str) : (sb.toString() + str);
    }
	public static String getTimeString(Calendar calendar) {      
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(calendar.get(Calendar.YEAR)))     
          .append(valueOfString(String.valueOf(calendar.get(Calendar.MONTH) + 1),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.MINUTE)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.SECOND)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.MILLISECOND)),3));       
        return sb.toString();
    } 
	
	public static String getFileName() {
		 Calendar calendar = new GregorianCalendar();
	      return getTimeString(calendar);	
	}
}
