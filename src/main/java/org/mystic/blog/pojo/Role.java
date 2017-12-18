package org.mystic.blog.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/11/1 9:33
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Data
@ToString
public class Role implements Serializable {
    private Integer roleID;
    private String roleName;
}
