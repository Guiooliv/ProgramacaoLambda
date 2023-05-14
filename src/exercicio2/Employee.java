package exercicio2;

public class Employee {
    private String email;
    private String nome;
    private Double salary;

    public Employee(String nome, String email, Double salary){
        this.email = email;
        this.nome = nome;
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
