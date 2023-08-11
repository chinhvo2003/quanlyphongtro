/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BANGMDEOL;

/**
 *
 * @author chinh
 */
public class PHONG {
    
    public int maphong, sogiuong,songuoi;
    public String loaiphong;
    public float giatien;

    public PHONG() {
    }

    public PHONG(int maphong, int sogiuong, int songuoi, String loaiphong, float giatien) {
        this.maphong = maphong;
        this.sogiuong = sogiuong;
        this.songuoi = songuoi;
        this.loaiphong = loaiphong;
        this.giatien = giatien;
    }

    public int getMaphong() {
        return maphong;
    }

    public boolean setMaphong(int maphong) {
       if(maphong >=1 && maphong<=500){
           this.maphong=maphong;
           return true;
       }else{
           System.out.println("Ma phong gom 3 so !!!");
           return false;
       }
    }

    public int getSogiuong() {
        return sogiuong;
    }

    public void setSogiuong(int sogiuong) {
        this.sogiuong = sogiuong;
    }

    public int getSonguoi() {
        return songuoi;
    }

    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public boolean setLoaiphong(String loaiphong) {
        if(loaiphong.equals("Phòng trọ nhỏ") || loaiphong.equals("Phòng trọ thông") || loaiphong.equals("Phòng trọ rộng") ){
            this.loaiphong=loaiphong;
            return true;
        }else{
            System.out.println("Chọn một trong 3 loại trọ !!!");
            return false;
        }
    }

    public float getGiatien() {
        return giatien;
    }

    public void setGiatien(float giatien) {
        this.giatien = giatien;
    }
    
    
}
