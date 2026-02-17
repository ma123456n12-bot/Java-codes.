    import java.util.ArrayList;
/* ===================== COURSE CLASS ===================== */
class Course {
    String name;
    int marks;
    int credit;
    Course(String name, int marks, int credit) {
        this.name = name;
        this.marks = marks;
        this.credit = credit;
    }

    double creditGained() {
        return (marks / 100.0) * credit;
    }
}
/* ===================== STUDENT CLASS ===================== */
class Student {
    String name;
    ArrayList<Course> courseList = new ArrayList<>();

    Student(String name) {
        this.name = name;
    }
    void addCourse(Course course) {
        courseList.add(course);
    }
    double calculateGPA() {
        double totalCredits = 0;
        double totalCreditGained = 0;

        for (Course c : courseList) {
            totalCredits += c.credit;
            totalCreditGained += c.creditGained();
        }
        return totalCreditGained / totalCredits;
    }

    void displayCourses() {
        for (Course c : courseList) {
            System.out.println("Course: " + c.name);
            System.out.println("Marks: " + c.marks);
            System.out.println("Credit: " + c.credit);
            System.out.println("Credit gained: " + c.creditGained());
        }
    }
}
/* ===================== TEACHER CLASS ===================== */
class Teacher {
    String name;
    ArrayList<Student> students = new ArrayList<>();

    Teacher(String name) {
        this.name = name;
    }

    void addStudent(Student s) {
        students.add(s);
    }

    double getAverageMarks(String courseName) {
        double sum = 0;
        int count = 0;

        for (Student s : students) {
            for (Course c : s.courseList) {
                if (c.name.equals(courseName)) {
                    sum += c.marks;
                    count++;
                }
            }
        }
        return sum / count;
    }
}

/* ===================== MAIN CLASS ===================== */
public class Finalclass {
    public static void main(String[] args) {

        Student s1 = new Student("Ali");

        Course c1 = new Course("Math", 90, 6);
        Course c2 = new Course("English", 80, 4);

        s1.addCourse(c1);
        s1.addCourse(c2);

        Teacher t1 = new Teacher("Ahmed");
        t1.addStudent(s1);

        System.out.println("GPA: " + s1.calculateGPA());
        System.out.println("Average Math Marks: " + t1.getAverageMarks("Math"));

        s1.displayCourses();
    }
}


