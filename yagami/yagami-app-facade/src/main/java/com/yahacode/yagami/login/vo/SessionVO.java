package com.yahacode.yagami.login.vo;


import com.yahacode.yagami.pd.model.People;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zengyongli 2018-03-01
 */
@Data
@Builder
@AllArgsConstructor
public class SessionVO implements Serializable {

    public People people;

    public String token;

}
