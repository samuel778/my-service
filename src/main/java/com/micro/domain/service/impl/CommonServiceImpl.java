package com.micro.domain.service.impl;

import com.micro.core.config.jwt.JwtTokenUtils;
import com.micro.domain.service.CommonService;
import com.micro.utils.captcha.ImageCode;
import com.micro.utils.captcha.ImageCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
@Service
@Slf4j
public class CommonServiceImpl implements CommonService {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public ImageCode imageCodeCreate() {
        return ImageCodeUtils.create(jwtTokenUtils);
    }

    @Override
    public boolean imageCodeValidate(String imageToken, String userCodeValue) throws RuntimeException {
        return ImageCodeUtils.validate(imageToken, userCodeValue, jwtTokenUtils);
    }
}
