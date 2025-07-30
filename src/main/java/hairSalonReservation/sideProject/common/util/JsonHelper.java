package hairSalonReservation.sideProject.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JsonHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object){
        try{
            return  objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            throw new RuntimeException("json 직렬화 중 알 수 없는 오류 발생");
        }
    }

    public static <T> T fromJson(String json, Class<T> tClass){
        try{
            return objectMapper.readValue(json, tClass);
        }catch (JsonProcessingException e){
            throw new RuntimeException("json 역직렬화 중 알 수 없는 오류 발생");
        }
    }

    public static <T> List<T> fromJsonToList(String json, TypeReference<List<T>> typeReference) {

        if(json == null || json.isEmpty()){return new ArrayList<>();}

        try{
            return objectMapper.readValue(json, typeReference);
        }catch (JsonProcessingException e){
            log.error("원본 에러 이유 : {}", e.getMessage());
            throw new RuntimeException("json 역직렬화 중 알 수 없는 오류 발생");
        }
    }
}
