/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BANGMDEOL;

import java.util.Date;

/**
 *
 * @author chinh
 */
public class THUEPHONG {
    public int mathuephong;
    public String makh;
    public int id_p;
    public Date ngaythue;
    public String trangthai;

    public THUEPHONG(int mathuephong, String makh, int id_p, Date ngaythue, String trangthai) {
        this.mathuephong = mathuephong;
        this.makh = makh;
        this.id_p = id_p;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
    }

    public THUEPHONG() {
    }

    public int getMathuephong() {
        return mathuephong;
    }

    public void setMathuephong(int mathuephong) {
        this.mathuephong = mathuephong;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public Date getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(Date ngaythue) {
        this.ngaythue = ngaythue;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

   
    
}
