public class Staff extends Employee{
    private String title;

    public Staff(String name, String address, String tel, String email, double salary, int office, MyDate employed_date, String title) {
        super(name, address, tel, email, salary, office, employed_date);
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString()+"Staff{" +
                "title='" + title + '\'' +
                '}';
    }
}
