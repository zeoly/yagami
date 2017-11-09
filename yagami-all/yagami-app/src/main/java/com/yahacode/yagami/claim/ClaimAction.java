package com.yahacode.yagami.claim;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zengyongli
 */
@RestController
@RequestMapping("/claim")
public class ClaimAction extends BaseAction {

    private ClaimService claimService;

    @RequestMapping(method = RequestMethod.POST)
    public void addClaim(@RequestBody List<MultipartFile> files, @RequestBody Claim claim) throws
            BizfwServiceException {
        People loginPeople = getLoginPeople();
    }

    @Autowired
    public void setClaimService(ClaimService claimService) {
        this.claimService = claimService;
    }
}
