package com.yahacode.yagami.document.action;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.service.DocumentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zengyongli
 */
@RestController
@RequestMapping("/docgroup")
public class DocumentGroupAction extends BaseAction {

    private DocumentGroupService documentGroupService;

    @RequestMapping(method = RequestMethod.POST, value = "/{groupId}")
    public String addDocument(MultipartFile file, @PathVariable("groupId") String groupId) throws
            BizfwServiceException {
        return documentGroupService.addDocument(file, groupId, getLoginPeople().getCode());
    }

    @Autowired
    public void setDocumentGroupService(DocumentGroupService documentGroupService) {
        this.documentGroupService = documentGroupService;
    }
}
