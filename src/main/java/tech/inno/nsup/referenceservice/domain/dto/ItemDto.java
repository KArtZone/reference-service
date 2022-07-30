package tech.inno.nsup.referenceservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author IVIvanov
 * @since 22.07.2022
 */
@Data
@Schema(description = "Структура записи справочника")
public class ItemDto {

    @JsonProperty("id")
    @Schema(description = "Идентификатор справочника", required = true, nullable = true)
    UUID id;

    @JsonProperty("attributes")
    @Schema(description = "Значения атрибутов записи", required = true)
    List<AttributeValueDto> attributes;
}
