package org.mystic.blog.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 10:41
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Data
@ToString
public class ArticleSort {
    private Integer sortArticleID;
    private String sortArticleName;
    private Integer userID;
}
