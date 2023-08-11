/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QUANLYPHONGTRO;

import BANGMDEOL.CHUYENPHONG;
import CAUTRUCLUUTRU.DoubleLinkedList;
import CAUTRUCLUUTRU.NodeDoubleL;
import CAUTRUCLUUTRU.TreeNode;
import CONTENT.ConnectJDBCC;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author chinh
 */
public class QUANLYCHUYENPHONG extends CHUYENPHONG{
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public QUANLYCHUYENPHONG() {
    }

    public QUANLYCHUYENPHONG(int machuyenphong, String makh, int id_pcu, int id_pmoi, Date ngaychuyen, String lydo) {
        super(machuyenphong, makh, id_pcu, id_pmoi, ngaychuyen, lydo);
    }
     public void hienthithongtinchuyenphong(){
        System.out.printf("|%8s|%8s|%5s|%5s|%15s|%37s\n", machuyenphong,makh,id_pcu,id_pmoi,ngaychuyen,lydo);
    }
    
    public void hienthitieudechuyenphong(){
        System.out.println("---------------------------------------------------------------------------------------");
	System.out.println("|Ma Chuyen Phong| Ma KH  |ID_PCU|ID_PMOI|  Ngay Chuyen  |            Ly do            |");
	System.out.println("---------------------------------------------------------------------------------------");
    }
    
    public void hamhienthichuyenphong(ArrayList<QUANLYCHUYENPHONG> listchArrayList){
        hienthitieudechuyenphong();
        for(QUANLYCHUYENPHONG lQuanlychuyenphong : listchArrayList){
        lQuanlychuyenphong.hienthithongtinchuyenphong();
        }
    }
    public QUANLYCHUYENPHONG sherachuyenphong(String makh,ArrayList<QUANLYCHUYENPHONG> listchArrayList){
        for(int i=0;i<listchArrayList.size();i++){
            if(listchArrayList.get(i).getMakh().equalsIgnoreCase(makh)){
            return listchArrayList.get(i);
            }
        }
        return null;
    }
    public void chuyenphong(TreeNode treeNode, DoubleLinkedList dLL, NodeDoubleL node,ArrayList<QUANLYCHUYENPHONG> listchArrayLists) throws ClassNotFoundException, SQLException, ParseException{
            ConnectJDBCC dao = new ConnectJDBCC();
            Scanner sc = new Scanner(System.in);
            String makh; int maphong;
            Date ngaychuyen ; String lydo;
            QUANLYPHONG quanlyphong = new QUANLYPHONG();
            QUANLYCHUYENPHONG quanlychuyenphong = new QUANLYCHUYENPHONG();
            NodeDoubleL nodeDoubleL;
            TreeNode treeNode1;
            while(true){
                try {
                    System.out.println("Nhap ma chuyen phong: ");
                    machuyenphong = Integer.parseInt(sc.nextLine());
                    System.out.println("Nhap ma khach hang muon chuyen : ");
                    makh = sc.nextLine();
                    if(node.qlQuanlykhachhang.setMakh(makh)){
                        nodeDoubleL = dLL.checkkh(node, makh);
                     if(nodeDoubleL !=null){
                         nodeDoubleL.qlQuanlykhachhang.hienthitieude();
                         nodeDoubleL.qlQuanlykhachhang.hienthithongtinkhachhang();
                         break;
                     }   else{
                         System.out.println("Khach hang nay khong ton tai!");
                     }
                    }
                } catch (Exception e) {
                }
            }
            while(true){
                try {
                    System.out.println("Nhap ma phong ban muon chuyen toi :");
                    maphong = Integer.parseInt(sc.nextLine());
                    if(quanlyphong.setMaphong(maphong)){
                        // chek da dur nguoi chuwa
                        treeNode1 = quanlyphong.checkFullPhong(treeNode, maphong);
                        if(treeNode1 !=null){
                            if(nodeDoubleL.qlQuanlykhachhang.getMaphong() == maphong){
                              System.out.println("Khach hang da ow phong nay:");
                            
                            }else
                                break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Nhap sai vui long nhap lai !!!");
                }
            }
            while(true) {
		try {
                    System.out.println("Nhap ngay chuyen: ");
                    df.setLenient(false);
                    ngaychuyen = df.parse(sc.nextLine());
                    if(ngaychuyen.after(nodeDoubleL.qlQuanlykhachhang.getNgayvao()))
			break;
                    else
			System.err.println("Ngay chuyen khong duoc nho hon ngay vao");
		} catch (ParseException e) {
			System.err.println("Nhap sai dinh dang (yyyy-MM-dd)");
		}
            }
            System.out.println("Nhap ly do chuyen:");
            lydo = sc.nextLine();
            if(treeNode1 !=null){
                ///Lay ma phong hien tai
                int id_pcu = nodeDoubleL.qlQuanlykhachhang.getMaphong();
                nodeDoubleL.qlQuanlykhachhang.setMaphong(treeNode1.qlp.getMaphong());
                dao.capnhatkhachhang(nodeDoubleL.qlQuanlykhachhang, makh, maphong);
                
                // tang so luong nguoi trong phong
                treeNode1.qlp.setSonguoi(treeNode1.qlp.getSonguoi()+1);
                dao.suaphong(treeNode1.qlp, maphong);
                
                /// resst lai so nguoi trong phong
                TreeNode idphongcuNode;
                idphongcuNode = quanlyphong.chekphong(treeNode, id_pcu);
                idphongcuNode.qlp.setSonguoi(idphongcuNode.qlp.getSonguoi()-1);
                dao.suaphong(idphongcuNode.qlp, id_pcu);
                
                /// luu danh sach chuyen phongq
                quanlychuyenphong.setMachuyenphong(machuyenphong);
                quanlychuyenphong.setMakh(makh);
                quanlychuyenphong.setId_pcu(id_pcu);
                quanlychuyenphong.setId_pmoi(treeNode1.qlp.getMaphong());
                quanlychuyenphong.setNgaychuyen(ngaychuyen);
                quanlychuyenphong.setLydo(lydo);
                listchArrayLists.add(quanlychuyenphong);
                dao.insertchuyenPhong(listchArrayLists.get(listchArrayLists.size()-1));
                QUANLYKHACHHANG.hienthitieude();
                nodeDoubleL.qlQuanlykhachhang.hienthithongtinkhachhang();
                System.out.println("Chuyen phong thanh cong !!!");
         }
    }
}
