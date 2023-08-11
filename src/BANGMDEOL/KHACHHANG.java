/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BANGMDEOL;

import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author chinh
 */
public class KHACHHANG {
    
    
    public String makh;
    public String hotenkh;
    public String sdt;
    public Date ngaysinh;
    public String cmnd;
    public String gioitinh;
    public String quequan;
    public int maphong;
    public Date ngayvao;
    public String trangthai;

    public KHACHHANG() {
    }

    public KHACHHANG(String makh, String hotenkh,  String sdt, Date ngaysinh, String cmnd, String gioitinh, String quequan, int maphong, Date ngayvao, String trangthai) {
        this.makh = makh;
        this.hotenkh = hotenkh;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.cmnd = cmnd;
        this.gioitinh = gioitinh;
        this.quequan = quequan;
        this.maphong = maphong;
        this.ngayvao = ngayvao;
        this.trangthai = trangthai;
    }

   

    public String getMakh() {
        return makh;
    }

    public boolean setMakh(String makh) {
        Pattern p = Pattern.compile("^C[0-9]{7}$");
		if(p.matcher(makh).find()) {
			this.makh = makh;
			return true;
		} 
		else {
			System.err.println("Ma kh co dang (Cxxxxxx) (x la so): ");
			return false;
		}
    }

    public String getHotenkh() {
        return hotenkh;
    }

    public void setHotenkh(String hotenkh) {
        this.hotenkh = hotenkh;
    }

    
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getGioitinh() {
        return gioitinh;
    }

   public boolean setGioitinh(String gioitinh) {
		if(gioitinh.equals("NAM") || gioitinh.equals("NU")) {
			this.gioitinh = gioitinh;
			return true;
		} else {
			System.err.println("Nam hoac nu");
			return false;
		}
	}
    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public Date getNgayvao() {
        return ngayvao;
    }

    public void setNgayvao(Date ngayvao) {
        this.ngayvao = ngayvao;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    
    
    
    
    
}
