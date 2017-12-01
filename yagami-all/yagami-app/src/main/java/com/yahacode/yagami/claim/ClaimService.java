package com.yahacode.yagami.claim;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;

import java.util.List;

/**
 * @author zengyongli
 */
public interface ClaimService extends BaseService<Claim> {

    /**
     * init claim for uploading
     *
     * @param claim
     *         entity
     * @return inited entity
     * @throws BizfwServiceException
     *         framework exception
     */
    Claim initClaim(Claim claim) throws BizfwServiceException;

    /**
     * get the claim list to be reviewed
     *
     * @return claim list
     * @throws BizfwServiceException
     *         framework exception
     */
    List<Claim> getClaimReviewList() throws BizfwServiceException;

    /**
     * get the claim list which has been reported
     *
     * @return claim list
     * @throws BizfwServiceException
     *         framework exception
     */
    List<Claim> getClaimReportList() throws BizfwServiceException;

    /**
     * accept the claim biz
     *
     * @param claim
     *         business entity
     * @throws BizfwServiceException
     *         framework exception
     */
    void acceptClaim(Claim claim) throws BizfwServiceException;

    /**
     * reject the claim biz
     *
     * @param claim
     *         business entity
     * @throws BizfwServiceException
     *         framework exception
     */
    void rejectClaim(Claim claim) throws BizfwServiceException;
}
