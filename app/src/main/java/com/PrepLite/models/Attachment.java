package com.PrepLite.models;

import android.net.Uri;

public class Attachment {

    private String title;
    private boolean downloadStat;
    private Uri downloadedFile;
    private String filePath;

    public Attachment(String title, boolean downloadStat, Uri downloadedFile, String filePath) {
        this.title = title;
        this.downloadStat = downloadStat;
        this.downloadedFile = downloadedFile;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
