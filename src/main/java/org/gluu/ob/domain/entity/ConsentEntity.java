package org.gluu.ob.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gluu.ob.domain.enums.ConsentStatusEnum;
import org.gluu.ob.util.converters.ArrayToJsonConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "consent")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class ConsentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String profile;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "user_id")
    private String userId;

    @Column
    @Convert(converter = ArrayToJsonConverter.class)
    private String[] accounts;

    @Column(name = "creation_datetime")
    @NotNull
    private Date creationDatetime;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private ConsentStatusEnum status;

    @Column(name = "status_update_datetime")
    @NotNull
    private Date statusUpdateDatetime;

    @Column
    @Convert(converter = ArrayToJsonConverter.class)
    private String[] permissions;

    @Column(name = "expiration_datetime")
    private Date expirationDatetime;

    @Column(name = "transaction_from_datetime")
    private Date transactionFromDatetime;

    @Column(name = "transaction_to_datetime")
    private Date transactionToDatetime;

}
