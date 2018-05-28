package com.yahacode.yagami.claim.repository;

import com.yahacode.yagami.claim.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-04-04
 */
public interface ClaimRepository extends JpaRepository<Claim, String> {

    List<Claim> findAllByStatusIn(String... status);
}
