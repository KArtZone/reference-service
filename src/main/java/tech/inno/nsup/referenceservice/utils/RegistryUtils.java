package tech.inno.nsup.referenceservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.inno.nsup.referenceservice.domain.ReferenceEntry;
import tech.inno.nsup.referenceservice.domain.dto.AttributeMultipleValueDto;
import tech.inno.nsup.referenceservice.domain.entity.AttributeTextValue;
import tech.inno.nsup.referenceservice.domain.entity.ItemAttribute;
import tech.inno.nsup.referenceservice.domain.enums.AttributeType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * Часто используемые функции связанные с работой справочника.
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@Slf4j
@Component
public class RegistryUtils {

    private static final ZoneId zoneId = ZoneId.systemDefault();
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    /**
     * Используется ли для значения записи справочник или нет.
     *
     * @param attribute значение атрибута записи
     *
     * @return факт использования справочника
     */
    public boolean isReference(ItemAttribute attribute) {
        return AttributeType.ENUM.equals(attribute.getType()) || AttributeType.DICTIONARY.equals(attribute.getType());
    }

    /**
     * Получить строковое представления для простых атрибутов (не являются атрибутами со справочником).
     *
     * @param attribute значение атрибута
     * @return строковое представление значения атрибута
     */
    public Optional<String> getSimpleAttributeValueAsString(ItemAttribute attribute) {
        if (!isReference(attribute)) {
            return getAttributeValue(attribute, null).map(String::valueOf);
        }
        return Optional.empty();
    }

    /**
     * Получить строковое представления для простых атрибутов (не являются атрибутами со справочником).
     *
     * @param attribute значение атрибута
     * @return строковое представление значения атрибута
     */
    public List<AttributeMultipleValueDto> getAttributeValue(ItemAttribute attribute) {
        if (isReference(attribute)) {
            return attribute.getMultipleValues().stream()
                     .map(v -> new AttributeMultipleValueDto(v.getId(), v.getValue()))
                     .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Позволяет получить значение атрибута записи справочника.
     * Если значение не установленно Optional будет пустой. Если значение есть, то оно вернется как Object,
     * так как типы значений атрибуты могут быть разные.
     *
     * @param attribute значение атрибута
     * @param transform функция, которая может изменить возвращаемое значение (используется для справочных атрибутов)
     * @return значение атрибута
     */
    public Optional<Object> getAttributeValue(ItemAttribute attribute, BiFunction<String, String, Object> transform) {
        if (isNull(attribute) || isNull(attribute.getType())) {
            return Optional.empty();
        }
        switch (attribute.getType()) {
            case STRING:
                return Optional.ofNullable(attribute.getStringValue());
            case TEXT:
                return Optional.ofNullable(attribute.getTextValue())
                               .map(AttributeTextValue::getValue);
            case DATE:
                return Optional.ofNullable(attribute.getNumberValue())
                               .map(this::getLocalDate)
                               .map(dateFormat::format);
            case DATETIME:
                return Optional.ofNullable(attribute.getNumberValue())
                               .map(this::getLocalDateTime)
                               .map(dateTimeFormat::format);
            case YEAR:
            case INTEGER:
                return Optional.ofNullable(attribute.getNumberValue())
                               .map(BigDecimal::intValue);
            case NUMBER:
                return Optional.ofNullable(attribute.getNumberValue());
            case BOOLEAN:
                return Optional.ofNullable(attribute.getNumberValue())
                               .map(value -> !value.equals(BigDecimal.ZERO));
            case ENUM:
            case DICTIONARY:
                return Optional.ofNullable(attribute.getMultipleValues())
                               .map(values -> values.stream()
                                                    .map(value -> {
                                                        final String id = value.getValue();
                                                        return new ReferenceEntry(id, transform.apply(attribute.getAttributeKey(), id));
                                                    }).collect(Collectors.toList()));
            default:
                return Optional.empty();
        }
    }

    /**
     * Получить дату по его числовому представлению.
     *
     * @param number числовое представление даты
     * @return объект даты
     */
    public LocalDate getLocalDate(BigDecimal number) {
        return Instant.ofEpochSecond(number.longValue()).atZone(zoneId).toLocalDate();
    }

    /**
     * Получить дату и время по его числовому представлению.
     *
     * @param number числовое представление даты времени
     * @return объект даты времени
     */
    public LocalDateTime getLocalDateTime(BigDecimal number) {
        return Instant.ofEpochSecond(number.longValue()).atZone(zoneId).toLocalDateTime();
    }

}
