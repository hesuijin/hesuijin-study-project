package com.example.demo.jvmMemoryModel.demo2JvmMemoryModelGC;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/9
 */
public class ThreadStackOverflowTest {
    //JVM设置
    // - Xss 128k  -Xss 默认 1M
    static  int count = 0;
    /**
     * 每一次 调用redo的时候
     * main线程 都新增一个 redo 的栈帧
     */
    static  void redo(){
        count ++;
        redo();
    }

    public static void main(String[] args) {
        try{
            redo();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(count);
        }
    }
}
