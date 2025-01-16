import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sample {

    public static void main(String[] args) {

        record Employee(
                String name,
                String dept,
                double salary,
                int age,
                List<String> emails) {
        }

        record EmployeeDTO(String name, int age) {
        }

        List<Employee> employees = List.of(
                new Employee("Atul", "Engg", 200000.00, 33,Arrays.asList("atul.mahajan@saama.com", "mahajanatul525@gmail.com")),
                new Employee("Rahul", "Hr", 200000.00, 56,Arrays.asList("rtul.mahajan@saama.com", "mahajanatul525@gmail.com")),
                new Employee("Venkat", "Hr", 200000.00, 23,Arrays.asList("etul.mahajan@saama.com", "mahajanatul525@gmail.com")),
                new Employee("Rudra", "Sales", 200000.00, 67,Arrays.asList("ttul.mahajan@saama.com", "mahajanatul525@gmail.com")),
                new Employee("Yash", "Engg", 200000.00, 22,Arrays.asList("qtul.mahajan@saama.com", "mahajanatul525@gmail.com")));

    
        // Sort Employee by multiple fields
        List<Employee> sortEmployee = employees.stream()
                .sorted(Comparator.comparing(Employee::age).thenComparing(Employee::name)).collect(Collectors.toList());

        sortEmployee.forEach(System.out::println);

        // Map Employee with grouping
        Map<String, List<EmployeeDTO>> dto = employees.stream().collect(Collectors.groupingBy(Employee::dept,
                Collectors.mapping(emp -> new EmployeeDTO(emp.name, emp.age), Collectors.toList())));

        System.out.print(dto);

        Map<String, List<String>> employeeNames = employees.stream().collect(
                Collectors.groupingBy(Employee::dept, Collectors.mapping(Employee::name, Collectors.toList())));
        System.out.println(employeeNames);

        // group by dept
        Map<String, List<Employee>> employeesByDept = employees.stream().collect(Collectors.groupingBy(Employee::dept));
        System.out.println(employeesByDept);

        // avarage salary by dept
        Map<String, Double> avarageSalarybyDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept, Collectors.averagingDouble(Employee::salary)));
        System.out.println(avarageSalarybyDept);

        // summing salary by dept
        Map<String, Double> sumSalarybyDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept, Collectors.summingDouble(Employee::salary)));
        System.out.println(sumSalarybyDept);

        // number of employee by dept
        Map<String, Long> nbEmployeeByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept, Collectors.counting()));
        System.out.println(nbEmployeeByDept);

        // filter employee having age 33
        List<Employee> employeeswithageless33 = employees.stream().filter(e -> e.age <= 33)
                .collect(Collectors.toList());
        System.out.println(employeeswithageless33);

        Map<Boolean, List<Employee>> partitioning = employees.stream().filter(e -> e.age <= 33)
                .collect(Collectors.partitioningBy(e -> e.age == 33));
        System.out.println(partitioning);


        employees.stream().collect(Collectors.groupingBy(Employee::dept, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted(Comparator.comparing(Employee::name)).toList())));

    }
}
