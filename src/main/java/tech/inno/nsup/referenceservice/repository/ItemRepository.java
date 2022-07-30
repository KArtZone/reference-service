package tech.inno.nsup.referenceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.inno.nsup.referenceservice.domain.entity.Item;

import java.util.UUID;

/**
 * Репозиторий для взаимодействия с записями справочника.
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

}
