import com.nanxinda.ploy01.Animal;
import com.nanxinda.ploy01.Cat;
import com.nanxinda.ploy01.Dog;
import com.nanxinda.ploy02.Fish;
import com.nanxinda.ploy02.Food;
import com.nanxinda.ploy02.Master;

public class Main {
    public static void main(String[] args) {
        /*
        多态:方法或对象有多种形态
        1)方法的重写和重载体现多态
        2)对象的多态
        编译类型:变量声明时的类型
        运行类型:变量实际指向的对象类型
        1.编译类型在定义对象时确定不能再改变(强制转化类型也并没有改变编译类型)
        2.运行类型是可以发生变化
        3.编译类型可以与运行类型不一致(eg.Animal animal = new Dog();animal=new Cat();)
         */
        Animal animal = new Dog();
        //运行时,执行到这行时,animal运行类型是Dog
        animal.cry();//小狗汪汪叫
        //编译类型是Animal,运行类型未Cat
        animal = new Cat();
        animal.cry();//小猫喵喵叫
        //多态案例一
        Master jack = new Master("jack");
        Food food = new Fish("鱼");
        com.nanxinda.ploy02.Animal animal1 = new com.nanxinda.ploy02.Cat("狸花猫");
        System.out.println(jack.feed(animal1,food));



    }
}