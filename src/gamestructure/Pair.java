package gamestructure;

public class Pair<Key, Value> {
    private final Key key;
    private final Value value;

    /**
     * 
     * @param key
     * @param value
     */
    public Pair(final Key key, final Value value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 
     * @return .
     */
    public Key getKey() {
        return this.key;
    }

    /**
     * 
     * @return .
     */
    public Value getValue() {
        return this.value;
    }
}
