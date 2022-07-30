package tech.inno.nsup.referenceservice.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tech.inno.nsup.referenceservice.domain.enums.AttributeType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * Сущность для хранения привязки значения атрибута (по разным типам) к записи справочника.
 * Значение атрибута записи справочника может быть разного типа, тип задается множеством {@link AttributeType}.
 * Атрибут содержит колонки для хранения строкового значения фиксированной длины и для хранения числовой информации.
 * Для хранения текстов не фиксированной длины существует связь атрибута к сущности {@link AttributeTextValue},
 * а для значений множественного выбора которые указывают на справочник используется связь к сущности
 * {@link AttributeMultipleValue}.
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@Data
@Entity
@Table(name = "item_attribute")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = {
        "id", "item.id", "attributeKey", "type", "stringValue",
        "numberValue", "textValue.value", "multipleValues"})
public class ItemAttribute {

    @Id
    @GeneratedValue
    @ToString.Include
    @Column(name = "id")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @ToString.Include
    @Column(name = "attribute_key")
    private String attributeKey;

    @Column(name = "attribute_type")
    @Enumerated(EnumType.STRING)
    private AttributeType type;

    @Column(name = "string_value")
    private String stringValue;

    @Column(name = "number_value")
    private BigDecimal numberValue;

    @OneToOne(mappedBy = "attribute", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AttributeTextValue textValue;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<AttributeMultipleValue> multipleValues;
}
