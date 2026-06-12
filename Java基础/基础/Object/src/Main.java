import com.nanxinda.Object.equalsExercise.Person;

public class Main {
    public static void main(String[] args) {
        Person person1= new Person('M',18,"jack");
        Person person2 = new Person('M',"jack",18);
        System.out.println(person2.equals(person1));
        System.out.println(person2.oldEquals(person1));

    }
}