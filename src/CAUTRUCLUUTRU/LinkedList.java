package CAUTRUCLUUTRU;

import CONTENT.ConnectJDBCC;
import java.sql.SQLException;
import java.util.Scanner;


public class LinkedList {
	int size;
	NodeLinkedList head;
	NodeLinkedList tail;
	
	public LinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	public int size() {
		return size;
	}
	
	public void insertHead(NodeLinkedList node) {
		if(head != null) {
			node.next = head;
		}
		head = node;
	}
	public void insertTail(NodeLinkedList node) {
		tail = head;
		if(head == null) {
			head = node;
		}
		else {
			while(tail.next != null) {
				tail = tail.next;
			}
			tail.next = node;
		}
	
	}
	
	public void insertAt(NodeLinkedList node, int position) {
		if(position == 0) {
			insertHead(node);
		}
		else {
			NodeLinkedList currentNode = head;
			int count = 0;
			while(currentNode != null) {
				count ++;
				if(count == position) {
					node.next = currentNode.next;
					currentNode.next = node;
					break;
				}
				currentNode = currentNode.next;
			}
		}
	}
	
	public void removeHead(NodeLinkedList node) {
		if(head != null) {
			head = head.next;
		}
		else {
			System.out.println("Danh sach rong khong the xoa !!!");
		}
	}
	
	public void removeTail(NodeLinkedList node) {
		tail = head;
		if(head == null)
			System.out.println("Danh sach rong khong the xoa !!!");
		else {
			NodeLinkedList prevNode = null;
			while(tail.next != null) {
				prevNode = tail;
				tail= tail.next;
			}
			if(prevNode == null)
				head = head.next;
			else {
				prevNode.next = tail.next;
			}
		}
	}
	
	public void removeAt(NodeLinkedList node, int position) {
		if(head == null) 
			System.out.println("Danh sach rong khong the xoa !!!");
		if(position == 0) {
			removeHead(node);
		} else {
			NodeLinkedList currentNode = head;
			NodeLinkedList prevNode = null;
			int count = 0;
			boolean bIsFound = false;
			while(currentNode != null) {
				if(count == position) {
					bIsFound = true;
					break;
				}
				prevNode = currentNode;
				currentNode = currentNode.next;
				count++;
			}
			if(bIsFound)
				if(prevNode == null)
					head = head.next;
				else
					prevNode.next = currentNode.next;
		}
	}
	
	public NodeLinkedList checktendn(String tendangnhap) {
		NodeLinkedList currNode = head;
		while(currNode != null) {
			if(currNode.qlND.getTendangnhap().equals(tendangnhap)) 
				return currNode;
			currNode = currNode.next;
		}
		return null;
	}
	
	public String checkmatkhau(String matkhau, String tendangnhap) {
		NodeLinkedList checkNode = checktendn(tendangnhap);
		if(checkNode != null) 
			if(checkNode.qlND.getMatkhau().equals(matkhau)) {
				System.out.println("Dang nhap thanh cong !!!");
				return tendangnhap;
			} 
		return null;
	}
	public NodeLinkedList searchdangnhap(String tendangnhap) {
		NodeLinkedList currNode = head;
		while(currNode != null) {
			if(currNode.qlND.getTendangnhap().equalsIgnoreCase(tendangnhap))
					return currNode;
			currNode = currNode.next;
		}
		return null;
	}
        
	public void doimatkhau(String tendangnhap) throws ClassNotFoundException, SQLException{
            ConnectJDBCC dao = new ConnectJDBCC();
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap mat khau cu: ");
            String matkhaucu = sc.nextLine();
            NodeLinkedList cuLinkedList = searchdangnhap(tendangnhap);
            if(cuLinkedList.qlND.getMatkhau().equals(matkhaucu)){
                System.out.println("Nhap mat khau moi: ");
                String matkhaumoi = sc.nextLine();
                System.out.println("Nhap lai mat khau moi: ");
                String matkhaumoi1 = sc.nextLine();
                if(matkhaumoi.equals(matkhaumoi1)){
                    cuLinkedList.qlND.setMatkhau(matkhaumoi);
                    dao.updatenguoidung(cuLinkedList.qlND, tendangnhap);
                }
            }
        }
	public void hamHienThiListQL() {
		System.out.println("--------------------------");
		NodeLinkedList currNode = head;
		while(currNode != null) {
			currNode.hienThiQuanLy();
			currNode = currNode.next;
		}
	}
}
