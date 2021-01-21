package com.zhang.modules.security.config.bean;

import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import com.zhang.exception.BadRequestException;
import com.zhang.utils.StringUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.Objects;

/**
 * ClassName LoginProperties
 * Description TODO 类描述：从配置文件读取 login 配置
 *
 * @author ZhangRenjie
 * Date  2020/12/24 10:17
 */
@Data
@Configuration

@ConfigurationProperties(prefix = "login")
public class LoginProperties {

    /**
     * 账号单用户 登录
     */
    private  boolean singleLogin = false;

    private  LoginCode loginCode;
    /**
     * 用户登录信息缓存
     */
    private boolean cacheEnable;

    public boolean isSingleLogin(){
        return singleLogin;
    }

    public boolean isCacheEnable(){
        return cacheEnable;
    }

    /**
     * Description //TODO 获取验证码生产类
     * @return com.wf.captcha.base.Captcha
     * @author ZhangRenJie
     * @date 2020/12/24 11:07
     **/
    public Captcha getCaptcha() {
        if (Objects.isNull(loginCode)) {
            loginCode = new LoginCode();
            if (Objects.isNull(loginCode.getCodeType())) {
                loginCode.setCodeType(LoginCodeEnum.arithmetic);
            }
        }
        return switchCaptcha(loginCode);
    }

    /**
     * Description //TODO 依据配置信息生产验证码
     * @param loginCode 登录验证配置信息
     * @return com.wf.captcha.base.Captcha
     * @author ZhangRenJie
     * @date 2020/12/24 11:10
     **/
    private Captcha switchCaptcha(LoginCode loginCode) {

        Captcha captcha;
        synchronized (this) {
            switch (loginCode.getCodeType()) {
                case arithmetic:
                    captcha = new ArithmeticCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case chinese:
                    captcha = new ChineseCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case chinese_gif:
                    captcha = new ChineseGifCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case gif:
                    captcha = new GifCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case spec:
                    captcha = new SpecCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                default:
                    throw new BadRequestException("验证码配置信息错误！正确配置查看 LoginCodeEnum ");
            }
        }
        if (StringUtils.isNotBlank(loginCode.getFontName())) {
            captcha.setFont((new Font(loginCode.getFontName(), Font.PLAIN, loginCode.getFontSize())));
        }
        return captcha;
    }
}