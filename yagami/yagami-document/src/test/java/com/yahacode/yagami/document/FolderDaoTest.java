package com.yahacode.yagami.document;

import com.yahacode.yagami.document.dao.FolderDao;
import com.yahacode.yagami.document.model.Folder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zengyongli
 */
public class FolderDaoTest extends BaseTest {

    private FolderDao folderDao;

    @Test
    public void testGetAuthorizedFolderByRole() {
        List<Folder> list = folderDao.getAuthorizedFolderByRole("8a8080875825fe41015825fe6ad40000");
        Assert.assertEquals(list.size(), 2);
    }

    @Autowired
    public void setFolderDao(FolderDao folderDao) {
        this.folderDao = folderDao;
    }
}
