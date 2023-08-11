package CAUTRUCLUUTRU;

import CONTENT.ConnectJDBCC;
import QUANLYPHONGTRO.QUANLYKHACHHANG;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class DoubleLinkedList {
	public int size;
	public NodeDoubleL head;
	public NodeDoubleL tail;

	public DoubleLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	public int size() {
		return size;
	}

	// Tìm sinh viên cuối cùng.
	public NodeDoubleL NodeEnd(NodeDoubleL node) {
		NodeDoubleL currNode = head;
		while (currNode != null) {
			if (currNode.next == null) {
				return currNode;
			}
			currNode = currNode.next;
		}
		return node;
	}

	
        // Check xem mã khách hàng đã tồn tại trong danh sách hay chưa
	public NodeDoubleL checkkh(NodeDoubleL node, String makh) {
		NodeDoubleL currNode = head;
		while (currNode != null) {
			if (currNode.qlQuanlykhachhang.getMakh().equalsIgnoreCase(makh))
				return currNode;			
			currNode = currNode.next;
		}
		return null;
	}
	



        public NodeDoubleL search(NodeDoubleL node, String makh) {
		if (head == null)
			return null;
		else {
			NodeDoubleL currNode = head;
			int index = -1;
			while (currNode != null) {
				if (currNode.qlQuanlykhachhang.getMakh().equalsIgnoreCase(makh)) {
					return currNode;
				}
				currNode = currNode.next;
			}
			return null;
		}
	}

	//	Tìm khach hâng theo ma kh, nếu tìm thấy thì return ra sinh viên đó, còn không thì return null;
	public NodeDoubleL searchlh(NodeDoubleL node) {
		if (head == null)
			return null;
		else {
			Scanner sc = new Scanner(System.in);
			String makh;
			while (true) {
				try {
					System.out.println("Nhap ma khach hang :");
					makh = sc.nextLine();
					if (node.qlQuanlykhachhang.setMakh(makh))
						break;
				} catch (Exception e) {
				}
			}

			NodeDoubleL currNode = checkkh(node, makh);
			if (currNode != null) {
				System.out.println("Khach hang can tim: ");
				currNode.qlQuanlykhachhang.hienthitieude();
				currNode.qlQuanlykhachhang.hienthithongtinkhachhang();
			} else
				System.err.println("Khong tim thay khach hang can tim: ");

		}
		return null;

	}


	public void insertHead(NodeDoubleL node) {
		if (head == null)
			head = node;
		else {
			NodeDoubleL temp = head;
			head = node;
			temp.prev = node;
			node.next = temp;
		}
	}

	public void insertTail(NodeDoubleL node) {
		tail = head;
		if (head == null)
			head = node;
		else {
			while (tail.next != null) {
				tail = tail.next;
			}
			NodeDoubleL temp = tail;
			tail = node;
			node.prev = temp;
			temp.next = node;
		}
	}

	public void insertAt(NodeDoubleL node, int index) {
		if (index == 0)
			insertHead(node);
		else {
			NodeDoubleL currNode = head;
			int count = 0;
			while (currNode != null) {
				count++;
				if (count == index) {
					currNode.next.prev = node;
					node.next = currNode.next;
					currNode.next = node;
					node.prev = currNode;
					break;
				}
				currNode = currNode.next;
			}

		}
	}
	// xoá đuôi
	public void removeTail(NodeDoubleL node) {
		tail = head;
		if (head == null) {
			System.out.println("Danh sach rong khong the xoa !!!");
		} else {
			NodeDoubleL currNode = null;
			while (tail.next != null) {
				currNode = tail;
				tail = tail.next;
			}
			if (currNode == null) {
				head = head.next;
			} else {
				currNode.next = tail.next;
			}
		}
	}
        public void updatekhachhang(DoubleLinkedList dLL, NodeDoubleL node) throws ClassNotFoundException, SQLException {

		if (head == null)
			System.out.println("Danh sách sinh viên rỗng không thể chỉnh sửa");
		else {
			Scanner sc = new Scanner(System.in);
			QUANLYKHACHHANG quanlykhachhang = new QUANLYKHACHHANG();
			String makh;
			while (true) {
				System.out.println("Nhập mã sinh viên muốn chỉnh sửa: ");
				makh = sc.nextLine();
				boolean check = quanlykhachhang.setMakh(makh);
				if (check)
					break;
			}
                ConnectJDBCC dao = new ConnectJDBCC();
			NodeDoubleL currNode = checkkh(node, makh);
			if (currNode != null) {
				quanlykhachhang.nhapthongtinkh(dLL, node);

				currNode.qlQuanlykhachhang.setHotenkh(quanlykhachhang.getHotenkh());
				currNode.qlQuanlykhachhang.setNgaysinh(quanlykhachhang.getNgaysinh());
                                currNode.qlQuanlykhachhang.setCmnd(quanlykhachhang.getCmnd());
                                currNode.qlQuanlykhachhang.setSdt(quanlykhachhang.getSdt());
                                currNode.qlQuanlykhachhang.setGioitinh(quanlykhachhang.getGioitinh());
                                currNode.qlQuanlykhachhang.setQuequan(quanlykhachhang.getQuequan());
                                currNode.qlQuanlykhachhang.setMaphong(quanlykhachhang.getMaphong());
                                currNode.qlQuanlykhachhang.setTrangthai(quanlykhachhang.getTrangthai());
                                dao.capnhatkhachhang(quanlykhachhang, makh, currNode.qlQuanlykhachhang.getMaphong());
				System.out.println("Cập nhập thành công");
				hamHienThiListkhachhang();
			} else
				System.out.println("Không tìm thấy sinh viên theo mã sinh viên");
		}
	}
	public void hamHienThiListkhachhang() {
		if(head == null) 
			System.out.println("Danh sach rong !!!"); 
		else {
			NodeDoubleL currNode = head;
			currNode.qlQuanlykhachhang.hienthitieude();
			while (currNode != null) {
				if (currNode.qlQuanlykhachhang.getTrangthai().equalsIgnoreCase("DANG THUE"))
					currNode.hienthikhachhang();
				currNode = currNode.next;
			}
		}
	}
        public void traodoikh(NodeDoubleL node1, NodeDoubleL node2) {
		
		String makh = node1.qlQuanlykhachhang.getMakh();
                String hotenkh = node1.qlQuanlykhachhang.getHotenkh();
                Date ngaysinh = node1.qlQuanlykhachhang.getNgaysinh();
                String cmnd = node1.qlQuanlykhachhang.getCmnd();
                String sdt = node1.qlQuanlykhachhang.getSdt();
                String gioitinh = node1.qlQuanlykhachhang.getGioitinh();
                String quequan = node1.qlQuanlykhachhang.getQuequan();
                int maphong = node1.qlQuanlykhachhang.getMaphong();
                Date ngayvao = node1.qlQuanlykhachhang.getNgayvao();
                String trangthai = node1.qlQuanlykhachhang.getTrangthai();

		node1.qlQuanlykhachhang.setMakh(node2.qlQuanlykhachhang.getMakh());
                node1.qlQuanlykhachhang.setHotenkh(node2.qlQuanlykhachhang.getHotenkh());
                node1.qlQuanlykhachhang.setNgaysinh(node2.qlQuanlykhachhang.getNgaysinh());
                node1.qlQuanlykhachhang.setCmnd(node2.qlQuanlykhachhang.getCmnd());
                node1.qlQuanlykhachhang.setSdt(node2.qlQuanlykhachhang.getSdt());
                node1.qlQuanlykhachhang.setGioitinh(node2.qlQuanlykhachhang.getGioitinh());
                node1.qlQuanlykhachhang.setQuequan(node2.qlQuanlykhachhang.getQuequan());
                node1.qlQuanlykhachhang.setMaphong(node2.qlQuanlykhachhang.getMaphong());
                node1.qlQuanlykhachhang.setNgayvao(node2.qlQuanlykhachhang.getNgayvao());
                node1.qlQuanlykhachhang.setTrangthai(node2.qlQuanlykhachhang.getTrangthai());
                
                
                node2.qlQuanlykhachhang.setMakh(makh);
		node2.qlQuanlykhachhang.setHotenkh(hotenkh);
                node2.qlQuanlykhachhang.setNgaysinh(ngaysinh);
                node2.qlQuanlykhachhang.setCmnd(cmnd);
                node2.qlQuanlykhachhang.setSdt(sdt);
                node2.qlQuanlykhachhang.setGioitinh(gioitinh);
                node2.qlQuanlykhachhang.setQuequan(quequan);
                node2.qlQuanlykhachhang.setMaphong(maphong);
                node2.qlQuanlykhachhang.setNgayvao(ngayvao);
                node2.qlQuanlykhachhang.setTrangthai(trangthai);

		
	}
        /// sap xep theo ten
	public void sapxeptheotenn(){
            if(head == null){
                System.out.println("Danh sach rong khong the sap xep!!!");
            }else{
                NodeDoubleL nodeDoubleL = head;
                NodeDoubleL prDoubleL = null;
                String hotenkh;
//                int age;
//                double mark;
                while(nodeDoubleL != null){
                prDoubleL = nodeDoubleL.next;
                while(prDoubleL !=null){
                    if(nodeDoubleL.qlQuanlykhachhang.getHotenkh().compareToIgnoreCase(prDoubleL.qlQuanlykhachhang.getHotenkh())>0){
                        traodoikh(nodeDoubleL, prDoubleL);
                    }
                    prDoubleL = prDoubleL.next;
                }
                nodeDoubleL = nodeDoubleL.next;
            }
                hamHienThiListkhachhang();
            }
        }
        
        // sap xep so phong tang dan selectionsort(sap xep chon)
	public void sapxeptangdantheosophong() {
		if (head == null)
			System.out.println("Danh sach rong khong the sap xep!!!");
		NodeDoubleL currNode = head;
		while (currNode != null) {
			NodeDoubleL minNodeDoubleL = currNode;
			NodeDoubleL prevNode = currNode.next;
			while (prevNode != null) {
				if (prevNode.qlQuanlykhachhang.getMaphong() < minNodeDoubleL.qlQuanlykhachhang.getMaphong())
					minNodeDoubleL = prevNode;
				prevNode = prevNode.next;
			}
			if (minNodeDoubleL.qlQuanlykhachhang.getMaphong() != currNode.qlQuanlykhachhang.getMaphong()) {
				traodoikh(minNodeDoubleL, currNode);
			}
			currNode = currNode.next;
		}
		hamHienThiListkhachhang();
	}
}
