package xhu.click.common.entity.constants;

public class RedisConstants {
    /**
     * 所有项目前缀
     */
    public static final String REDIS_PREFIX="click:";
    /**
     * 登录用户token前缀
     */
    public static final String LOGIN_USER_KEY = "click:user:login";
    /**
     * token保存时间
     */
    public static final long LOGIN_USER_TTL = 35;
}
