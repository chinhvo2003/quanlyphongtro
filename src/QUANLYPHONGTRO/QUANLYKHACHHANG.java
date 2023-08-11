    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QUANLYPHONGTRO;

import BANGMDEOL.KHACHHANG;
import CAUTRUCLUUTRU.DoubleLinkedList;
import CAUTRUCLUUTRU.NodeDoubleL;
import CONTENT.ConnectJDBCC;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author chinh
 */
public class QUANLYKHACHHANG extends KHACHHANG{
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public QUANLYKHACHHANG() {
    }

    public QUANLYKHACHHANG(String makh, String hotenkh, String sdt, Date ngaysinh, String cmnd, String gioitinh, String quequan, int maphong, Date ngayvao, String trangthai) {
        super(makh, hotenkh, sdt, ngaysinh, cmnd, gioitinh, quequan, maphong, ngayvao, trangthai);
    }

    

   
    
    public void hienthithongtinkhachhang() {
		System.out.printf("|%8s|%10s|%10s|%10s|%10s|%5s|%20s|%5s|%10s|%15s|\n", makh,hotenkh,ngaysinh,cmnd,sdt,gioitinh,quequan,maphong,ngayvao,trangthai);
	}
    
    public static void hienthitieude() {
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|  MA KH  |     HO TEN KH     |NGAY SINH|  CMND  |   SĐT   |GIOI TINH|     QUE QUAN     |MA PHONG|  NGAY VAO  |TRANG THAI| ");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
	}
    public void nhapthongtinkh(DoubleLinkedList doubleLinkedList, NodeDoubleL nodeDoubleL){
        df.setLenient(false);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Nhap ma khach hang: ");
            String makh1 = sc.nextLine();
            boolean check=setMakh(makh1);
            if(check){
                NodeDoubleL cuDoubleL = doubleLinkedList.checkkh(nodeDoubleL, makh1);
				if(cuDoubleL != null) 
					System.err.println("Khach hang ton tai !!!!");
				else
					break;
                     }
            }
            System.out.println("Nhap ho ten khach hang: ");
            hotenkh = sc.nextLine();
            while(true) {
			try {
				System.out.println("Nhap ngay sinh(yyyy-MM-dd): ");
				ngaysinh = df.parse(sc.nextLine());
				break;
			} catch (ParseException e) {
				System.err.println("Nhap sai dinh dang (yyyy-MM-dd): ");
			}
		}
            System.out.println("Nhap cmnd: ");
            cmnd = sc.nextLine();
            System.out.println("Nhap sdt: ");
            sdt = sc.nextLine();
            System.out.println("Nhap gioi tinh: ");
            while(!setGioitinh(sc.nextLine()));
            System.out.println("Nhap que quan:");
            quequan = sc .nextLine();
//            System.out.println("Nhap ma phong: ");
//            maphong = sc.nextInt();
            while(true) {
			try {
				System.out.println("Nhap ngay vào(yyyy-MM-dd): ");
				ngayvao = df.parse(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("Nhap sai dinh dang (yyyy-MM-dd): ");
                        }
        }
        trangthai = "DANG THUE";
    }
    public static void hamnhapthongtinkhachhang(DoubleLinkedList doubleLinkedList, NodeDoubleL nodeDoubleL) throws ClassNotFoundException, SQLException{
        QUANLYKHACHHANG quanlykhachhang = new QUANLYKHACHHANG();
        quanlykhachhang.nhapthongtinkh(doubleLinkedList, nodeDoubleL);
        NodeDoubleL nodeDoubleL1 =new NodeDoubleL(quanlykhachhang);
        doubleLinkedList.insertTail(nodeDoubleL1);
    }
}
