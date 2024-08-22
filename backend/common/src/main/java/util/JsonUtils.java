package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils {

    public static String objectToJson(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Error when writing object to json string [{}]", e.getMessage());
        }
        return null;
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Error when reading json string to object [{}]", e.getMessage());
        }
        return null;
    }

    public static <T> T responseToObject(Object obj, Class<T> clazz) {
        String json = objectToJson(obj);
        return jsonToObject(json, clazz);
    }

    public static <T> List<T> responseToListObject(Object obj, Class<T> clazz) {
        String json = objectToJson(obj);
        List listObj = jsonToObject(json, List.class);
        if (listObj == null) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<>();
        for (Object itemObj : listObj) {
            T item = responseToObject(itemObj, clazz);
            list.add(item);
        }

        return list;
    }
}
