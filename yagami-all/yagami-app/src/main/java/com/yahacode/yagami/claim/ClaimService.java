package com.yahacode.yagami.claim;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;

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
}
