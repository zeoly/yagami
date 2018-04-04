package com.yahacode.yagami.claim.model;

import com.yahacode.yagami.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bf_claim")
public class Claim extends BaseModel {

    /**
     * self cancel case
     */
    public static final String STATUS_INVALID = "0";

    /**
     * init, not uploading documents
     */
    public static final String STATUS_INIT = "1";

    /**
     * case registered, to be reviewed
     */
    public static final String STATUS_REGISTERED = "2";

    /**
     * accept the case
     */
    public static final String STATUS_ACCEPT = "3";

    /**
     * reject the case
     */
    public static final String STATUS_REJECT = "4";

    public static final String COLUMN_STATUS = "status";

    @Id
    @Column(name = "id_bf_claim")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfClaim;

    @Column(name = "reporter_name")
    private String reporterName;

    @Column(name = "reporter_phone")
    private String reporterPhone;

    @Column(name = "auditor")
    private String auditor;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private String status;

    @Column(name = "doc_group_no")
    private String documentGroupNo;

    public Claim(String peopleCode) {
        super(peopleCode);
    }

    @Override
    public String getId() {
        return idBfClaim;
    }

}
