package org.mystic.blog.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mystic
 * Date: 2017/10/12
 * Time: 10:50
 * Description:
 */
@Data
@ToString
public class User {

    /**
     * 用户ID
     **/
    private Integer userID;

    /**
     * 用户名
     **/
    private String userName;

    /**
     * 用户密码
     **/
    private String userPWD;

    /**
     * 邮箱
     **/
    private String userEmail;

    /**
     * 手机号码
     **/
    private String userPhone;

    /**
     * QQ
     **/
    private String userQQ;

    /**
     * 性别
     **/
    private String userSex;

    /**
     * 最近登录的IP
     **/
    private String userLastLoginIP;

    /**
     * 最近登录的时间
     **/
    private Date userLastLoginTime;
}
