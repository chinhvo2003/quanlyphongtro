/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QUANLYPHONGTRO;

import BANGMDEOL.PHONG;
import CAUTRUCLUUTRU.MyBST;
import CAUTRUCLUUTRU.TreeNode;
import CONTENT.ConnectJDBCC;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author chinh
 */
public class QUANLYPHONG extends PHONG{

    public QUANLYPHONG() {
    }

    public QUANLYPHONG(int maphong, int sogiuong, int songuoi, String loaiphong, float giatien) {
        super(maphong, sogiuong, songuoi, loaiphong, giatien);
    }
    
    
    // kiểm tra phòng đã tồn tại hay chưa
    
    public TreeNode chekphong(TreeNode root, int maphong){
        if(root==null){
            return null;
        }else{
            if(maphong < root.qlp.getMaphong())
                return chekphong(root.left, maphong);
            else if(maphong > root.qlp.getMaphong())
                return chekphong(root.right, maphong);
            else 
                return root;
        }
    }//Kiểm tra xem phòng có tồn tại không và đã đủ người chưa, trả về phòng đó
	public TreeNode checkFullPhong(TreeNode treeNode, int maPhong) {
		TreeNode currNode = chekphong(treeNode, maPhong);
		if(currNode != null) {
			if(currNode.qlp.getSonguoi() < currNode.qlp.getSogiuong() * 2)
				return currNode;
			else 
				System.err.println("PPhong da du nguoi !!!");
		} else 
			System.err.println("Phong da ton tai !!!");
		
		return null;
	}
         //Tìm kiếm
	public void searchBST(TreeNode root) {
		Scanner sc = new Scanner(System.in);
		int maPhong;
		while(true) {
			try {
				System.out.println("Nhap ma phong muon tim: ");
				maPhong = Integer.parseInt(sc.next());
				if(setMaphong(maPhong)) 
					break;
			} catch (Exception e) {
				System.err.println("Nhap sai vui long nhap lai !!!");
			}
		}
		TreeNode currNode = chekphong(root, maPhong);
		if(currNode == null)
			System.err.println("Khong tim thay phong !!!");
		else {
			System.out.println("Phong muon tim là: ");
			currNode.qlp.hienthitieudephong();
			currNode.qlp.hienthithongtinphong();
		}
		
	}
	
