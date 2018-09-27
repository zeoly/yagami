//package com.yahacode.yagami.document;
//
//import com.yahacode.yagami.base.BizfwServiceException;
//import com.yahacode.yagami.document.dao.RoleFolderAuthorityDao;
//import com.yahacode.yagami.document.model.RoleFolderAuthority;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @author zengyongli
// */
//public class RoleFolderAuthorityDaoTest extends BaseTest {
//
//    private RoleFolderAuthorityDao roleFolderAuthorityDao;
//
//    @Test
//    public void testGetAuthority() throws BizfwServiceException {
//        String roleId = "8a80808659171f020159176299880002";
//        String folderId = "8a808086594b252e01594b39a1630002";
//        RoleFolderAuthority roleFolderAuthority = roleFolderAuthorityDao.getAuthority(roleId, folderId);
//        Assert.assertNotNull(roleFolderAuthority);
//    }
//
//    @Autowired
//    public void setRoleFolderAuthorityDao(RoleFolderAuthorityDao roleFolderAuthorityDao) {
//        this.roleFolderAuthorityDao = roleFolderAuthorityDao;
//    }
//}
