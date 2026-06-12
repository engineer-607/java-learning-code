import com.nanxinda.encap01.Encap01;

public class Main {
    public static void main(String[] args) {
        /*
        封装：把抽象出的数据（属性）和对数据的操作（方法）封装在一起，
        数据被保护在内部，程序的其他部分只有通过被授权的操作（方法）
        才能对数据操作
        封装的理解和好处：
        1）隐藏实现细节（不用管方法如何调用，只需要调用即可）
        2）可以对数据进行验证，保证安全合理
        封装的实现步骤：
        1）将属性进行私有化
        2）提供一个公共的（public）set方法，用于对属性判断和赋值
        public void setXxx(类型 参数名){
               //加入数据验证的业务逻辑
               属性=参数名
        }
        3）提供一个公共的(public)get方法，用于获取属性的值
        public 数据类型 getXxx(){//权限判断，用于判断某个属性
              return xx;
     }
         */
        Encap01 encap01 = new Encap01();
        encap01.setAge(19);
        encap01.setName("jack");
        encap01.setSalary(30000);
        encap01.info();
        Encap01 encap2 = new Encap01("Smith",30,30000,"主管");
        encap2.info();

    }
}