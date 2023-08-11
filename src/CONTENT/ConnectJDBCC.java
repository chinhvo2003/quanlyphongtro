/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTENT;

import CAUTRUCLUUTRU.DoubleLinkedList;
import CAUTRUCLUUTRU.LinkedList;
import CAUTRUCLUUTRU.MyBST;
import CAUTRUCLUUTRU.NodeDoubleL;
import CAUTRUCLUUTRU.NodeLinkedList;
import CAUTRUCLUUTRU.TreeNode;
import QUANLYPHONGTRO.QUANLYCHUYENPHONG;
import QUANLYPHONGTRO.QUANLYKHACHHANG;
import QUANLYPHONGTRO.QUANLYNGUOIDUNG;
import QUANLYPHONGTRO.QUANLYPHONG;
import QUANLYPHONGTRO.QUANLYTHUEPHONG;
import QUANLYPHONGTRO.QUANLYTRAPHONG;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author chinh
 */
public class ConnectJDBCC {
    public Connection conn ;
public SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public ConnectJDBCC() throws ClassNotFoundException, SQLException{
       try {
			String userName = "sa";
			 String password = "chinh2003";
			 String connectionURL = "jdbc:sqlserver://CHINH\\SQLEXPRESS:1433;databaseName=QL_PHONGTRO;"
						+ "encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(connectionURL,userName,password );
			System.out.println("Ket noi thanh cong");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.err.println("Ket noi that bai");
			e.printStackTrace();
		}
    }
 
