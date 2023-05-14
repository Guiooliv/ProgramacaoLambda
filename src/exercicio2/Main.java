package exercicio2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter file full path: ");
        String path = scan.nextLine();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while(line != null){
                String[] filds = line.split(",");
                list.add(new Employee(filds[0], filds[1], Double.parseDouble(filds[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            double salary = scan.nextDouble();

            List<String> emails = list.stream()
                    .filter(p -> p.getSalary() > salary)
                    .map(p -> p.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
            emails.forEach(System.out::println);

            double sum = list.stream()
                   .filter(x -> x.getNome().charAt(0) == 'M')
                   .map(x -> x.getSalary())
                   .reduce(0.0, (x,y) -> x + y);
            System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scan.close();
    }
}
