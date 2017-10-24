package com.yahacode.yagami.document.action;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.service.DocumentService;
import com.yahacode.yagami.document.utils.FileUtils;
import com.yahacode.yagami.pd.model.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/document")
public class DocumentAction extends BaseAction {

    Logger logger = LoggerFactory.getLogger(getClass());

    private DocumentService documentService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH)
    public void modifyDocument(@RequestBody Document document) throws BizfwServiceException {
        People people = getLoginPeople();
        document.update(people.getCode());
        documentService.modifyDocument(document);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{documentId}")
    public void deleteDocument(@PathVariable("documentId") String documentId) throws BizfwServiceException {
        People people = getLoginPeople();
        Document document = documentService.queryById(documentId);
        document.update(people.getCode());
        documentService.deleteDocument(document);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{documentId}")
    public void downloadDocument(HttpServletRequest request, HttpServletResponse response, @PathVariable
            ("documentId") String documentId) throws BizfwServiceException, IOException {
        try {
            Document document = documentService.queryById(documentId);
            String name = FileUtils.getLocalStorage() + document.getUrl();

            File file = new File(name);
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(document.getName()
                    .getBytes("utf-8"), "iso8859-1"));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            InputStream in = new FileInputStream(name);
            OutputStream out = response.getOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
