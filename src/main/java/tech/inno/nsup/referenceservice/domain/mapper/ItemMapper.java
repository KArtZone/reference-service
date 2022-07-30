package tech.inno.nsup.referenceservice.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import tech.inno.nsup.referenceservice.domain.dto.AttributeValueDto;
import tech.inno.nsup.referenceservice.domain.dto.ItemDto;
import tech.inno.nsup.referenceservice.domain.entity.Item;
import tech.inno.nsup.referenceservice.domain.entity.ItemAttribute;
import tech.inno.nsup.referenceservice.utils.RegistryUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Преобразования для записи справочника.
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@Mapper(componentModel = "spring", uses = RegistryUtils.class)
public abstract class ItemMapper {

    @Autowired
    protected RegistryUtils utils;

    public abstract ItemDto toDto(Item item);

    public List<AttributeValueDto> toDto(Map<String, ItemAttribute> attributes) {
        return attributes.values().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Mappings({
            @Mapping(target = "key", source = "attribute.attributeKey"),
            @Mapping(target = "value", expression = "java(utils.getSimpleAttributeValueAsString(attribute).orElse(null))"),
            @Mapping(target = "details", expression = "java(utils.getAttributeValue(attribute))")
    })
    public abstract AttributeValueDto toDto(ItemAttribute attribute);
}
