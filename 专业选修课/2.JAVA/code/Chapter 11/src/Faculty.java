public class Faculty extends Employee{
    private int office_time;
    private int level;

    public Faculty(String name, String address, String tel, String email, double salary, int office, MyDate employed_date, int office_time, int level) {
        super(name, address, tel, email, salary, office, employed_date);
        this.office_time = office_time;
        this.level = level;
    }


    @Override
    public String toString() {
        return super.toString()+"Faculty{" +
                "office_time='" + office_time + '\'' +
                ", level=" + level +
                '}';
    }
}
