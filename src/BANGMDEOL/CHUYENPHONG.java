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
public class CHUYENPHONG {
    public int machuyenphong;
    public String makh;
    public int id_pcu, id_pmoi;
    public Date ngaychuyen;
    public String lydo;

    public CHUYENPHONG() {
    }

    public CHUYENPHONG(int machuyenphong, String makh, int id_pcu, int id_pmoi, Date ngaychuyen, String lydo) {
        this.machuyenphong = machuyenphong;
        this.makh = makh;
        this.id_pcu = id_pcu;
        this.id_pmoi = id_pmoi;
        this.ngaychuyen = ngaychuyen;
        this.lydo = lydo;
    }

    public int getMachuyenphong() {
        return machuyenphong;
    }

    public void setMachuyenphong(int machuyenphong) {
        this.machuyenphong = machuyenphong;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public int getId_pcu() {
        return id_pcu;
    }

    public void setId_pcu(int id_pcu) {
        this.id_pcu = id_pcu;
    }

    public int getId_pmoi() {
        return id_pmoi;
    }

    public void setId_pmoi(int id_pmoi) {
        this.id_pmoi = id_pmoi;
    }

    public Date getNgaychuyen() {
        return ngaychuyen;
    }

    public void setNgaychuyen(Date ngaychuyen) {
        this.ngaychuyen = ngaychuyen;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }
    
    
}
