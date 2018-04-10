package com.yahacode.yagami.document.repository;

import com.yahacode.yagami.document.model.RoleFolderAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-04-03
 */
public interface RoleFolderAuthorityRepository extends JpaRepository<RoleFolderAuthority, String> {

    void deleteByFolderId(String folderId);

    List<RoleFolderAuthority> findAllByFolderId(String folderId);
}
