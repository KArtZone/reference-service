package tech.inno.nsup.referenceservice.service;

import tech.inno.nsup.referenceservice.domain.dto.ItemDto;

import java.util.UUID;

/**
 * Сервис по работе со справочником.
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
public interface ReferenceService {

    /**
     * Функция получения информации по конкретному справочнику.
     *
     * @param id идентификатор справочника
     *
     * @return справочник
     */
    ItemDto get(UUID id);

    /**
     * Создание новой записи справочника.
     *
     * @param itemId идентификатор справочника
     * @param attributeId идентификатор атрибута справочника
     * @param value добавляемое значение атрибута справочника
     *
     * @return справочник
     */
    ItemDto addAttributeValue(UUID itemId, UUID attributeId, String value);

}
