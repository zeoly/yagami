package com.yahacode.yagami.claim.action;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.claim.model.Claim;
import com.yahacode.yagami.claim.service.ClaimService;
import com.yahacode.yagami.pd.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zengyongli
 */
@RestController
@RequestMapping("/claim")
public class ClaimAction extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ClaimService claimService;

    @RequestMapping(method = RequestMethod.POST)
    public Claim initClaim(@RequestBody Claim claim) throws BizfwServiceException {
        claim.init(getLoginPeople().getCode());
        return claimService.initClaim(claim);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public void addClaim(HttpServletRequest request) throws BizfwServiceException {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        Person loginPeople = getLoginPeople();
        logger.info("claim in");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/review")
    public List<Claim> getClaimReviewList() throws BizfwServiceException {
        return claimService.getClaimReviewList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/report")
    public List<Claim> getClaimReportList() throws BizfwServiceException {
        return claimService.getClaimReportList();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{claimId}")
    public void accept(@PathVariable("claimId") String claimId, String comment) throws BizfwServiceException {
        Claim claim = claimService.queryById(claimId);
        claim.update(getLoginPeople().getCode());
        claim.setComment(comment);
        claimService.acceptClaim(claim);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{claimId}")
    public void reject(@PathVariable("claimId") String claimId, String comment) throws BizfwServiceException {
        Claim claim = claimService.queryById(claimId);
        claim.update(getLoginPeople().getCode());
        claim.setComment(comment);
        claimService.rejectClaim(claim);
    }

    @Autowired
    public void setClaimService(ClaimService claimService) {
        this.claimService = claimService;
    }
}
