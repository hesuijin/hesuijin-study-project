package com.example.demo.generics;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2023/3/17
 */
public class WildcardGenericsMethod {

    public static void main(String args[]) {

        //上限为 Number类型
        GenericsMethodDTO<? extends Number> request1 = new GenericsMethodDTO<>(100.1);
        GenericsMethodDTO<? extends Number> request2 = new GenericsMethodDTO<>(100L);

        //下限为String  即包含String的父类的Object
        GenericsMethodDTO<? super String>  response1 = funByWildcard(request1);
        GenericsMethodDTO<? super String>  response2 = funByWildcard(request2);
        System.out.println("response1:"+response1.getKey()+" "+response1.getKey().getClass());
        System.out.println("response2:"+response2.getKey()+" "+response2.getKey().getClass());
    }

    public static GenericsMethodDTO<? super String> funByWildcard (GenericsMethodDTO<? extends Number> request){
        GenericsMethodDTO<Object> response = new GenericsMethodDTO<>(request.getKey().toString());
        return response;
    }


}
