public class GenericSort {
    public static void main(String[] args) {
        String[] list = new String[]{"awd","sadsa","fsdf"};
        sort(list);
        for (int i =0;i<list.length;i++){
            System.out.print(list[i]+" ");
        }
    }
    public static void sort(Comparable[] list){
        Comparable currentMin;
        int currentMinIndex;
        for (int i=0;i<list.length-1;i++){
            currentMin = list[i];
            currentMinIndex = i;
            for (int j=i+1;j<list.length;j++){
                if (currentMin.compareTo(list[j])>0){
                    currentMinIndex = j;
                    currentMin = list[j];
                }
            }
            if (currentMinIndex !=i){
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }
}
