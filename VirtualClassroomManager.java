import java.util.*;
import java.util.logging.*;

public class VirtualClassroomManager {
    private static final Logger logger = Logger.getLogger(VirtualClassroomManager.class.getName());

    private Map<String, Classroom> classrooms;

    public VirtualClassroomManager() {
        this.classrooms = new HashMap<>();
        logger.info("Virtual Classroom Manager initialized.");
    }

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            try {
                System.out.println("Enter command: ");
                input = scanner.nextLine();
                manager.handleInput(input);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred: ", e);
            }
        }
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "add_classroom":
                addClassroom(parts[1]);
                break;
            case "add_student":
                addStudent(parts[1]);
                break;
            case "schedule_assignment":
                scheduleAssignment(parts[1]);
                break;
            case "submit_assignment":
                submitAssignment(parts[1]);
                break;
            default:
                logger.warning("Unknown command: " + command);
                System.out.println("Unknown command: " + command);
        }
    }

    private void addClassroom(String input) {
        String className = input.trim();
        if (classrooms.containsKey(className)) {
            logger.warning("Classroom already exists: " + className);
            System.out.println("Classroom already exists: " + className);
        } else {
            classrooms.put(className, new Classroom(className));
            logger.info("Classroom " + className + " has been created.");
            System.out.println("Classroom " + className + " has been created.");
        }
    }

    private void addStudent(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            logger.warning("Invalid input for adding student.");
            System.out.println("Invalid input for adding student.");
            return;
        }

        String studentId = parts[0];
        String className = parts[1];

        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            logger.warning("Classroom does not exist: " + className);
            System.out.println("Classroom does not exist: " + className);
        } else {
            classroom.addStudent(studentId);
            logger.info("Student " + studentId + " has been enrolled in " + className + ".");
            System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
        }
    }

    private void scheduleAssignment(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length != 2) {
            logger.warning("Invalid input for scheduling assignment.");
            System.out.println("Invalid input for scheduling assignment.");
            return;
        }

        String className = parts[0];
        String assignmentDetails = parts[1];

        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            logger.warning("Classroom does not exist: " + className);
            System.out.println("Classroom does not exist: " + className);
        } else {
            classroom.scheduleAssignment(assignmentDetails);
            logger.info("Assignment for " + className + " has been scheduled.");
            System.out.println("Assignment for " + className + " has been scheduled.");
        }
    }

    private void submitAssignment(String input) {
        String[] parts = input.split(" ", 3);
        if (parts.length != 3) {
            logger.warning("Invalid input for submitting assignment.");
            System.out.println("Invalid input for submitting assignment.");
            return;
        }

        String studentId = parts[0];
        String className = parts[1];
        String assignmentDetails = parts[2];

        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            logger.warning("Classroom does not exist: " + className);
            System.out.println("Classroom does not exist: " + className);
        } else {
            classroom.submitAssignment(studentId, assignmentDetails);
            logger.info("Assignment submitted by Student " + studentId + " in " + className + ".");
            System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
        }
    }

    class Classroom {
        private String name;
        private Set<String> students;
        private List<String> assignments;

        public Classroom(String name) {
            this.name = name;
            this.students = new HashSet<>();
            this.assignments = new ArrayList<>();
        }

        public void addStudent(String studentId) {
            students.add(studentId);
        }

        public void scheduleAssignment(String assignmentDetails) {
            assignments.add(assignmentDetails);
        }

        public void submitAssignment(String studentId, String assignmentDetails) {
            if (!students.contains(studentId)) {
                logger.warning("Student " + studentId + " is not enrolled in " + name);
                System.out.println("Student " + studentId + " is not enrolled in " + name);
            } else {
                logger.info("Assignment details received from Student " + studentId + ": " + assignmentDetails);
                System.out.println("Assignment details received from Student " + studentId + ": " + assignmentDetails);
            }
        }
    }
}
