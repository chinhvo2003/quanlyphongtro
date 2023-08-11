package CAUTRUCLUUTRU;

import QUANLYPHONGTRO.QUANLYNGUOIDUNG;

public class NodeLinkedList {
	public QUANLYNGUOIDUNG qlND;
	public NodeLinkedList next;
	
	public NodeLinkedList(QUANLYNGUOIDUNG qlND) {
		this.qlND = qlND;
		this.next = null;
	}
	
	public void hienThiQuanLy() {
		qlND.hamnHIENTHITHONGTINND();
	}
}
