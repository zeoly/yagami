package com.yahacode.yagami.document.action;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.service.DocumentService;
import com.yahacode.yagami.document.utils.FileUtils;
import com.yahacode.yagami.pd.model.People;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    private Logger logger = LoggerFactory.getLogger(getClass());

    private DocumentService documentService;

    @ApiOperation(value = "修改文件")
    @ApiImplicitParam(name = "document", value = "文件", required = true, dataTypeClass = Document.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH)
    public void modifyDocument(@RequestBody Document document) throws BizfwServiceException {
        People people = getLoginPeople();
        document.update(people.getCode());
        documentService.modifyDocument(document);
    }

    @ApiOperation(value = "更新文件版本")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{documentId}")
    public void updateDocument(@RequestBody MultipartFile file, @PathVariable("documentId") String documentId) throws
            BizfwServiceException {
        Document document = documentService.saveDocument(file, getLoginPeople().getCode());
        documentService.updateDocument(document, documentId);
    }

    @ApiOperation(value = "下载文件")
    @ApiImplicitParam(name = "documentId", value = "主键", required = true, dataTypeClass = String.class)
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
