package Task3;

public class Apple implements Fruit{
    private float weight = 1.0f;

    public Apple() {}
    public Apple(float weight) {
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}
