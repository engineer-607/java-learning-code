package com.nanxinda.linkedlist.circularlinkedlist;
/// 解决约瑟夫问题
public class CircleQuestion {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.initialize(5);
        circleLinkedList.list();
        circleLinkedList.circleSolve(1,2);

    }
}
class CircleLinkedList{
    Student first;
    int countStu;
    public void initialize(int k){
        countStu = k;
         first = new Student(1);
        Student temp = first;
        for (int i = 0; i < k-1; i++) {
            Student student = new Student(i+2);
            temp.next = student;
            temp = temp.next;
            if(i==k-2){
                student.next = first;
            }
        }
    }
    public void list(){
        if(first == null){
            System.out.println("环形链表为空");
            return;
        }
        Student temp = first;
        while (true){
            System.out.printf("编号为%d\n",temp.no);
            if(temp.next ==first){
                break;
            }
            temp = temp.next;
        }
    }
    public Student getOrder(int k){
        Student temp = first;
        int i = 1;
        while (true){
            if(i==k){
                return temp;
            }
            if(temp.next == first){
                System.out.println("没有找到该节点");
                return null;
            }
            temp = temp.next;
            i++;
        }
    }
    private Student getBefore(Student student){
        Student temp = student;
        while (true){
            if(temp.next == student){
                return temp;
            }
            temp = temp.next;
        }
    }

    /**
     *
     * @param k:第几个小孩开始报数
     * @param m：每次报到几暂时停止
     */
    public void circleSolve(int k,int m){
        /// 注意：需要先对数据进行校验
        if(first==null||k<0||k>countStu||m<0){
            System.out.print("不符合报数规则");
            return;
        }
        int count = 0;
        Student temp = this.getOrder(k);
        while (true){
            count++;
            if(count == m){
                Student before = this.getBefore(temp);
                before.next = temp.next;
                System.out.print(temp.no+"->");
                count = 0;

            }
            if(temp.next == temp){
                System.out.print(temp.no);
                break;
            }
            temp = temp.next;
        }

    }
}
class Student{
    int no;
    Student next;

    public Student(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no+"}";
    }
}