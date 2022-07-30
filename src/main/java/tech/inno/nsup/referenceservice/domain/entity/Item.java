package tech.inno.nsup.referenceservice.domain.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;
import java.util.UUID;


/**
 * Сущность для хранения записей справочника.
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@Data
@Entity
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    @ToString.Include
    @Column(name = "id")
    private UUID id;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @MapKey(name = "attributeKey")
    private Map<String, ItemAttribute> attributes;
}
