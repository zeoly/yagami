package com.yahacode.yagami.smsboom;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengyongli 2018-12-12
 */
@RestController
public class BoomController {

    @Autowired
    private ValidCodeBoom validCodeBoom;

    @GetMapping("/boom/{phone}")
    public void boom(@PathVariable String phone) throws JSONException {
        validCodeBoom.boom(phone);
    }
}
