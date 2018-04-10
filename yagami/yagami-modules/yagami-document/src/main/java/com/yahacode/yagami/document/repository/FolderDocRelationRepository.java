package com.yahacode.yagami.document.repository;

import com.yahacode.yagami.document.model.FolderDocRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-04-02
 */
public interface FolderDocRelationRepository extends JpaRepository<FolderDocRelation, String> {

    List<FolderDocRelation> findByFolderId(String folderId);

    void deleteByDocumentId(String documentId);

    long countByFolderId(String folderId);
}
