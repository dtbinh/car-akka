package fil.m1.car.akkads.message;

public class DataMessage implements Message {
    
    private static final long serialVersionUID = 1L;
    private String content;
    
    public DataMessage() {
    }

    public DataMessage(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
}
