package org.mystic.blog.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/12 10:50
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Data
@ToString
public class User implements Serializable {
    private Integer userID;
    private String userName;
    private String userPWD;
    private String userEmail;
    private String userPhone;
    private String userQQ;
    private int userSex;
    private String userLastLoginIP;
    private Date userLastLoginTime;
    private List<String> roles;
}
