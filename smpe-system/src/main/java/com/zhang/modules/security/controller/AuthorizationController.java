package com.zhang.modules.security.controller;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import com.zhang.annotation.rest.AnonymousGetMapping;
import com.zhang.modules.security.config.bean.LoginCodeEnum;
import com.zhang.modules.security.config.bean.LoginProperties;
import com.zhang.modules.security.config.bean.SecurityProperties;
import com.zhang.response.Result;
import com.zhang.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName AuthorizationController
 * Description TODO 类描述： 授权接口
 *
 * @author ZhangRenjie
 * Date  2020/12/24 9:35
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
// 会生成一个包含常量，和标识了NotNull的变量的构造方法。生成的构造方法是私有的private。
@RequiredArgsConstructor
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {

    private final LoginProperties loginProperties;
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;


    @ApiOperation("获取验证码")
    @AnonymousGetMapping(value = "/code")
    public Result<Map<String, Object>> getCode(){
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        // 当验证码类型为 arithmetic 时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() -1 == LoginCodeEnum.arithmetic.ordinal() & captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        log.info("登录图片验证码结果：" + captchaValue);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2){{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return Result.success(imgResult);
    }

}