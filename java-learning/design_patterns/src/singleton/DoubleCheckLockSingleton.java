package singleton;

/**
 * 双重检查锁单例模式
 *
 * @author DeltaV235
 */
public class DoubleCheckLockSingleton {
    // volatile关键字禁用了指令重排序以及在该对象被修改时及时将工作内存中的副本同步至主内存，并更新所有线程的副本
    private static volatile DoubleCheckLockSingleton instance;

    private DoubleCheckLockSingleton() {
    }

    /**
     * 减少了多线程中同步等待的时间
     *
     * @return 该对象实例
     */
    public static DoubleCheckLockSingleton getInstance() {
        // 若instance不为null，则直接返回instance，省去了线程等待同步锁的时间
        if (instance == null)
            synchronized (DoubleCheckLockSingleton.class) {
                // 防止多个线程重复实例化对象
                if (instance == null)
                    instance = new DoubleCheckLockSingleton();
            }
        return instance;
    }
}
