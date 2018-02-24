package com.yahacode.yagami.document.action;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.service.DocumentGroupService;
import com.yahacode.yagami.document.service.DocumentService;
import com.yahacode.yagami.document.utils.FileUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentAction extends BaseAction {

    private DocumentService documentService;

    private DocumentGroupService documentGroupService;

    private FileUtils fileUtils;

    @ApiOperation(value = "修改文件")
    @ApiImplicitParam(name = "document", value = "文件", required = true, dataTypeClass = Document.class)
    @PatchMapping("/{documentId}")
    public void modifyDocument(@RequestBody Document document) throws BizfwServiceException {
        documentService.modifyDocument(document);
    }

    @ApiOperation(value = "更新文件版本")
    @ApiImplicitParams({@ApiImplicitParam(name = "documentId", value = "主键", required = true, dataTypeClass = String
            .class), @ApiImplicitParam(name = "file", value = "新文件", required = true, dataTypeClass = MultipartFile
            .class)})
    @PostMapping("/{documentId}")
    public void updateDocument(@RequestBody MultipartFile file, @PathVariable("documentId") String documentId) throws
            BizfwServiceException {
        Document document = documentService.saveDocument(file);
        documentService.updateDocument(document, documentId);
    }

    @ApiOperation(value = "下载文件")
    @ApiImplicitParam(name = "documentId", value = "主键", required = true, dataTypeClass = String.class)
    @GetMapping("/{documentId}")
    public void downloadDocument(HttpServletRequest request, HttpServletResponse response, @PathVariable
            ("documentId") String documentId) throws BizfwServiceException, IOException {
        try {
            Document document = documentService.queryById(documentId);
            String name = fileUtils.getLocalStorage() + document.getUrl();

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

    @ApiOperation(value = "在文档组中添加文件")
    @ApiImplicitParams({@ApiImplicitParam(name = "groupNo", value = "文档组编号", required = true, dataTypeClass = String
            .class), @ApiImplicitParam(name = "file", value = "新文件", required = true, dataTypeClass = MultipartFile
            .class)})
    @PostMapping("/group/{groupNo}")
    public String addDocument(MultipartFile file, @PathVariable("groupNo") String groupNo) throws
            BizfwServiceException {
        return documentGroupService.addDocument(file, groupNo);
    }

    @ApiOperation(value = "查询文档组下所有文件")
    @ApiImplicitParam(name = "groupNo", value = "文档组编号", required = true, dataTypeClass = String.class)
    @GetMapping("/group/{groupNo}")
    public List<Document> getByDocumentGroupNo(@PathVariable("groupNo") String groupNo) throws BizfwServiceException {
        return documentGroupService.getByGroupNo(groupNo);
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setDocumentGroupService(DocumentGroupService documentGroupService) {
        this.documentGroupService = documentGroupService;
    }

    @Autowired
    public void setFileUtils(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }
}
