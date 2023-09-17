package xhu.click.common.utils.thread;

public class LocalHolder {
    /**
     * 单线程维护对象
     */
    private static final ThreadLocal<Object>tl=new ThreadLocal<>();

    /**
     * 获取单线程对象
     * @param object
     */
    public static void saveObject(Object object){
        tl.set(object);
    }

    /**
     * 保存单线程对象
     * @return
     */
    public static Object getObject(){
        return tl.get();
    }

    /**
     * 移除对象
     */
    public static void removeObject(){
        tl.remove();
    }
}
