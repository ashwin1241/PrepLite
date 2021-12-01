package com.PrepLite.models;

import android.net.Uri;

public class Attachment {

    private String title;
    private boolean downloadStat;
    private Uri downloadedFile;

    public Attachment(String title, boolean downloadStat, Uri downloadedFile) {
        this.title = title;
        this.downloadStat = downloadStat;
        this.downloadedFile = downloadedFile;
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

    public Uri getDownloadedFile() {
        return downloadedFile;
    }

    public void setDownloadedFile(Uri downloadedFile) {
        this.downloadedFile = downloadedFile;
    }
}
