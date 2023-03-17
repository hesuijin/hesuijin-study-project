package com.example.demo.generics;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2023/3/17
 */
public class GenericsMethodDTO<T>  {

    private T key;
    public GenericsMethodDTO(T key) {
        this.key = key;
    }
    public T getKey(){
        return key;
    }
}
