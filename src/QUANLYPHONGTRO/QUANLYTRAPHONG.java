/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QUANLYPHONGTRO;

import BANGMDEOL.TRAPHONG;
import CAUTRUCLUUTRU.DoubleLinkedList;
import CAUTRUCLUUTRU.NodeDoubleL;
import CAUTRUCLUUTRU.TreeNode;
import CONTENT.ConnectJDBCC;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author chinh
 */
public class QUANLYTRAPHONG extends TRAPHONG{
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public QUANLYTRAPHONG() {
    }

    public QUANLYTRAPHONG(int matraphong, String makh, int id_p, Date ngaytra, String lydo) {
        super(matraphong, makh, id_p, ngaytra, lydo);
    }

   
     public void hienthithongtintraphong(){
        System.out.printf("|%8s|%8s|%5s|%15s|%37s\n", matraphong,makh,id_p,ngaytra,lydo);
    }
    
    public void hienthitieudetraphong(){
        System.out.println("-----------------------------------------------------------------------------");
	System.out.println("|Ma Tra Phong| Ma KH  |ID_P|  Ngay Tra  |            Ly do            |");
	System.out.println("-----------------------------------------------------------------------------");
    }
    public void hamhienthitraphong(ArrayList<QUANLYTRAPHONG> lsQuanlytraphongs){
        hienthitieudetraphong();
        for(QUANLYTRAPHONG lQuanlytraphong : lsQuanlytraphongs){
            lQuanlytraphong.hienthithongtintraphong();
        }
    }
    public QUANLYTRAPHONG sheartrphong(ArrayList<QUANLYTRAPHONG> listqQuanlytraphongs,String makh){
        for(int i=0;i<listqQuanlytraphongs.size();i++){
          if(listqQuanlytraphongs.get(i).getMakh().equals(makh)){
              return listqQuanlytraphongs.get(i);
          }  
        }
        return null;
    }
    
    public void trphong(TreeNode treeNode, DoubleLinkedList dLL, NodeDoubleL node, ArrayList<QUANLYTRAPHONG> lstqQuanlytraphongs) throws ClassNotFoundException, SQLException{
        ConnectJDBCC dao = new ConnectJDBCC();
        Scanner sc = new Scanner(System.in);
//        String makh;
//        int maphong;
//        Date ngaytra;
        QUANLYPHONG quanlyphong = new QUANLYPHONG();
        while(true){
            try {
                System.out.println("Nhap ma tra phong:");
            matraphong = Integer.parseInt(sc.nextLine());
            System.out.println("Nhap ma khach hang muon trả:");
            makh = sc.nextLine();
            if(node.qlQuanlykhachhang.setMakh(makh)){
                NodeDoubleL nodeDoubleL = dLL.checkkh(node, makh);
                if(nodeDoubleL !=null){
                    nodeDoubleL.qlQuanlykhachhang.hienthitieude();
                    nodeDoubleL.qlQuanlykhachhang.hienthithongtinkhachhang();
                    System.out.println("Nhap ly do muon tra:");
                    lydo = sc.nextLine();
                    while(true){
                        try {
                            System.out.println("Nhap ngay tra:");
                            df.setLenient(false);
                            ngaytra = df.parse(sc.nextLine());
                            if(ngaytra.after(nodeDoubleL.qlQuanlykhachhang.getNgayvao()))
                                break;
                            else
                                System.out.println("Ngay tra khong be hon ngay vao!!!");
                        } catch (Exception e) {
  				System.err.println("vUI long nhap dung dinh dang (yyyy-MM-dd)");
                        }
                    }
                    int chon;
                    while(true){
                        try {
                            System.out.println("Ban co chac chan muon tra phong:1. Co || 2. Khong");
                            chon = Integer.parseInt(sc.nextLine());
                            if(chon==1 || chon ==2)
                                break;
                            else
                                System.out.println("Bna chon sai!!!");
                        } catch (Exception e) {
                            System.out.println("Ban da chon sai !!!");
                        }
                    }
                    id_p = nodeDoubleL.qlQuanlykhachhang.getMaphong();
                    switch(chon){
                        case 1:{
                            // chuyen tran thai
                            nodeDoubleL.qlQuanlykhachhang.setTrangthai("DA TRA");
                            dao.capnhatkhachhang(nodeDoubleL.qlQuanlykhachhang, makh, id_p);
                            //giam so luong trong phong
                            TreeNode treeNode1 = quanlyphong.chekphong(treeNode, id_p);
                            if(treeNode1 !=null){
                                treeNode1.qlp.setSonguoi(treeNode1.qlp.getSonguoi()-1);
                                dao.suaphong(treeNode1.qlp, id_p);
                            }
                            /// luuu danh sach tra phong
                            QUANLYTRAPHONG quanlytraphong = new QUANLYTRAPHONG();
                            quanlytraphong.setMatraphong(matraphong);
                            quanlytraphong.setMakh(makh);
                            quanlytraphong.setId_p(id_p);
                            quanlytraphong.setNgaytra(ngaytra);
                            quanlytraphong.setLydo(lydo);
                            lstqQuanlytraphongs.add(quanlytraphong);
                            dao.inserttraphong(lstqQuanlytraphongs.get(lstqQuanlytraphongs.size()-1));
                            hamhienthitraphong(lstqQuanlytraphongs);
                            System.out.println("Tra phong thanh cong !!!");
                            break;
                        }
                        case 2:{
                            System.out.println("Khong tra phong !!!");
                            break;
                        }
                    }
                    break;
                }else
                    System.out.println("Khach hang ko ton tai !!!");
            }
            } catch (Exception e) {
            }
        }
    }
}
