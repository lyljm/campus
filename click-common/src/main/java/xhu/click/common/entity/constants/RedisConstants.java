package xhu.click.common.entity.constants;

public class RedisConstants {
    /**
     * 所有项目前缀
     */
    public static final String REDIS_PREFIX="click:";

    /**
     * 登录用户token前缀
     */
    public static final String LOGIN_USER_KEY = "click:user:login:";
    /**
     * token保存时间
     */
    public static final long LOGIN_USER_TTL = 35;

    /**
     * 获取用户注册id
     */
    public static final String NEX_ID_PREFIX="click:register:id:";
    /**
     * 点赞前缀
     */
    public static final String POST_IS_LIKED="click:post:isLiked:";
    /**
     * 评论点赞
     */
    public static final String COMMENT_IS_LIKED="click:comment:isLiked:";
    /**
     * 是否收藏
     */
    public static final String POST_IS_COLLECT="click:post:isCollect:";

}
