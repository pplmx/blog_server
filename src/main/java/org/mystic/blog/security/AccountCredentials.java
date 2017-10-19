package org.mystic.blog.security;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/19 9:07
 * @since: JDK1.8.0_144
 * @version: Description:
 */
@Data
class AccountCredentials {

    private String userName;
    private String userPWD;

}
