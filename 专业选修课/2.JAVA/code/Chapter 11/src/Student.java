public class Student extends Person{
    private final String class_state;

    public Student(String name, String address, String tel, String email, String class_state) {
        super(name, address, tel, email);
        this.class_state = class_state;
    }

//    public void setClass_state(String class_state) {
//        this.class_state = class_state;
//    }
    public String toString(){
        return super.toString()+"\nclass_state:"+class_state;
    }
}
