package com.yahacode.yagami.claim;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClaimAction extends BaseAction {

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
        People loginPeople = getLoginPeople();
        logger.info("claim in");
    }

    @Autowired
    public void setClaimService(ClaimService claimService) {
        this.claimService = claimService;
    }
}
