package com.hdd.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码相关api
 * @author hedd
 * @create 2021/4/22
 */
@RestController
public class CaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha",produces = "image/jpeg")//加produces指定响应体的返回类型和编码格式
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        //定义response输出类型为image/jpeg
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //---------------------------生成验证码 begin----------------------
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码"+text);
        //将验证码放到session中
        request.getSession().setAttribute("captcha",text);
        //根据文本内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出图片，格式为jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        //---------------------------生成验证码 end----------------------

    }
}
