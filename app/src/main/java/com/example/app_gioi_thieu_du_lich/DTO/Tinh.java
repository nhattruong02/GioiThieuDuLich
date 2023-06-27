package com.example.app_gioi_thieu_du_lich.DTO;

public class Tinh {
    private int matinh;
    private String tentinh;
    private byte[] hinh;

    public Tinh(int matinh, String tentinh, byte[] hinh) {
        this.matinh = matinh;
        this.tentinh = tentinh;
        this.hinh = hinh;
    }

    public int getMatinh() {
        return matinh;
    }

    public void setMatinh(int matinh) {
        this.matinh = matinh;
    }

    public String getTentinh() {
        return tentinh;
    }

    public void setTentinh(String tentinh) {
        this.tentinh = tentinh;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
