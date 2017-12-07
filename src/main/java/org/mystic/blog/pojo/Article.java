package org.mystic.blog.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 10:42
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Data
@ToString
public class Article {
    private Integer articleID;
    private String articleName;
    private Date articleTime;
    private String articleIP;
    private int articlePV;
    private int articleType;
    private String articleContent;
    private int articleTopShow;
    private int articleStar;
    private Integer userID;
    private Integer sortArticleID;
}
