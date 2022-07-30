package tech.inno.nsup.referenceservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.inno.nsup.referenceservice.domain.dto.ItemDto;
import tech.inno.nsup.referenceservice.domain.entity.AttributeMultipleValue;
import tech.inno.nsup.referenceservice.domain.entity.Item;
import tech.inno.nsup.referenceservice.domain.entity.ItemAttribute;
import tech.inno.nsup.referenceservice.domain.enums.AttributeType;
import tech.inno.nsup.referenceservice.domain.exception.ImmutableAttributeValueException;
import tech.inno.nsup.referenceservice.domain.exception.ReferenceNotFoundException;
import tech.inno.nsup.referenceservice.domain.mapper.ItemMapper;
import tech.inno.nsup.referenceservice.repository.ItemRepository;
import tech.inno.nsup.referenceservice.service.ReferenceService;

import java.util.Map;
import java.util.UUID;

/**
 * Сервис взаимодействия с сущностью справочника.
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@Slf4j
@Service
@AllArgsConstructor
public class ReferenceServiceImpl implements ReferenceService {

    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    @Override
    public ItemDto get(UUID id) {
        final Item item = itemRepository.findById(id).orElseThrow(ReferenceNotFoundException::new);
        final ItemDto itemDto = mapper.toDto(item);
        log.info("Send back response data for item: {}", item.getId());
        return itemDto;
    }

    @Override
    public ItemDto addAttributeValue(UUID itemId, UUID attributeId, String value) {
        final Item item = itemRepository.findById(itemId).orElseThrow(ReferenceNotFoundException::new);

        ItemAttribute a = item.getAttributes().values().stream()
                                          .filter(e -> attributeId.equals(e.getId()))
                                          .findFirst()
                                          .orElseThrow(ReferenceNotFoundException::new);

        if (!AttributeType.DICTIONARY.equals(a.getType())) {
            throw new ImmutableAttributeValueException();
        }

        AttributeMultipleValue v = new AttributeMultipleValue();
        v.setValue(value);
        v.setAttribute(a);
        a.getMultipleValues().add(v);

        final Item saved = itemRepository.save(item);
        final ItemDto itemDto = mapper.toDto(saved);
        log.info("Send back response data for item: {}", saved.getId());
        return itemDto;
    }
}
