    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Scanner;
    class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int registeredCount;
    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.registeredCount = 0;
      }
      public String getCode() {
        return code;
      }
      public String getTitle() {
        return title;
      }
      public String getDescription() {
        return description;
       }
       public int getCapacity() {
        return capacity;
      }
      public int getAvailableSlots() {
        return capacity - registeredCount;
      }
      public boolean register() {
        if (registeredCount < capacity) {
            registeredCount++;
            return true;
         }
        return false;
       }
       public boolean drop() {
        if (registeredCount > 0) {
            registeredCount--;
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return String.format("%s - %s\nDescription: %s\nCapacity: %d\nAvailable Slots: %d",
                code, title, description, capacity, getAvailableSlots());
       }
       }
       class Student {
      private String id;
       private String name;
      private List<Course> registeredCourses;
        public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
       }
        public String getId() {
        return id;
        }
        public String getName() {
        return name;
       }
       public List<Course> getRegisteredCourses() {
        return registeredCourses;
       }
       public boolean registerForCourse(Course course) {
        if (course.register()) {
            registeredCourses.add(course);
            return true;
          }
        return false;
        }
        public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course) && course.drop()) {
            return true;
         }
        return false;
       }
       }
       class CourseRegistrationSystem {
     private Map<String, Course> courses;
     private Map<String, Student> students;
      private Scanner scanner;
      public CourseRegistrationSystem() {
        this.courses = new HashMap<>();
        this.students = new HashMap<>();
        this.scanner = new Scanner(System.in);
        courses.put("CS101", new Course("CS101", "Intro to Computer Science", "Basic concepts of programming", 30));
        courses.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to calculus", 25));
        courses.put("ENG101", new Course("ENG101", "English Literature", "Study of classic literature", 20));
        students.put("S001", new Student("S001", "Alice Johnson"));
        students.put("S002", new Student("S002", "Bob Smith"));
      }
      public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) {
            System.out.println(course);
            System.out.println();
        }
       }
       public void registerStudentForCourse() {
        System.out.print("\nEnter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        if (student.registerForCourse(course)) {
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Failed to register. Course might be full.");
        }
      }
        public void dropStudentCourse() {
        System.out.print("\nEnter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        if (student.dropCourse(course)) {
            System.out.println("Successfully dropped from " + course.getTitle());
        } else {
            System.out.println("Failed to drop the course.");
        }
         }
         public void run() {
        while (true) {
            System.out.println("\n1. Display Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    registerStudentForCourse();
                    break;
                case 3:
                    dropStudentCourse();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                 }
                }
               }
             }
             public class Main {
              public static void main(String[] args) {
              CourseRegistrationSystem system = new CourseRegistrationSystem();
               system.run();
              }
               }
