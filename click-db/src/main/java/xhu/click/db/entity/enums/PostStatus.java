package xhu.click.db.entity.enums;

/**
 * Post状态
 */
public enum PostStatus {
    DRAFT(0,"草稿"),
    Unaudited(1,"未审核"),
    POSTED(2,"已发布");
    int status;
    String desc;
    private PostStatus(int status,String description){
        this.status=status;
        this.desc=description;
    }
    public int getStatus(){
        return this.status;
    }

}