    public TreeNode insertBST(TreeNode root, TreeNode treeNode) {
		if(root == null) {
			root = treeNode;
			return root;
		}
		if(treeNode.qlp.getMaphong() > root.qlp.getMaphong()) {
			if(root.right == null) 
				root.right = treeNode;
			else
				insertBST(root.right, treeNode);
		}
		else 
			if(root.left == null) 
				root.left = treeNode;
			else
				insertBST(root.left, treeNode);
		return root;
	}
        public static void InOder(TreeNode cTreeNode) {
		if(cTreeNode == null) {
			return;
		}
		//Duyệt bên trái
		InOder(cTreeNode.left);
		
		cTreeNode.hienthiphong();
		
		InOder(cTreeNode.right);
	}
    public void nhapthongtinphong(TreeNode treeNode){
        Scanner sc = new Scanner(System.in);
        while(true){
            try {
                System.out.println("Nhap ma phong: ");
                maphong=Integer.parseInt(sc.nextLine());
                boolean chek = setMaphong(maphong);
                if(chek){
                    TreeNode cuNode = chekphong(treeNode, maphong);
                    if(cuNode !=null){
                        System.out.println("Ma phong da ton tai !!");
                    }else
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhap sai vui long nhap lai !!!");
            }
        }
        System.out.println("Nhap loai phong (Phòng trọ nhỏ || Phòng trọ thông || Phòng trọ rộng)");
        while(!setLoaiphong(sc.nextLine()));
        System.out.println("Nhap so giuong: ");
        while(true){
            try {
                sogiuong = Integer.parseInt(sc.nextLine());
                if(sogiuong >=1 && sogiuong<=3)
                    break;
                else
                    System.out.println("So giuong tu 1 den 3 !!!"); 
            } catch (Exception e) {
                   System.out.println("So giuong tu 1 den 3 !!!"); 
            }
        }
        songuoi=0;
        
        if(loaiphong.equals("Phòng trọ nhỏ"))
            giatien = sogiuong * 5000;
        else if(loaiphong.equals("Phòng trọ thông"))
            giatien = sogiuong * 7000;
        else 
            giatien = sogiuong * 10000;
        
    }
    public TreeNode updateNode(TreeNode root) throws ClassNotFoundException, SQLException {
                ConnectJDBCC dao = new ConnectJDBCC();
		Scanner sc = new Scanner(System.in);
		int maPhong;
		while(true) {
			try {
				System.out.println("Nhap ma phong muon chinh sua: ");
				maPhong = Integer.parseInt(sc.next());
				if(setMaphong(maPhong)) 
					break;
			} catch (Exception e) {
				System.err.println("Nhap sai, vui long nhap lai !!!");
			}
		}
		if(root == null)
			return null;
		else {
			QUANLYPHONG quanlyphong = new QUANLYPHONG();
			TreeNode cNode = chekphong(root, maPhong);
			if(cNode!= null) {
				MyBST bST = new MyBST();
				quanlyphong.nhapthongtinphong(bST.mRoot);
				//Kiểm tra xem số người tối đa của phòng sau khi chỉnh sửa có lớn hơn số người đang ở không
				if(quanlyphong.getSogiuong()*2 > cNode.qlp.getSonguoi()) {
					quanlyphong.setSonguoi(cNode.qlp.getSonguoi());
					cNode.qlp.setLoaiphong(quanlyphong.getLoaiphong());
					cNode.qlp.setSogiuong(quanlyphong.getSogiuong());
					cNode.qlp.setGiatien(quanlyphong.getGiatien());
					dao.suaphong(quanlyphong, maPhong);
				}
				else 
					System.err.println("So giuong nho hon so phong !!!");
			} else 
				System.out.println("khong tim thay phong de chinh sua");
			quanlyphong.hienthitieudephong();
                        quanlyphong.InOder(root);
			
			return root;
		}
			
			
			
		}
    //Tìm node con trái cùng của root
	public TreeNode findLeftModeNode(TreeNode root) {
		if(root == null)
			return null;
		TreeNode leftModeNode = root;
		while(leftModeNode.left != null) 
			leftModeNode = leftModeNode.left;
		return leftModeNode;
	}
	
	//Tìm node con phải cùng của root
	public TreeNode findRightModeNode(TreeNode root) {
		if(root == null)
			return null;
		TreeNode leftModeNode = root;
		while(leftModeNode.left != null) 
			leftModeNode = leftModeNode.right;
		return leftModeNode;
	}
	
	public void ganPhong(TreeNode root1, TreeNode root2) {
		int maPhong = root1.qlp.getMaphong();
		String loaiPhong = root1.qlp.getLoaiphong();
		int soGiuong = root1.qlp.getSogiuong();
		int soNguoi = root1.qlp.getSonguoi();
		float giaTien = root1.qlp.giatien;
		
		root1.qlp.setMaphong(root2.qlp.getMaphong());
		root2.qlp.setLoaiphong(root2.qlp.getLoaiphong());
		root1.qlp.setSogiuong(root2.qlp.getSogiuong());
		root1.qlp.setSonguoi(root2.qlp.getSonguoi());
		root1.qlp.setGiatien(root2.qlp.getGiatien());
		
		root2.qlp.setMaphong(maPhong);
		root2.qlp.setLoaiphong(loaiPhong);
		root2.qlp.setSogiuong(soGiuong);
		root2.qlp.setSonguoi(soNguoi);
		root2.qlp.setGiatien(giaTien);
	}
    public TreeNode deleteNode(TreeNode root, int maPhong) throws ClassNotFoundException, SQLException  {
                ConnectJDBCC dao = new ConnectJDBCC();
		if(root == null)
			return null;
		else {
			//B1: đi tìm node xoá
			if(maPhong < root.qlp.getMaphong()) 
				root.left = deleteNode(root.left, maPhong);
			else if(maPhong > root.qlp.getMaphong()) 
				root.right = deleteNode(root.right, maPhong);
			else { //root.value == key ==> xoá root
				//B2: xoá node
//					Kiểm tra xem phòng còn người ở hay không
				if(root.qlp.getSonguoi() != 0) {
					System.err.println("Phong dang co nguoi ko the xoa !!!");
				}
				else {
					dao.xoaphong(maPhong);
					//Th1: Node xoá là node lá: 
					if(root.left == null && root.right == null) {
						System.out.println("xOA THANH CONG");
						return null;
					}
					//TH2.1: Chỉ có 1 con bên trái
					if(root.left != null && root.right == null) {
						System.out.println("XOA THANH CONG");
						return root.left;
					}
					//TH2.2: Chỉ có 1 con bên phải
					if(root.left == null && root.right != null) {
						System.out.println("XOA THANH CONG!");
						return root.right;
					}
					//TH3: Tồn tại 2 con
					//Tìm node trái cùng của con bên phải
					TreeNode leftModeNode = findLeftModeNode(root.right);
					ganPhong(root, leftModeNode);
					root.right = deleteNode(root.right, leftModeNode.qlp.getMaphong());
				}
			}
			return root;
		}
	}
	
	
    public static void hamnhapthongtinphong(MyBST bST) throws ClassNotFoundException, SQLException{
        ConnectJDBCC dao = new ConnectJDBCC();
        Scanner sc = new Scanner(System.in);
        int h;
        while(true){
            try {
                System.out.println("Nhap so phong can nhap: ");
                h = Integer.parseInt(sc.nextLine());
                if(h >= 0 && h <=10)
                    break;
                else
                    System.out.println("So phong nhap lon hon 0");
            } catch (Exception e) {
                    System.out.println("So phong nhap lon hon 0");
            }
        }
        for(int i = 0; i < h; i++){
            QUANLYPHONG quanlyphong = new QUANLYPHONG();
            quanlyphong.nhapthongtinphong(bST.mRoot);
            TreeNode treeNode = new TreeNode(quanlyphong);
            dao.themphong(quanlyphong);
            bST.mRoot = quanlyphong.insertBST(bST.mRoot, treeNode);
        }
        System.out.println("Them thanh cong !!!");
    }
    public void hienthithongtinphong(){
        System.out.printf("|%8s|%10s|%9s|%8s|%11s|\n", maphong, loaiphong, sogiuong, songuoi, giatien);
    }
    public static void hienthitieudephong(){
        System.out.println("----------------------------------------------------");
	System.out.println("|Ma phong|Loai phong|So giuong|So nguoi| Gia tien  |");
	System.out.println("----------------------------------------------------");    
    }
}   
