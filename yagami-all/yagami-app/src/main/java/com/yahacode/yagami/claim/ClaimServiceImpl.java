package com.yahacode.yagami.claim;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zengyongli
 */
@Service
public class ClaimServiceImpl extends BaseServiceImpl<Claim> implements ClaimService {

    private ClaimDao claimDao;

    @Override
    public BaseDao<Claim> getBaseDao() {
        return claimDao;
    }

    @Autowired
    public void setClaimDao(ClaimDao claimDao) {
        this.claimDao = claimDao;
    }
}
