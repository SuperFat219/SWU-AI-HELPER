public class TestPerson {
    public static void main(String[] args) {
        Person person = new Person("小王","公园道1号","13838389289","18191@163.com");
        System.out.println(person.toString());
        Student student = new Student("小李","公园道3号","171291","189@sina.com","大一");
        System.out.println(student.toString());
        MyDate myDate = new MyDate(2021,8,1);
        Employee employee = new Employee("打工人","西南大学","18391283","12831@qq.com",3000,306,myDate);
        System.out.println(employee.toString());
        Faculty faculty = new Faculty("老王","公园道12号","12381","1239821@swu.edu.cn",4000,205,myDate,3,2);
        System.out.println(faculty.toString());
        Staff staff = new Staff("老李","street 12","12937","12381@icloud.com",5000,301,myDate,"最佳模范员工");
        System.out.println(staff.toString());
    }
}
