package putitout.zipjetclone.ui.model;


import putitout.zipjetclone.ui.util.ZUtil;

public class Contacts {

    private String name;
    private String email;
    private int relationShip = ZUtil.KEY_NO_RELATIONSHIP;

    public Contacts(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(int relationShip) {
        this.relationShip = relationShip;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
