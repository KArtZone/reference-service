package tech.inno.nsup.referenceservice.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Утилитарный класс по работе с JSON данными
 */
public class JsonUtils {

    private static ObjectMapper objectMapper;

    /**
     * Метод читает json файл по его расположению внутри jar архива и создает на его основе объект-заглушку Dto
     *
     * @param file   путь до json файла
     * @param tClass Тип класса Dto, который будет создан
     * @param <T>    Параметризованный тип
     * @return Готовый класс Dto
     */
    public static <T> T createDummyDto(String file, Class<T> tClass) {
        try (InputStream inputStream = new ClassPathResource(file).getInputStream()) {
            return getObjectMapper().readValue(inputStream, tClass);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Can't parse json", e);
        } catch (IOException e) {
            throw new IllegalStateException("Can't process classpath resource", e);
        }
    }

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = Jackson2ObjectMapperBuilder.json()
                    .modules(new JavaTimeModule().addDeserializer(LocalDateTime.class,
                            new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .build();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        }
        return objectMapper;
    }
}
