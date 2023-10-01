public class Employee extends Person{
    private double salary;
    private int office;
    private MyDate employed_date;

    public Employee() {
    }

    public Employee(String name, String address, String tel, String email, double salary, int office, MyDate employed_date) {
        super(name, address, tel, email);
        this.salary = salary;
        this.office = office;
        this.employed_date = employed_date;
    }

    @Override
    public String toString() {
        return super.toString()+"\nEmployee{" +
                "salary=" + salary +
                ", office=" + office +
                ", employed_data='" + employed_date + '\'' +
                '}';
    }
}
