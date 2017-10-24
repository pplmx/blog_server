package org.mystic.blog.pojo;

import lombok.Data;
import lombok.ToString;

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
    private String articleTime;
    private String articleIP;
    private int articlePV;
    private int articleType;
    private String articleContent;
    private int articleTopShow;
    private int articleStar;
    private Integer userID;
    private Integer sortArticleID;
}
