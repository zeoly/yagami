package com.yahacode.yagami.claim.service.impl;

import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.claim.model.Claim;
import com.yahacode.yagami.claim.repository.ClaimRepository;
import com.yahacode.yagami.claim.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zengyongli
 */
@Service
public class ClaimServiceImpl extends BaseServiceImpl<Claim> implements ClaimService {

    private ClaimRepository claimRepository;

    @Override
    public Claim initClaim(Claim claim) throws BizfwServiceException {
        claim.setDocumentGroupNo(StringUtils.generateUUID());
        claim.setStatus(Claim.STATUS_INIT);
        save(claim);
        return claim;
    }

    @Override
    public List<Claim> getClaimReviewList() throws BizfwServiceException {
        return claimRepository.findAllByStatusIn(Claim.STATUS_INIT);
    }

    @Override
    public List<Claim> getClaimReportList() throws BizfwServiceException {
        return claimRepository.findAllByStatusIn(Claim.STATUS_INIT, Claim.STATUS_ACCEPT);
    }

    @Override
    public void acceptClaim(Claim claim) throws BizfwServiceException {
        claim.setStatus(Claim.STATUS_ACCEPT);
        claim.setAuditor(claim.getUpdateBy());
        update(claim);
    }

    @Override
    public void rejectClaim(Claim claim) throws BizfwServiceException {
        claim.setStatus(Claim.STATUS_REJECT);
        claim.setAuditor(claim.getUpdateBy());
        update(claim);
    }

    @Autowired
    public void setClaimRepository(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public JpaRepository<Claim, String> getBaseRepository() {
        return null;
    }
}
