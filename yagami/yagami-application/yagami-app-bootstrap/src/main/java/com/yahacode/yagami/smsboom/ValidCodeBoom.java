package com.yahacode.yagami.smsboom;

import com.yahacode.yagami.base.common.LogUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author zengyongli 2018-12-12
 */
@Component
public class ValidCodeBoom {

    @Autowired
    private RestTemplate restTemplate;

    public void boom(String phone) throws JSONException {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("phone", "18126190312");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(map, getHeaders());
        String response = restTemplate.postForEntity("http://lbsyun.baidu.com/apiconsole/register/sendIdentifyCode",
                entity, String.class).getBody();
        LogUtils.info("response:" + response);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
//        headers.add(":authority:", "h5.ele.me");
//        headers.add(":method:", " POST");
//        headers.add(":path:", "/restapi/eus/login/mobile_send_code");
//        headers.add(":scheme:", "https");
        headers.add("Accept", "*/*");
//        headers.add("Content-Encoding", "UTF-8");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
