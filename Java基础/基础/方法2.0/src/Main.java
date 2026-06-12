public class Main {
    public static void main(String[] args) {
        /*
        构造方法/构造器
        介绍：
        构造方法又叫构造器，是类的一种特殊的方法，主要作用是对新对象的初始化
        eg.在创建人类对象时，就直接指定这个对象的年龄和姓名
        基本语法:
        [修饰符]方法名（形参列表）{
             方法体;
        }
        注意事项：
        1）构造器的修饰符可以默认，可以是public protected private
        2）构造器没有返回值，也不能写void
        3）方法名 和类名必须一样
        4）构造器的调用由系统完成
         */
        Person person = new Person("Jack",18);
        System.out.println("person信息如下：(1)名称-"+person.name+" （2）年龄-"+person.age);
        /*
        构造使用细节：
        1.一个类可以定义多个不同的构造器，即构造器的重载
        2.如果没有定义构造器，系统会自动给类生成一个默认无参构造器
        eg.Dog dog = new Dog();(因为又默认无参构造器，所以无需传入参数）
        class Dog {
           public Dog(){
           }
        }
        一旦定义自己的构造器，默认的构造器就被覆盖，就不能再使用默认的无参构造器
        除非显示的定义一下即Dog(){}

         */
        NewPerson newPerson = new NewPerson(){};
        NewPerson newPerson1 = new NewPerson("jack");
        System.out.println("1号人物年龄："+newPerson.age+",姓名："+newPerson.name);
        System.out.println("2号人物年龄："+newPerson1.age+",姓名："+newPerson1.name);
        /*this关键字
        Java虚拟机给每个对象分配this，代表当前对象
        eg.this.name代表当前对象的name属性
        this的本质：本质存储了对象的地址，指向对象的空间，每个对象创建时会附带this（隐藏）
        this的细节和注意事项：
        （1）this关键字可以访问本类的属性，方法，构造器
        eg.this访问属性
        class A{
        String name ="smith";
           public void f3(){
                name="jack";
                System.out.println(name);//就近原则，输出jack
                System.out.println(this.name);//明确表示输出name属性，输出smith
             }
        }
        eg.this访问方法
        class T{
        public void A(){
        B();
        this.B();//二者均可以调用B方法
        }
        public void B(){
        }
        }
        eg.this访问构造器，格式：this(参数列表)，注意事项：构造器访问构造器时必须放在第一句，
        意味构造器的调用首先在构造器中，然后必须放在首句
        class T{
            public T(){
               this("jack",100);r//访问构造器必须要放在构造器首句
               .....
            }
            public T(String name, int age){
                .....
            }
        }
         */
        //作业
        Dog dog1 = new Dog("小白",3);
        Dog dog2 = new Dog("小黑",4);
        TestDog testDog = new TestDog();
        if(testDog.compareTo(dog1,dog2)){
            System.out.println("是同一条狗");
        }else{
            System.out.println("不是同一条狗");
        }

    }
}
class Person{
    String name;
    int age;
    //第一个构造器
    public Person(String pName,int pAge){
        System.out.println("构造器被调用");
        name=pName;
        age=pAge;
    }
    //第二个构造器
    public Person(String pName){
        name = pName;
    }
}
//作业
class NewPerson{
    //流程分析：main函数遇到第一个new NewPerson();
    //判断该类是否已加载，发现未加载
    // 加载NewPerson类信息（NewPerson.class)，只会加载一次
    //创建第一个对象，分配地址，JVM自动对属性进行初始化（并非默认构造器进行）
    //NewPerson ()构造器进行初始化，age=18
    //对象创建完成，对象名newPerson指向对象地址
    //遇到第二个new NewPerson("Jack");
    //判断该类是否已加载，发现已加载
    //创建第二个对象，分配地址，对属性进行默认初始化
    //NewPerson(String pName)构造器进行初始化,字符串Jack进入常量池，将其地址赋值给属性name
    //对象创建完成，对象名newPerson1指向对象地址
    String name;
    int age;
    //完成对象的初始化
    public NewPerson(){
        age=18;
    }
    public NewPerson(String pName){
        name=pName;

    }
}
class Dog{
    String name;
    int age;
    public Dog(String name,int age){
        this.name=name;
        this.age=age;
    }
}
class TestDog {
    public boolean compareTo(Dog dog1,Dog dog2){
        if(dog1.age== dog2.age&&dog1.name.equals(dog2.name)){
            return true;
        }
        return  false;
    }
}
