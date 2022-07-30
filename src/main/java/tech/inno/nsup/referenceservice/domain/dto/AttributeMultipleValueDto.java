package tech.inno.nsup.referenceservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author IVIvanov
 * @since 22.07.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Детали значения атрибута записи справочника")
public class AttributeMultipleValueDto {

    @JsonProperty("id")
    @Schema(description = "Идентификатор значения атрибута", required = true)
    UUID id;

    @JsonProperty("value")
    @Schema(description = "Значение атрибута")
    String value;

}
