package org.gluu.ob.service;

import lombok.extern.slf4j.Slf4j;
import org.gluu.ob.domain.entity.ConsentEntity;
import org.gluu.ob.domain.enums.ConsentStatusEnum;
import org.gluu.ob.domain.repository.ConsentRepository;
import org.gluu.ob.exception.InternalError;
import org.gluu.ob.rest.model.Consent;
import org.gluu.ob.rest.model.PostConsent;
import org.gluu.ob.util.ApiConstants;
import org.gluu.ob.util.Utils;
import org.gluu.ob.util.converters.ConsentConverter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class ConsentService {

    @Inject
    ConsentRepository consentRepository;

    public Consent create(PostConsent newConsent) throws InternalError {
        try {
            final Date now = new Date();

            ConsentEntity consentEntity = new ConsentEntity();
            consentEntity.setCreationDatetime(now);
            consentEntity.setExpirationDatetime(Utils.parseDate(newConsent.getExpirationDatetime(), ApiConstants.DATE_FORMAT));
            consentEntity.setPermissions(newConsent.getPermissions().toArray(new String[0]));
            consentEntity.setProfile(newConsent.getProfile());
            consentEntity.setStatus(ConsentStatusEnum.AWAITING_AUTHORISATION);
            consentEntity.setStatusUpdateDatetime(now);
            consentEntity.setTransactionFromDatetime(Utils.parseDate(newConsent.getTransactionFromDatetime(), ApiConstants.DATE_FORMAT));
            consentEntity.setTransactionToDatetime(Utils.parseDate(newConsent.getTransactionFromDatetime(), ApiConstants.DATE_FORMAT));

            consentEntity = consentRepository.save(consentEntity);

            log.info("New consent created, data: {}", consentEntity);

            return ConsentConverter.toObject(consentEntity);
        } catch (Exception e) {
            log.error("Error registering a new Consent in the DB", e);
            throw new InternalError("Problems registering the new consent entity.");
        }
    }

    public void revokeConsent(String consentId) throws InternalError {
        Long id = Long.parseLong(consentId.replace(ApiConstants.CONSENT_ID_PREFIX, ""));
        Optional<ConsentEntity> consentEntityOptional = consentRepository.findById(id);
        if (consentEntityOptional.isPresent()) {
            ConsentEntity consent = consentEntityOptional.get();
            if (consent.getStatus() == ConsentStatusEnum.REVOKED) {
                throw new InternalError("Consent is already revoked.");
            }
            if (consent.getStatus() != ConsentStatusEnum.AUTHORISED &&
                    consent.getStatus() != ConsentStatusEnum.AWAITING_AUTHORISATION) {
                throw new InternalError("Consent can not be revoked.");
            }
            consent.setStatus(ConsentStatusEnum.REVOKED);
            consent.setStatusUpdateDatetime(new Date());
            consentRepository.save(consent);

            log.info("Consent has been revoked, id: {}", consentId);
        } else {
            throw new InternalError("Consent not found.");
        }
    }

    public Consent getConsent(String consentId) {
        Long id = Long.parseLong(consentId.replace(ApiConstants.CONSENT_ID_PREFIX, ""));
        Optional<ConsentEntity> consentEntityOptional = consentRepository.findById(id);
        return consentEntityOptional.map(ConsentConverter::toObject).orElse(null);
    }

}
