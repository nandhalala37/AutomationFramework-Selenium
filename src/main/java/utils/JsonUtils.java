package utils;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * JsonUtils provides methods to parse JSON files using Gson.
 */
public class JsonUtils {

    private static final Gson gson = new Gson();

    /**
     * Parses a JSON file into a specified class.
     *
     * @param filePath Path to JSON file in `resources/testdata/`
     * @param clazz    Target class type
     * @param <T>      Generic return type
     * @return Parsed object of type T
     */
    public static <T> T fromJson(String filePath, Class<T> clazz) {
        try (InputStreamReader reader = new InputStreamReader(
                JsonUtils.class.getClassLoader().getResourceAsStream("testdata/" + filePath))) {
            return gson.fromJson(reader, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON file: " + filePath, e);
        }
    }

    /**
     * Parses a JSON file into a generic List or Map structure.
     *
     * @param filePath Path to JSON file
     * @param type     TypeToken for generic type
     * @param <T>      Type of object
     * @return Parsed data as T
     */
    public static <T> T fromJson(String filePath, Type type) {
        try (InputStreamReader reader = new InputStreamReader(
                JsonUtils.class.getClassLoader().getResourceAsStream("testdata/" + filePath))) {
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON file: " + filePath, e);
        }
    }

    /**
     * Converts an object to JSON string.
     *
     * @param object Object to convert
     * @return JSON String
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
