/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QUANLYPHONGTRO;

import BANGMDEOL.NGUOIDUNGM;
import CAUTRUCLUUTRU.LinkedList;
import java.util.Scanner;

/**
 *
 * @author chinh
 */
public class QUANLYNGUOIDUNG extends NGUOIDUNGM{

    public QUANLYNGUOIDUNG() {
    }

    public QUANLYNGUOIDUNG(String tendangnhap, String matkhau) {
        super(tendangnhap, matkhau);
    }
    
    public void hamnHIENTHITHONGTINND(){
        System.out.printf("|%10s|%10s|%10s|%20s|%20s\n", tendangnhap,matkhau);
    }
    public String dangnhap(LinkedList linkedList){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten dang nhap: ");
        String tendangnhap =  sc.next();
        System.out.println("Nhap mat khau: ");
        String matkhau = sc.next();
        
        if(linkedList.checkmatkhau(matkhau, tendangnhap) !=null)
            return tendangnhap;
        else
            System.out.println("Khong dung !!!");
            return  null;
        
    }
}
