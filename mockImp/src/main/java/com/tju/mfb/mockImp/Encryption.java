package com.tju.mfb.mockImp;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

class ProxyFactory implements MethodInterceptor {

    public Object target;//维护一个目标对象
    public HashMap<String,Object> hashMap;
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //为目标对象生成代理对象
    public Object getProxyInstance() {
        //工具类
        Enhancer en = new Enhancer();
        //设置父类
        en.setSuperclass(target.getClass());
        //设置回调函数
        en.setCallback(this);
        //创建子类对象代理
        return en.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        System.out.println("开启事务");
        // 执行目标对象的方法
        StringBuilder id = new StringBuilder(method.getDeclaringClass().getName() + "/" + method.getName() + "/");
        StringBuilder fileName = new StringBuilder();
        for (Object arg : args) {
            fileName.append(arg);
            id.append(arg.getClass().getName()).append("/");
        }
        id.append(fileName);

        File file = new File(id.toString());
        Object result = method.invoke(target, args);
        if(file.exists()){
            System.out.println("挡板发动");
            hashMap = (HashMap<String, Object>)ProcessFile.getObjectByObjectInput(file);
            return hashMap.get(id.toString());
        }
        hashMap = new HashMap<String, Object>();
        hashMap.put(id.toString(),result);
        ProcessFile.createNewFile(id.toString());
        ProcessFile.saveObjectByObjectOutput(hashMap,file);
///        System.out.println("关闭事务");
        return result;
    }
}
public class Encryption{
    public static Object getMock(String classname){
        try {
            Class c = Class.forName(classname);
            Object loc = c.newInstance();
            return new ProxyFactory(loc).getProxyInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}

//class TestU{
//    int add(int a, int b){
//        return a+b;
//    }
//}
//
//class T{
//    public static void main(String[] args) {
//        TestU testU = (TestU)Encryption.getMock("com.tju.mfb.mockImp.TestU");
//        testU.add(1,2);
//    }
//}