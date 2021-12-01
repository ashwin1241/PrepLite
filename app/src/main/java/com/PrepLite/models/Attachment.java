package com.PrepLite.models;

public class Attachment {

    private String title;
    private boolean downloadStat;

    public Attachment(String title, boolean downloadStat) {
        this.title = title;
        this.downloadStat = downloadStat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDownloadStat() {
        return downloadStat;
    }

    public void setDownloadStat(boolean downloadStat) {
        this.downloadStat = downloadStat;
    }
}
