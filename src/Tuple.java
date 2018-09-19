public class Tuple<K, V>{
    private K first;
    private V second;

    public Tuple(K first, V second){
        this.first = first;
        this.second = second;
    }

    public K getFirst(){
        return this.first;
    }

    public V getSecond(){
        return this.second;
    }

}
