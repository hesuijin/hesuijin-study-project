package com.example.demo.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsMethod {

    public static void main(String args[]){
        String  tempString = "你好哈！";
        String  type1 = "String";
        GenericsMethodDTO<String> GenericsMethodDTOString = funForReturnType(tempString,type1);
        System.out.println("String:"+GenericsMethodDTOString.getKey());

        Integer tempInteger = 18;
        String  type2 = "Integer";
        GenericsMethodDTO<Integer> GenericsMethodDTOInteger = funForReturnType(tempInteger,type2);
        System.out.println("Integer:"+GenericsMethodDTOInteger.getKey());

        List<Integer> listInteger = new ArrayList<>();
        listInteger.add(1);
        listInteger.add(2);
        listInteger.add(3);
        System.out.println("integerList List:"+listInteger.toString());

        List<String> stringList = funByTypeChange(listInteger);
        System.out.println("stringList List:"+stringList.toString());
    }

    //<T> 的作用是 <>定义该方法为泛型方法，同时<>里面的参数T 可以作为该方法的里面的类型变量名。
    //响应中DTO<T> 中的T就可以被认为是已经进行了定义的 类型变量名。
    //如不理解，可以把方法名修改为这样：
    //public static <T,K> GenericsMethodDTO<T>  funForReturnType(Object temp,String type){}

    //为什么不能有 public static  <T> <T>  fun() 这样的形式？
    //实际上如果仅是单纯的需要返回对象是泛型，我们直接返回Object类型即可。
    //因此泛型响应需要使用  <T> DTO<T> 的方式。
    public static <T> GenericsMethodDTO<T>  funForReturnType(Object temp,String type){

       if ("String".equals(type)){
           GenericsMethodDTO<String> stringGeneric = new GenericsMethodDTO<String>(temp.toString());
           return (GenericsMethodDTO<T>) stringGeneric;
       }
       if ("Integer".equals(type)){
           GenericsMethodDTO<Integer> stringGeneric = new GenericsMethodDTO<Integer>(Integer.valueOf(temp.toString()));
           return (GenericsMethodDTO<T>) stringGeneric;
       }
        return null;
    }


    /**
     * 泛型方法定义了 <T,K>
     * 因此可以使用 入参的类型变量T或者响应的类型变量K
     * 注意 不管入参的类型变量T或者响应的类型变量K 都是需要通过方法调用方或者外部进行设置的
     * @param list
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T,K> List<K> funByTypeChange (List<T> list){
        List<K> listString = new ArrayList<K>();
        for (T t :list){
            listString.add((K)("哈哈哈"+t));
        }
        return listString;
    }

}