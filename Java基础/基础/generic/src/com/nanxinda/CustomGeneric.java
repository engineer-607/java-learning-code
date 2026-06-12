package com.nanxinda;
@SuppressWarnings({"all"})
public class
CustomGeneric {
    public static void main(String[] args) {
        Tiger tiger = new Tiger("john");
        //T、R、M默认是Object
        /// =============================

    }
}
//1.Tiger后面有泛型，所以把Tiger称为自定义泛型类
//2.T，R，M是泛型的标识符（一般为大写字母）
//3.泛型标识符可以有多个
//4.普通成员可以使用泛型（属性、方法）
//5.使用泛型的数组，不能初始化
//6.静态方法中不能使用类的泛型
class Tiger<T,R,M>{
    String name;
    R r;
    M m;
    T t;
    //数组在new时不能确定T的类型，无法在内存开空间
    T[] ts;
    public Tiger(String name){
        this.name = name;
    }

    public Tiger(String name, R r, M m, T t) {//构造器使用泛型
        this.name = name;
        this.r = r;
        this.m = m;
        this.t = t;
    }
    //因为静态和类相关的，在类加载时，对象还没有创建
    //所以，如果静态方法和静态属性使用泛型，JVM就无法完成初始化
}
///====================================================
interface IUsb<U,R> {
    int n = 10;
    //1.接口中，静态成员不能使用泛型
    //U name;(X)
    R get(U u);

    void hi(R r);

    void run(R r1,R r2,U u1,U u2);

    //jdk8中，可以在接口中，使用默认方法，也可以使用泛型
    default R method(U u){
        return null;
    }
}
//在继承接口 需要指定泛型接口的类型
interface IA extends IUsb<String,Integer>{}
//当实现IA接口时，因为IA继承IUSb接口时，指定U为String，R为Double
//，当实现IUsb接口的方法时，使用String替换U，是Double替换R
class AA implements IA{

    @Override
    public Integer get(String s) {
        return 0;
    }

    @Override
    public void hi(Integer integer) {

    }

    @Override
    public void run(Integer r1, Integer r2, String u1, String u2) {

    }
}
class BB implements IUsb<String,Float>{
//实现接口时，可以直接指定泛型接口的类型
    @Override
    public Float get(String s) {
        return 0f;
    }

    @Override
    public void hi(Float aFloat) {

    }

    @Override
    public void run(Float r1, Float r2, String u1, String u2) {

    }
}
//没有指定时，默认是Object
class CC implements IUsb<Object,Object>{

    @Override
    public Object get(Object object) {
        return null;
    }

    @Override
    public void hi(Object object) {

    }

    @Override
    public void run(Object r1, Object r2, Object u1, Object u2) {

    }
}