/**
 * 14.4
 */
public class House2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        House house1 = new House(3, 100);
        House house2 = (House) house1.clone();
        System.out.println(house1.getWhenBuilt());
        System.out.println(house2.getWhenBuilt());
        System.out.println(house1.getWhenBuilt() == house2.getWhenBuilt());
    }
}

class House implements Cloneable, Comparable {
    private int id;
    private double area;
    private java.util.Date whenBuilt;

    public House(int id, double area) {
        this.id = id;
        this.area = area;
        whenBuilt = new java.util.Date();
    }

    public double getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public java.util.Date getWhenBuilt() {
        return whenBuilt;
    }


//    public Object clone() {
//        try {
//            House house = (House)super.clone();
//            house.whenBuilt = (java.util.Date)(whenBuilt.clone());
//            return house;
//        }
//        catch (CloneNotSupportedException ex) {
//            return null;
//        }
//    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        House house = (House) super.clone();
        house.whenBuilt = (java.util.Date) (whenBuilt.clone());
        return house;
    }

    @Override
    public int compareTo(Object o) {
        if (area > ((House) o).area)
            return 1;
        else if (area < ((House) o).area)
            return -1;
        else
            return 0;
    }
}