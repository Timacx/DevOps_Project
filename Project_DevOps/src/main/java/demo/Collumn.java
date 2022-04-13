public class Collumn<T> {
    
    private String label;
    private T[] values;

    Collumn(String lab, T[] data){
        this.values = data;
        this.label = lab;
    }

    public T[] getValues() {
        return values;
    }

    public String getLabel() {
        return label;
    }
}
