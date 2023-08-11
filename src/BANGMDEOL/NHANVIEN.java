/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BANGMDEOL;

import java.sql.Date;
import java.util.regex.Pattern;

/**
 *
 * @author chinh
 */
public class NHANVIEN {
    public String manv;
    public String hotennv;
    public Date ngaysinh;
    public String gioitinh;
    public float lương;
    public  String email;
    public String sdt;
    public String diachi;
    public String trangthai;

    public NHANVIEN() {
    }

    public NHANVIEN(String manv, String hotennv, Date ngaysinh, String gioitinh, float lương, String email, String sdt, String diachi, String trangthai) {
        this.manv = manv;
        this.hotennv = hotennv;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.lương = lương;
        this.email = email;
        this.sdt = sdt;
        this.diachi = diachi;
        this.trangthai = trangthai;
    }

    public String getManv() {
        return manv;
    }

    public boolean setManv(String manv) {
		Pattern p = Pattern.compile("^C[0-9]{7}$");
		if(p.matcher(manv).find()) {
			this.manv = manv;
			return true;
		} 
		else {
			System.err.println("ma nhan vien (Cxxxxxx) (x la so): ");
			return false;
		}
		
	}

    public String getHotennv() {
        return hotennv;
    }

    public void setHotennv(String hotennv) {
        this.hotennv = hotennv;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public float getLương() {
        return lương;
    }

    public void setLương(float lương) {
        this.lương = lương;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

 
 

}
