package hairSalonReservation.sideProject.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonHelper {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private JsonHelper(){}



    public static String toJson(Object object){
        try{
            return  objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            log.error("처리중 오류 발생",e);
            throw new RuntimeException("json 직렬화 중 알 수 없는 오류 발생");
        }
    }

    public static <T> T fromJson(String json, Class<T> tClass){


        try{
            return objectMapper.readValue(json, tClass);
        }catch (JsonProcessingException e){
            log.error("처리중 오류 발생",e);
            throw new RuntimeException("json 역직렬화 중 알 수 없는 오류 발생");
        }
    }

    public static <T> List<T> fromJsonToList(String json, TypeReference<List<T>> typeReference) {

        if(json == null || json.isEmpty()){return new ArrayList<>();}

        try{
            return objectMapper.readValue(json, typeReference);
        }catch (JsonProcessingException e){
            log.error("처리중 오류 발생",e);
            throw new RuntimeException("json 역직렬화 중 알 수 없는 오류 발생");
        }
    }
}
