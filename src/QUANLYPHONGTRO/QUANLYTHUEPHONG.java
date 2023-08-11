/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QUANLYPHONGTRO;

import BANGMDEOL.THUEPHONG;
import CAUTRUCLUUTRU.DoubleLinkedList;
import CAUTRUCLUUTRU.MyBST;
import CAUTRUCLUUTRU.NodeDoubleL;
import CAUTRUCLUUTRU.TreeNode;
import CONTENT.ConnectJDBCC;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author chinh
 */
public class QUANLYTHUEPHONG extends THUEPHONG{
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public QUANLYTHUEPHONG(int mathuephong, String makh, int id_p, Date ngaythue, String trangthai) {
        super(mathuephong, makh, id_p, ngaythue, trangthai);
    }

    public QUANLYTHUEPHONG() {
    }

   
    
    public void hienthithongtinthuephong(){
        System.out.printf("|%8s|%8s|%5s|%15s|%15s\n", mathuephong,makh,id_p,ngaythue,trangthai);
    }
    
    public void hienthitieudethuephong(){
        System.out.println("--------------------------------------------------------");
	System.out.println("|Ma Thue Phong| Ma KH  |ID_P|  Ngay Thue  | TRANG THAI |");
	System.out.println("--------------------------------------------------------");
    }
    public void nhapthongtinthuephong(TreeNode treeNode, DoubleLinkedList dLL, NodeDoubleL node) throws ClassNotFoundException, SQLException{
             Scanner sc = new Scanner(System.in);
                ConnectJDBCC dao = new ConnectJDBCC();
		while(true) {
			try {
                                System.out.println("Nhap ma thue phong:");
                                mathuephong = Integer.parseInt(sc.next());
				System.out.println("Nhap ma phong: ");
				id_p = Integer.parseInt(sc.next());
				int index = -1;
				int index1 = -1;
//				
				//Check xem phòng tồn tại không và còn chổ không
				QUANLYPHONG qLP = new QUANLYPHONG();
				QUANLYKHACHHANG qLKH = new QUANLYKHACHHANG();
				TreeNode currNode = treeNode;
				currNode = qLP.checkFullPhong(treeNode, id_p);
				
				if(currNode != null) {
					qLKH.hamnhapthongtinkhachhang(dLL, node);
					dLL.NodeEnd(node).qlQuanlykhachhang.setMaphong(id_p);
					dao.themkhachhang(dLL.NodeEnd(node));
					makh = dLL.NodeEnd(node).qlQuanlykhachhang.getMakh();
					ngaythue = dLL.NodeEnd(node).qlQuanlykhachhang.getNgayvao();
					currNode.qlp.setSonguoi(currNode.qlp.getSonguoi() + 1);
					dao.suaphong(currNode.qlp, id_p);
					index = 1;
					break;
				}
				if(index == 1) {
					break;
				}
			} catch (Exception e) {
				System.err.println("Ban da nhap sai vui long nhap lai !!!");
			}
		}
		trangthai ="DANG THUE";
    }
    public static void hamNhapThuePhong(MyBST bST, DoubleLinkedList dLL, NodeDoubleL node ) throws SQLException, ClassNotFoundException {
                ConnectJDBCC dao = new ConnectJDBCC();
		QUANLYTHUEPHONG quanlythuephong = new QUANLYTHUEPHONG();
		quanlythuephong.nhapthongtinthuephong(bST.mRoot, dLL, node);
		TreeNode treeNode = new TreeNode(quanlythuephong);
		dao.insertThuePhong(quanlythuephong);
		bST.mRootTP = quanlythuephong.insertIntoBST(bST.mRootTP, treeNode);
		System.out.println("Them thanh cong !");
	}
    public TreeNode insertIntoBST(TreeNode root, TreeNode treeNode) {
		if(root == null) {
			root = treeNode;
			return root;
		}
		if(treeNode.qltp.mathuephong > root.qltp.getMathuephong()) {
			if(root.right == null) 
				root.right = treeNode;
			else
				insertIntoBST(root.right, treeNode);
		}
		else 
			if(root.left == null) 
				root.left = treeNode;
			else
				insertIntoBST(root.left, treeNode);
		return root;
	}
    public static void InOder(TreeNode currNode) {
			if(currNode == null) {
				return;
			}
			//Duyệt bên trái
			InOder(currNode.left);
			
			currNode.hienthithuephong();
			
			InOder(currNode.right);
	}
    public void taiThuePhong(MyBST bST, TreeNode treeNode, DoubleLinkedList dLL, NodeDoubleL node) throws SQLException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
                ConnectJDBCC dao = new ConnectJDBCC();
		QUANLYPHONG qLP = new QUANLYPHONG();
		NodeDoubleL currNode;
		TreeNode treeCurrNode;
		while(true) {
			try {
				System.out.println("Nhap ma khach hang muon thue lai: ");
				makh = sc.nextLine();
				currNode = dLL.search(node, makh);
				if(currNode != null) {
					if(currNode.qlQuanlykhachhang.getTrangthai().equalsIgnoreCase("DA TRA")) {
						currNode.qlQuanlykhachhang.hienthitieude();
						currNode.qlQuanlykhachhang.hienthithongtinkhachhang();
						break;
					}
					else
						System.err.println("Khach hang nay da thue !!!");
				}
				else
					System.err.println("Khach hang chua thue !!!");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		while(true) {
			try {
				System.out.println("Nhap ma phong muon thue: ");
				id_p = Integer.parseInt(sc.nextLine());
				treeCurrNode = qLP.checkFullPhong(treeNode, id_p);
				if(treeCurrNode != null) {
					break;
				}
			} catch (Exception e) {
				System.err.println("Vui long nhap dung dinh dang !!!");
			}
		}
		while(true) {
			try {
				df.setLenient(false);
				System.out.println("Nhap ngay thue: ");
				ngaythue = df.parse(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("vui long nhap dung dinh dang(yyyy-MM-dd): ");
			}
		}
		//Lưu thông tin thuê phòng, và thêm vào SQL
		QUANLYTHUEPHONG quanlythuephong = new QUANLYTHUEPHONG();
		quanlythuephong.setMakh(makh);
		quanlythuephong.setId_p(id_p);
		quanlythuephong.setTrangthai("DANG THUE");
		dao.insertThuePhong(quanlythuephong);
		TreeNode treeNode1 = new TreeNode(quanlythuephong);
		bST.mRootTP =quanlythuephong.insertIntoBST(bST.mRootTP, treeNode1);
		
//		Xét lại trạng thái và các thông tin cho sinh viên
		currNode.qlQuanlykhachhang.setNgayvao(ngaythue);
		currNode.qlQuanlykhachhang.setMaphong(id_p);
                    currNode.qlQuanlykhachhang.setTrangthai("DANG THUE");
		dao.capnhatkhachhang(currNode.qlQuanlykhachhang, makh, id_p);
		
		//Tăng số người trong phòng thêm vào
		treeCurrNode.qlp.setSonguoi(treeCurrNode.qlp.getSonguoi() + 1);
		dao.suaphong(treeCurrNode.qlp, id_p);
		System.out.println("Cho thue thanh cong !!!!");
	}
}
