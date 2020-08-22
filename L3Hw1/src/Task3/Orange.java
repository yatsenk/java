package Task3;

public class Orange implements Fruit {
    private float weight = 1.5f;

    public Orange() {}
    public Orange(float weight) {
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}
