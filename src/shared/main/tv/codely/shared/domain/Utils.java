package tv.codely.shared.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public final class Utils {
    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String dateToString(Timestamp timestamp) {
        return dateToString(timestamp.toLocalDateTime());
    }

    public static String jsonEncode(HashMap<String, Serializable> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static HashMap<String, Serializable> jsonDecode(String body) {
        try {
            return new ObjectMapper().readValue(body, HashMap.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static String toSnake(String text) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, text);
    }

    public static String toCamel(String text) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, text);
    }

    public static String toCamelFirstLower(String text) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, text);
    }

	public static void retry(int numberOfRetries, long waitTimeInMillis, Runnable operation) throws Exception {
		for (int i = 0; i < numberOfRetries; i++) {
			try {
				operation.run();
				return; // Success, exit the method
			} catch (Exception ex) {
				System.out.println("Retry " + (i + 1) + "/" + numberOfRetries + " fail. Retrying…");
				if (i >= numberOfRetries - 1) {
					throw ex;
				}

				try {
					Thread.sleep(waitTimeInMillis);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();

					throw new Exception("Operation interrupted while retrying", ie);
				}
			}
		}
	}

}
