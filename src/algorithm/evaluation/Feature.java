package algorithm.evaluation;

public class Feature {
    private int value;
    private int weight;

    public Feature(int value, int weight){
        this.value = value;
        this.weight = weight;
    }

    int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
