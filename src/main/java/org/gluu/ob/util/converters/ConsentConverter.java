package org.gluu.ob.util.converters;

import org.gluu.ob.persistence.entity.ConsentEntity;
import org.gluu.ob.rest.model.Consent;
import org.gluu.ob.util.ApiConstants;
import org.gluu.ob.util.Utils;

import java.util.Arrays;

public class ConsentConverter {

    public static Consent toObject(ConsentEntity consentEntity) {
        Consent consent = new Consent();
        consent.setAccounts(Arrays.asList(consentEntity.getAccounts()));
        consent.setClientId(consentEntity.getClientId());
        consent.setConsentId(String.format("%s%d", ApiConstants.CONSENT_ID_PREFIX, consentEntity.getId()));
        consent.setCreationDatetime(Utils.formatDate(consentEntity.getCreationDatetime(), ApiConstants.DATE_FORMAT));
        consent.setExpirationDatetime(Utils.formatDate(consentEntity.getExpirationDatetime(), ApiConstants.DATE_FORMAT));
        consent.setTransactionFromDatetime(Utils.formatDate(consentEntity.getTransactionFromDatetime(), ApiConstants.DATE_FORMAT));
        consent.setTransactionToDatetime(Utils.formatDate(consentEntity.getTransactionToDatetime(), ApiConstants.DATE_FORMAT));
        consent.setPermissions(Arrays.asList(consentEntity.getPermissions()));
        consent.setStatus(consentEntity.getStatus());
        consent.setUserId(consentEntity.getUserId());
        return consent;
    }

}
