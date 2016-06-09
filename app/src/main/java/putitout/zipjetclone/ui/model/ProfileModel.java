package putitout.zipjetclone.ui.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileModel implements Serializable {
    private String id;
    private String gender;
    private String name;
    private String image;
    private ArrayList<ChildrenModel> childrenList = new ArrayList<ChildrenModel>();
    private RequestModel friendRequestModel;
    private RequestModel requestRequestModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<ChildrenModel> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(ArrayList<ChildrenModel> childrenList) {
        this.childrenList = childrenList;
    }

    public RequestModel getFriendRequestModel() {
        return friendRequestModel;
    }

    public void setFriendRequestModel(RequestModel friendRequestModel) {
        this.friendRequestModel = friendRequestModel;
    }

    public RequestModel getRequestRequestModel() {
        return requestRequestModel;
    }

    public void setRequestRequestModel(RequestModel requestRequestModel) {
        this.requestRequestModel = requestRequestModel;
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "id='" + id + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", childrenList=" + childrenList +
                '}';
    }
}