        public void gelistnguoidung(LinkedList linkedList){
            String sql ="select * from NGUOI_DUNG";
		try {
                 PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QUANLYNGUOIDUNG QLQuanlynguoidung = new QUANLYNGUOIDUNG();
                                QLQuanlynguoidung.setTendangnhap(rs.getString(1));
				QLQuanlynguoidung.setMatkhau(rs.getString(2));
                                NodeLinkedList nodeLinkedList = new NodeLinkedList(QLQuanlynguoidung);
				linkedList.insertTail(nodeLinkedList);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        }
        public void updatenguoidung(QUANLYNGUOIDUNG quanlynguoidung, String tendangnhap){
            String sql = "update NGUOI_DUNG set matkhau=? where taikhoan=?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, quanlynguoidung.matkhau);
                ps.setString(2, tendangnhap);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        public void gelstphong(MyBST bST){
            String sql="select * from PHONG ";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    QUANLYPHONG quanlyphong = new QUANLYPHONG();
                    quanlyphong.setMaphong(rs.getInt(1));
                    quanlyphong.setLoaiphong(rs.getString(2));
                    quanlyphong.setSogiuong(rs.getInt(3));
                    quanlyphong.setSonguoi(rs.getInt(4));
                    quanlyphong.setGiatien(rs.getFloat(5));
                    TreeNode treeNode = new TreeNode(quanlyphong);
                    bST.mRoot = quanlyphong.insertBST(bST.mRoot, treeNode);
                }
                ps.close();
                rs.close();
            } catch (Exception e) {
            }
        }
        public void themphong(QUANLYPHONG quanlyphong){
            String sql="insert into PHONG values (?,?,?,?,?)";
            try {
                  PreparedStatement ps = conn.prepareStatement(sql);
                  ps.setInt(1, quanlyphong.getMaphong());
                  ps.setString(2, quanlyphong.getLoaiphong());
                  ps.setInt(3, quanlyphong.getSogiuong());
                  ps.setInt(4, quanlyphong.getSonguoi());
                  ps.setFloat(5, quanlyphong.getGiatien());
                  ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void suaphong(QUANLYPHONG quanlyphong, int maphong){
            String sql="update PHONG set loaiphong=?, sogiuong=?, songuoi=?, giatien=? where maphong=?";
            try {
                  PreparedStatement ps = conn.prepareStatement(sql);
                  ps.setString(1, quanlyphong.getLoaiphong());
                  ps.setInt(2, quanlyphong.getSogiuong());
                  ps.setInt(3, quanlyphong.getSonguoi());
                  ps.setFloat(4, quanlyphong.getGiatien());
                  ps.setInt(5, quanlyphong.getMaphong());
                  ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void xoaphong(int maphong){
            String sql="delete from PHONG where maphong=?";
            try {
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, maphong);
             if(ps.executeUpdate() > 0 ){
                 System.out.println("Xoa thah cong!!! ");
             }
             else 
                 System.out.println("Xoa that bai !!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        public void getListThuePhong(MyBST bST) {
		String sql = "select * from THUE_PHONG";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				QUANLYTHUEPHONG qLTP = new QUANLYTHUEPHONG();
				qLTP.setMathuephong(rs.getInt(1));
				qLTP.setMakh(rs.getString(2));
				qLTP.setId_p(rs.getInt(3));
				qLTP.setNgaythue(rs.getDate(4));
				qLTP.setTrangthai(rs.getString(5));
				TreeNode treeNode = new TreeNode(qLTP);
				bST.mRootTP = qLTP.insertIntoBST(bST.mRootTP, treeNode);
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void insertThuePhong(QUANLYTHUEPHONG quanlythuephong) {
		String sql = "INSERT INTO THUE_PHONG  VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quanlythuephong.getMathuephong());
			ps.setString(2, quanlythuephong.getMakh());
			ps.setInt(3, quanlythuephong.getId_p());
			ps.setString(4,  df.format(quanlythuephong.getNgaythue()));
			ps.setString(5, quanlythuephong.getTrangthai());
//			ps.execute();
			
			ps.executeUpdate();
//				System.out.println("Thêm dữ liệu thành công!");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        public void gelstchuyenphong(ArrayList<QUANLYCHUYENPHONG> listcQuanlychuyenphongs){
            String sql="select * from CHUYEN_PHONG";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    QUANLYCHUYENPHONG quanlychuyenphong = new QUANLYCHUYENPHONG();
                    quanlychuyenphong.setMachuyenphong(rs.getInt(1));
                    quanlychuyenphong.setMakh(rs.getString(2));
                    quanlychuyenphong.setId_pcu(rs.getInt(3));
                    quanlychuyenphong.setId_pmoi(rs.getInt(4));
                    quanlychuyenphong.setNgaychuyen(rs.getDate(5));
                    quanlychuyenphong.setLydo(rs.getString(6));
                    listcQuanlychuyenphongs.add(quanlychuyenphong);
                }
                ps.close();rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        public void insertchuyenPhong(QUANLYCHUYENPHONG quanlychuyenphong) {
		String sql = "INSERT INTO CHUYEN_PHONG  VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quanlychuyenphong.getMachuyenphong());
			ps.setString(2, quanlychuyenphong.getMakh());
			ps.setInt(3, quanlychuyenphong.getId_pcu());
                        ps.setInt(4, quanlychuyenphong.getId_pmoi());
			ps.setString(5,  df.format(quanlychuyenphong.getNgaychuyen()));
			ps.setString(6, quanlychuyenphong.getLydo());
			
			ps.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        public void gelsttraphong(ArrayList<QUANLYTRAPHONG> listcQuanlychuyenphongs){
            String sql="select * from TRA_PHONG";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    QUANLYTRAPHONG quanlychuyenphong = new QUANLYTRAPHONG();
                    quanlychuyenphong.setMatraphong(rs.getInt(1));
                    quanlychuyenphong.setMakh(rs.getString(2));
                    quanlychuyenphong.setId_p(rs.getInt(3));
                    quanlychuyenphong.setNgaytra(rs.getDate(4));
                    quanlychuyenphong.setLydo(rs.getString(5));
                    listcQuanlychuyenphongs.add(quanlychuyenphong);
                }
                ps.close();rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
         public void inserttraphong(QUANLYTRAPHONG quanlychuyenphong) {
		String sql = "INSERT INTO TRA_PHONG  VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quanlychuyenphong.getMatraphong());
			ps.setString(2, quanlychuyenphong.getMakh());
			ps.setInt(3, quanlychuyenphong.getId_p());
			ps.setString(4,  df.format(quanlychuyenphong.getNgaytra()));
			ps.setString(5, quanlychuyenphong.getLydo());
			
			ps.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        public void gelstkhahhang(DoubleLinkedList doubleLinkedList){
            String sql="select * from KHACH_HANG";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                QUANLYKHACHHANG qlQuanlykhachhang = new QUANLYKHACHHANG();
                qlQuanlykhachhang.setMakh(rs.getString(1));
                qlQuanlykhachhang.setHotenkh(rs.getString(2));
                qlQuanlykhachhang.setNgaysinh(rs.getDate(3));
                qlQuanlykhachhang.setCmnd(rs.getString(4));
                qlQuanlykhachhang.setSdt(rs.getString(5));
                qlQuanlykhachhang.setGioitinh(rs.getString(6));
                qlQuanlykhachhang.setQuequan(rs.getString(7));
                qlQuanlykhachhang.setMaphong(rs.getInt(8));
                qlQuanlykhachhang.setNgayvao(rs.getDate(9));
                qlQuanlykhachhang.setTrangthai(rs.getString(10));
                NodeDoubleL nodeDoubleL = new NodeDoubleL(qlQuanlykhachhang);
                doubleLinkedList.insertTail(nodeDoubleL);
                }
                ps.close();
		rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void themkhachhang(NodeDoubleL nodeDoubleL){
            String sql="insert into KHACH_HANG values(?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nodeDoubleL.qlQuanlykhachhang.getMakh());
                ps.setString(2, nodeDoubleL.qlQuanlykhachhang.getHotenkh());
		ps.setString(3,  df.format(nodeDoubleL.qlQuanlykhachhang.getNgaysinh()));
                ps.setString(4, nodeDoubleL.qlQuanlykhachhang.getCmnd());
                ps.setString(5, nodeDoubleL.qlQuanlykhachhang.getSdt());
                ps.setString(6, nodeDoubleL.qlQuanlykhachhang.getGioitinh());
                ps.setString(7, nodeDoubleL.qlQuanlykhachhang.getQuequan());
                ps.setInt(8, nodeDoubleL.qlQuanlykhachhang.getMaphong());
		ps.setString(9, df.format(nodeDoubleL.qlQuanlykhachhang.getNgayvao()));
                ps.setString(10, nodeDoubleL.qlQuanlykhachhang.getTrangthai());
                ps.executeUpdate();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void capnhatkhachhang(QUANLYKHACHHANG qlQuanlykhachhang,String makh,int maphong){
            String sql="update KHACH_HANG set hotenkh=?,ngaysinh=?,cmnd=?,dienthoai=?,gioitinh=?,quequan=?,maphong=?,ngayvao=?,trangthai=?"
                    + " where makh=?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, qlQuanlykhachhang.getHotenkh());
		ps.setString(2, df.format(qlQuanlykhachhang.getNgaysinh()));
                ps.setString(3, qlQuanlykhachhang.getCmnd());
                ps.setString(4, qlQuanlykhachhang.getSdt());
                ps.setString(5, qlQuanlykhachhang.getGioitinh());
                ps.setString(6, qlQuanlykhachhang.getQuequan());
                ps.setInt(7, maphong);
		ps.setString(8, df.format(qlQuanlykhachhang.getNgayvao()));
                ps.setString(9, qlQuanlykhachhang.getTrangthai());
                ps.setString(10, qlQuanlykhachhang.getMakh());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void xoakhachhang(String makh){
            String sql="delete from KHACH_HANG where makh=?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, makh);
			
			if(ps.executeUpdate() > 0) 
				System.out.println("Xoa thanh cong!");
			else 
				System.out.println("Xoa that bai");
		
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new ConnectJDBCC();
	}
}
