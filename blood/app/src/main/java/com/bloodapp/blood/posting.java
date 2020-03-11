package com.bloodapp.blood;

public class posting {
    private String title, desc, image, userName;

    public posting()
    {

    }

    public posting(String title, String desc, String image, String userName)
    {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.userName= userName;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public String getUserName() {
        return userName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
