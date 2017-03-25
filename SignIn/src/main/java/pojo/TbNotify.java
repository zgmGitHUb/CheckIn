package pojo;

import java.util.Date;

public class TbNotify {
    private String notifyid;

    private String content;

    private Date notifytime;

    public String getNotifyid() {
        return notifyid;
    }

    public void setNotifyid(String notifyid) {
        this.notifyid = notifyid == null ? null : notifyid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getNotifytime() {
        return notifytime;
    }

    public void setNotifytime(Date notifytime) {
        this.notifytime = notifytime;
    }
}