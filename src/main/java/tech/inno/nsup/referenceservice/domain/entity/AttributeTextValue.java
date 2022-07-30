package tech.inno.nsup.referenceservice.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tech.inno.nsup.referenceservice.domain.enums.AttributeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Сущность для хранения значений атрибутов на тип {@link AttributeType#TEXT} который является текстом
 * с неограниченным числом символов.
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@Data
@Entity
@Table(name = "attribute_text_value")
public class AttributeTextValue {

    @Id
    @Column(name = "attribute_id")
    private UUID id;

    @MapsId
    @OneToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "attribute_id")
    private ItemAttribute attribute;

    @ToString.Exclude
    @Column(name = "value")
    private String value;
}
