package putitout.zipjetclone.ui.model;

import java.io.Serializable;

public class RequestModel implements Serializable {

    private String image;
    private String name;
    private String relation;
    private String senderId;
    private String receiverId;
    private String requestId;

    public RequestModel(String image, String name, String relation, String senderId, String receiverId, String requestId) {
        this.image = image;
        this.name = name;
        this.relation = relation;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.requestId = requestId;
    }

    public String getImageName() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", relation='" + relation + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}
