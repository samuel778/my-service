package com.micro.utils.captcha;

import lombok.Data;
/**
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
@Data
public class ImageCode {
    private String imageToken;
    private String imageBase64;
    private String code;
}
