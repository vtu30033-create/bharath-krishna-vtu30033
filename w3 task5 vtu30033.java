import java.util.*;

class Student {
    private int id;
    private String name;
    private double cgpa;

    Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return cgpa;
    }
}

class Priorities {

    public List<Student> getStudents(List<String> events) {

        PriorityQueue<Student> queue = new PriorityQueue<>(
            (a, b) -> {

                // 1. Higher CGPA gets higher priority
                int result = Double.compare(b.getCGPA(), a.getCGPA());

                if (result != 0) {
                    return result;
                }

                // 2. Same CGPA -> alphabetical name
                result = a.getName().compareTo(b.getName());

                if (result != 0) {
                    return result;
                }

                // 3. Same CGPA and name -> smaller ID
                return Integer.compare(a.getID(), b.getID());
            }
        );

        for (String event : events) {

            String[] data = event.split(" ");

            if (data[0].equals("ENTER")) {

                String name = data[1];
                double cgpa = Double.parseDouble(data[2]);
                int id = Integer.parseInt(data[3]);

                queue.add(new Student(id, name, cgpa));

            } else if (data[0].equals("SERVED")) {

                if (!queue.isEmpty()) {
                    queue.poll();
                }
            }
        }

        List<Student> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }
}

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        List<String> events = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            events.add(sc.nextLine());
        }

        Priorities priorities = new Priorities();

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student student : students) {
                System.out.println(student.getName());
            }
        }

        sc.close();
    }
}
