package com.itcode;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MethodTest
{
    public static void main(String[] args)
    {
        String [] names ={"tom","tim","allen","alice"};
        Map mapParams = new HashMap();
        mapParams.put("fuck","fuck_lala");
        mapParams.put("mac","mac_book");
        Class<?> clazz = Test.class;
        try
        {
            Method PreOrderMethod = clazz.getMethod("PreOrder", Map.class);
            printMap(mapParams);
            PreOrderMethod.invoke(clazz.newInstance(),mapParams);
            System.out.println("after");
            printMap(mapParams);


//            Method method = clazz.getMethod("sayHi", String.class);
//            for(String name:names)
//                method.invoke(clazz.newInstance(),name);
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        }
    }

    private static void printMap(Map mapParams) {
        //1、迭代map集合的值
        Set set = mapParams.keySet(); //key装到set中
        Iterator it = set.iterator(); //返回set的迭代器 装的key值
        while(it.hasNext()){
            String key = (String)it.next();
            String value = (String)mapParams.get(key);
            System.out.println(key+"<---->"+value);
        }
    }
}
class Test
{
    public void sayHi(String name)
    {
        System.out.println("Hi "+name);
    }
    public void PreOrder(Map requestParams) {
        requestParams.put("access_token", "lalatokenfucklala");
    }
}
