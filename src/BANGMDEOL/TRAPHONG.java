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
public class TRAPHONG {
     public int matraphong;
    public String makh;
    public int id_p;
    public Date ngaytra;
    public String lydo;

    public TRAPHONG() {
    }

    public TRAPHONG(int matraphong, String makh, int id_p, Date ngaytra, String lydo) {
        this.matraphong = matraphong;
        this.makh = makh;
        this.id_p = id_p;
        this.ngaytra = ngaytra;
        this.lydo = lydo;
    }

    public int getMatraphong() {
        return matraphong;
    }

    public void setMatraphong(int matraphong) {
        this.matraphong = matraphong;
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

    public Date getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(Date ngaytra) {
        this.ngaytra = ngaytra;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    
}
