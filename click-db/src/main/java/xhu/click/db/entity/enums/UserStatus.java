package xhu.click.db.entity.enums;

public enum UserStatus {
    ABLE(0),
    DISABLE(1),
    CANCEL(2);
    int status;

    private UserStatus(int status) {
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
}
