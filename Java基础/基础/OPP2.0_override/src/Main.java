import com.nanxinda.overrideExercise.Person;
import com.nanxinda.overrideExercise.Student;



public class Main {
    public static void main(String[] args) {
        Person person = new Person("jack",18);
        Student student = new Student("Lucy",22,"2025...",80);
        System.out.println(person.say());
        System.out.println(student.say());
    }
}