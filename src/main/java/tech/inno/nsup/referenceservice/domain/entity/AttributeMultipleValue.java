package tech.inno.nsup.referenceservice.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tech.inno.nsup.referenceservice.domain.enums.AttributeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Сущность для хранения значения атрибута на список значений. Может использоваться для типов атрибута
 * {@link AttributeType#DICTIONARY} и {@link AttributeType#ENUM}
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@Data
@Entity
@Table(name = "attribute_multiple_value")
@EqualsAndHashCode(of = {"attribute.id", "value"})
public class AttributeMultipleValue {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    private ItemAttribute attribute;

    @ToString.Exclude
    @Column(name = "value")
    private String value;
}
