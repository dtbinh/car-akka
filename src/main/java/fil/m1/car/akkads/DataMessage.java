package fil.m1.car.akkads;

public class DataMessage {
    
    private String content;

    public DataMessage(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public String toString() {
        return content;
    }

}
