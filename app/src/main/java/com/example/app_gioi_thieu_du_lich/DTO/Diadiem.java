package com.example.app_gioi_thieu_du_lich.DTO;

public class Diadiem {
    private int madiadiem;
    private String tendiadiem;
    private String vitri;
    private String mota;
    private byte[] hinhdiadiem;
    private String linkvideo;
    private int matinh;

    public Diadiem(int madiadiem, String tendiadiem, String vitri, String mota, byte[] hinhdiadiem, String linkvideo, int matinh) {
        this.madiadiem = madiadiem;
        this.tendiadiem = tendiadiem;
        this.vitri = vitri;
        this.mota = mota;
        this.hinhdiadiem = hinhdiadiem;
        this.linkvideo = linkvideo;
        this.matinh = matinh;
    }

    public int getMadiadiem() {
        return madiadiem;
    }

    public void setMadiadiem(int madiadiem) {
        this.madiadiem = madiadiem;
    }

    public String getTendiadiem() {
        return tendiadiem;
    }

    public void setTendiadiem(String tendiadiem) {
        this.tendiadiem = tendiadiem;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public byte[] getHinhdiadiem() {
        return hinhdiadiem;
    }

    public void setHinhdiadiem(byte[] hinhdiadiem) {
        this.hinhdiadiem = hinhdiadiem;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }

    public int getMatinh() {
        return matinh;
    }

    public void setMatinh(int matinh) {
        this.matinh = matinh;
    }
}
