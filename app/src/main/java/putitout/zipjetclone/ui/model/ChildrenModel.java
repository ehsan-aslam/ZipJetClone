package putitout.zipjetclone.ui.model;

import java.io.Serializable;

public class ChildrenModel implements Serializable {
    String childId;
    String name;
    String ageYear;
    String ageMonth;
    String ageDay;
    String birthday;
    String weight;
    String height;
    String gender;
    String image;

    public ChildrenModel() {
    }

    public ChildrenModel(String childId, String name, String ageYear, String ageMonth, String ageDay, String birthday, String weight, String height, String gender, String image) {
        this.childId = childId;
        this.name = name;
        this.ageYear = ageYear;
        this.ageMonth = ageMonth;
        this.ageDay = ageDay;
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.image = image;
    }

    public String getAgeYear() { return ageYear; }

    public void setAgeYear(String ageYear) {
        this.ageYear = ageYear;
    }

    public String getAgeMonth() {
        return ageMonth;
    }

    public void setAgeMonth(String ageMonth) {
        this.ageMonth = ageMonth;
    }

    public String getAgeDay() {
        return ageDay;
    }

    public void setAgeDay(String ageDay) {
        this.ageDay = ageDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) { this.height = height; }

    public String getGender() { return gender; }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ChildrenModel{" +
                "childId='" + childId + '\'' +
                ", name='" + name + '\'' +
                ", ageYear='" + ageYear + '\'' +
                ", ageMonth='" + ageMonth + '\'' +
                ", ageDay='" + ageDay + '\'' +
                ", birthday='" + birthday + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
