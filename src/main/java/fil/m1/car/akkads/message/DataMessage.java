package fil.m1.car.akkads.message;

public class DataMessage implements Message {
    
    private String content;

    public DataMessage(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
}
