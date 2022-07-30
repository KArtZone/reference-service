package tech.inno.nsup.referenceservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author IVIvanov
 * @since 22.07.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Значение атрибута записи справочника")
public class AttributeValueDto {

    @JsonProperty("attrKey")
    @Schema(description = "Ключ атрибута", required = true)
    String key;

    @JsonProperty("value")
    @Schema(description = "Значение атрибута")
    String value;

    @JsonProperty("details")
    @Schema(description = "Список деталей значений атрибута")
    List<AttributeMultipleValueDto> details;
}
