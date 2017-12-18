package org.mystic.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Resource
	private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testSendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);//发送者.
        message.setTo("215104920@qq.com");//接收者.
        message.setSubject("测试邮件（邮件主题）");//邮件主题.
        message.setText("这是邮件内容");//邮件内容.
        javaMailSender.send(message);//发送邮件
    }

    @Test
    public void testSendHtml() {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(sender,"caoyu.info","UTF-8"));
            helper.setTo("215104920@qq.com");
            helper.setSubject("标题：发送Html内容");

            String sb = "<h1>大标题-h1</h1>" +
                    "<p style='color:#F00'>红色字</p>" +
                    "<p style='text-align:right'>右对齐</p>";
            helper.setText(sb, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        int userID = 8;
        //noinspection unchecked
        Map<String, Object> user = restTemplate.getForObject("http://localhost:8080/users/" + userID, HashMap.class);
        //assertThat(user.getUserSex()).isEqualTo(1);
        System.out.println("user = " + user);

        Map<String, Object> newUser = new HashMap<>(16);
        newUser.put("userName", "嘿嘿");
        newUser.put("userEmail", "21@qq.com");
        newUser.put("userPWD", "123456");
        newUser.put("userSex", 0);
        newUser.put("userPhone", "18888888888");
        newUser.put("userQQ", "232323232");
        restTemplate.put("http://localhost:8080/users/" + userID, newUser);
        //noinspection unchecked
        Map<String,Object> testUser = restTemplate.getForObject("http://localhost:8080/users/" + userID, HashMap.class);
        System.out.println("testUser = " + testUser);
    }

}
