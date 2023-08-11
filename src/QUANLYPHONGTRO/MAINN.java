/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QUANLYPHONGTRO;

import CAUTRUCLUUTRU.DoubleLinkedList;
import CAUTRUCLUUTRU.LinkedList;
import CAUTRUCLUUTRU.MyBST;
import CAUTRUCLUUTRU.NodeDoubleL;
import CAUTRUCLUUTRU.TreeNode;
import CONTENT.ConnectJDBCC;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author chinh
 */

public class MAINN {
static QUANLYPHONG sqlQuanlyphong = new QUANLYPHONG();
static QUANLYKHACHHANG quanlykhachhang = new QUANLYKHACHHANG();
static QUANLYTHUEPHONG quanlythuephong = new QUANLYTHUEPHONG();
static QUANLYCHUYENPHONG quanlychuyenphong = new QUANLYCHUYENPHONG();
static QUANLYTRAPHONG quanlytraphong = new QUANLYTRAPHONG();
static MyBST bST = new MyBST();
static DoubleLinkedList dLL = new DoubleLinkedList();
static LinkedList linkedList = new LinkedList();
static QUANLYNGUOIDUNG qlQuanlynguoidung = new QUANLYNGUOIDUNG();
static NodeDoubleL nodeDoubleL = new NodeDoubleL(quanlykhachhang);
static ArrayList<QUANLYCHUYENPHONG> listcQuanlychuyenphongs = new ArrayList<>();
static ArrayList<QUANLYTRAPHONG> listqQuanlytraphongs = new ArrayList<>();
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException{
        Scanner sc = new Scanner(System.in); 	
	ConnectJDBCC dao = new ConnectJDBCC();
        dao.gelistnguoidung(linkedList);
        dao.gelstphong(bST);
        dao.gelstkhahhang(dLL);
        dao.getListThuePhong(bST);
        dao.gelstchuyenphong(listcQuanlychuyenphongs);
        dao.gelsttraphong(listqQuanlytraphongs);
        String tendangnhap;
        do{
            tendangnhap = qlQuanlynguoidung.dangnhap(linkedList);
            if(tendangnhap !=null){
                int chon;
                do{
                    menutro();
                    while(true){
                        try {
                             chon = Integer.parseInt(sc.nextLine());
                        if(chon>=0 &&chon<=6)
                            break;
                        else
                            System.out.println("Bạn da chon sai vui long chon lai !!!");
                        } catch (Exception e) {
                            System.out.println("Bạn da chon sai vui long chon lai !!!");
                        }
                    }
                    switch(chon){
                        case 1:{
                            int luachon;
                            do{
                              menuPHONG();
                              while(true){
                                  try {
                                         chon = Integer.parseInt(sc.nextLine());
                                        if(chon>=0 &&chon<=5)
                                            break;
                                        else
                                            System.out.println("Bạn da chon sai vui long chon lai !!!");
                                        } catch (Exception e) {
                                            System.out.println("Bạn da chon sai vui long chon lai !!!");
                                   }
                              }
                              switch(chon){
                                  case 1:{
                                    sqlQuanlyphong.hamnhapthongtinphong(bST);
                                    sqlQuanlyphong.hienthitieudephong();
                                    sqlQuanlyphong.InOder(bST.mRoot);
                                    break;
                                  }
                                  case 2:{
                                      sqlQuanlyphong.hienthitieudephong();
                                      sqlQuanlyphong.InOder(bST.mRoot);
                                      break;
                                  }
                                  case 3:{
                                      sqlQuanlyphong.updateNode(bST.mRoot);
                                      break;
                                  }
                                  case 4:{
                                      sqlQuanlyphong.searchBST(bST.mRoot);
                                      break;
                                  }
                                  case 5:{
                                      int maphong;
                                      while(true){
                                          try {
                                              System.out.println("Nhap ma phong muon xoa: ");
                                              maphong = Integer.parseInt(sc.nextLine());
                                              if(sqlQuanlyphong.setMaphong(maphong)){
                                                  TreeNode cNode = sqlQuanlyphong.chekphong(bST.mRoot, maphong);
                                                  if(cNode == null)
                                                      System.out.println("Ma phong khong ton tai !!!");
                                                      break;
                                              }
                                          } catch (Exception e) {
                                              System.out.println("bAN NHAP SAI VUI LONG NHAP LAI!!!");
                                          }
                                      }
                                      bST.mRoot = sqlQuanlyphong.deleteNode(bST.mRoot, maphong);
                                      sqlQuanlyphong.hienthitieudephong();
                                      sqlQuanlyphong.InOder(bST.mRoot);
                                      break;
                                  }
                                      
                                  case 0:{
                                      System.out.println("off");
                                  }
                              }
                            }while (chon != 0);
                            break;
                        }
                        case 2:{
                            int luachon;
                            do{
                               menukhachhanf();
                               while(true){
                                   try {
                                         luachon = Integer.parseInt(sc.nextLine());
                                        if(chon>=0 &&chon<=4)
                                            break;
                                        else
                                            System.out.println("Bạn da chon sai vui long chon lai !!!");
                                        } catch (Exception e) {
                                            System.out.println("Bạn da chon sai vui long chon lai !!!");
                                   }
                              }
                               switch(luachon){
                                   case 1:{
                                       dLL.hamHienThiListkhachhang();
                                       break;
                                   }
                                   case 2:{
                                       dLL.updatekhachhang(dLL, nodeDoubleL);
                                       break;
                                   }
                                   case 3:{
                                       dLL.searchlh(nodeDoubleL);
                                       break;
                                   }
                                   case 4:{
                                       menusapxepkh();
                                       while(true){
                                           try {
                                               luachon = Integer.parseInt(sc.nextLine());
                                           if(luachon >= 0 && luachon <=3)
                                               break;
                                           else
                                               System.out.println("Ban da chon sai vui long chon lai !!!");
                                           } catch (Exception e) {
                                              System.out.println("Ban da chon sai vui long chon lai !!!");
                                           }
                                       }
                                       switch(luachon){
                                           case 1:{
                                               dLL.sapxeptheotenn();
                                               break;
                                           }
                                           case 2:{
                                               dLL.sapxeptangdantheosophong();
                                               break;
                                           }
                                       }break;
                                   }
                               }
                            }while (luachon != 0);
                            break;
                        }
                        case 3:{
                            int luachon;
                            do{
                                menuthuePHONG();
                                while(true){
                                    try {
                                         luachon = Integer.parseInt(sc.nextLine());
                                        if(chon>=0 &&chon<=3)
                                            break;
                                        else
                                            System.out.println("Bạn da chon sai vui long chon lai !!!");
                                        } catch (Exception e) {
                                            System.out.println("Bạn da chon sai vui long chon lai !!!");
                                   }
                                }
                                switch(luachon){
                                    case 1:{
                                        quanlythuephong.hamNhapThuePhong(bST, dLL, nodeDoubleL);
                                        quanlythuephong.hienthitieudethuephong();
                                        quanlythuephong.InOder(bST.mRootTP);
                                        break;
                                    }
                                    case 2:{
                                        quanlythuephong.hienthitieudethuephong();
                                        quanlythuephong.InOder(bST.mRootTP);
                                        break;
                                    }
                                    case 3:{
                                        quanlythuephong.taiThuePhong(bST, bST.mRoot, dLL, nodeDoubleL);
                                        break;
                                    }
                                }
                            }while (luachon != 0);
                            break;
                        }
                        case 4:{
                            linkedList.doimatkhau(tendangnhap);
                            break;
                        }
                        case 5:{
                            quanlychuyenphong.chuyenphong(bST.mRoot, dLL, nodeDoubleL, listcQuanlychuyenphongs);
                            quanlychuyenphong.hamhienthichuyenphong(listcQuanlychuyenphongs);
                            break;
                        }
                        case 6:{
                            quanlytraphong.trphong(bST.mRoot, dLL, nodeDoubleL, listqQuanlytraphongs);
                            quanlytraphong.hamhienthitraphong(listqQuanlytraphongs);
                            break;
                        }
                    }
                }while (chon !=0);
            }
        }while (tendangnhap == null);
        
    }
    public static void menuPHONG() {
		System.out.println("------------------QUAN LY PHONG------------------");
		System.out.println("|*1. Them phong                                 |");
		System.out.println("|*2. Hien thi phong                             |");
		System.out.println("|*3. Cap nhat phong                             |");
		System.out.println("|*4. Tim kiem phong                             |");
		System.out.println("|*5. Xoa phong                                  |");
		System.out.println("|*0. Ket thuc                                   |");
		System.out.println("-------------------------------------------------");
		System.out.println("***Moi ban chon***:");
    }
    public static void menukhachhanf() {
		System.out.println("----------------QUAN LY KHACH HÀNG---------------");
		System.out.println("|*1. Hien thi khach hang                        |");
		System.out.println("|*2. Cap nhat khach hang                        |");
		System.out.println("|*3. Tim kiem khach hang	                      |");
		System.out.println("|*4. Sap xep khach hang                         |");
		System.out.println("|*0. Quay lại                                   |");
		System.out.println("-------------------------------------------------");
		System.out.println("***Moi ban chon***:");
	}
    public static void menusapxepkh() {
		System.out.println("-----------------Sap xep khach hang--------------");
		System.out.println("|*1. Sap xep theo ten                           |");
		System.out.println("|*2. Sap xep theo ma phong                      |");
		System.out.println("|*3. Sap xep theo ngay vao                      |");
		System.out.println("|*0. Quay lại                                   |");
		System.out.println("-------------------------------------------------");
		System.out.println("***Moi ban chon***:");
	}
    public static void menuthuePHONG() {
		System.out.println("------------------QUAN LY THUE PHONG-------------");
		System.out.println("|*1. Thue phong                                 |");
		System.out.println("|*2. Hien thi ds thue phong                     |");
		System.out.println("|*3. Khach hang quay lai thue                   |");
		System.out.println("|*0. Ket thuc                                   |");
		System.out.println("-------------------------------------------------");
		System.out.println("***Moi ban chon***:");
    }
     public static void menutro() {
		System.out.println("------------------QUAN LY PHONG TRO--------------");
		System.out.println("|*1. Quan ly phong                              |");
		System.out.println("|*2. Quan ly khach hang                         |");
		System.out.println("|*3. Quan ly thue phong                         |");
		System.out.println("|*4. Doi mat khau                               |");
		System.out.println("|*5. Quan ly chuyen phong                       |");
                System.out.println("|*6. Quan ly tra phong                          |");
		System.out.println("|*0. Ket thuc                                   |");
		System.out.println("-------------------------------------------------");
		System.out.println("***Moi ban chon***:");
    }
}