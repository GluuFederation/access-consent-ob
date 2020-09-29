package org.gluu.ob.domain.repository;

import org.gluu.ob.domain.entity.ConsentEntity;
import org.gluu.ob.domain.enums.ConsentStatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ConsentRepository extends CrudRepository<ConsentEntity, Long> {

    List<ConsentEntity> findConsentEntitiesByStatusAndExpirationDatetimeNotNullAndExpirationDatetimeBefore(
            ConsentStatusEnum status, Date expiration);

}
