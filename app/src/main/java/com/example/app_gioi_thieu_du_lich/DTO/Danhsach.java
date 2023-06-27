package com.example.app_gioi_thieu_du_lich.DTO;

public class Danhsach {
    private int madiadiem;
    private String tendiadiem;
    private String vitri;
    private byte[] hinh;

    public Danhsach(int madiadiem, String tendiadiem, String vitri, byte[] hinh) {
        this.madiadiem = madiadiem;
        this.tendiadiem = tendiadiem;
        this.vitri = vitri;
        this.hinh = hinh;
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

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
