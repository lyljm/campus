package xhu.click.db.entity.enums;

/**
 * 排序策略
 */
public enum PostStrategy {
    LATEST(0,"最新"),
    Hottest(1,"最热"),
    RESENT_REPLY(2,"最近回复");

    int status;
    String desc;
    private PostStrategy(int status,String description){
        this.status=status;
        this.desc=description;
    }
    public int getStatus(){
        return this.status;
    }

}
