package CAUTRUCLUUTRU;

import QUANLYPHONGTRO.QUANLYKHACHHANG;

public class NodeDoubleL {
        public QUANLYKHACHHANG qlQuanlykhachhang;
	public NodeDoubleL next;
	public NodeDoubleL prev;

    public NodeDoubleL(QUANLYKHACHHANG qlQuanlykhachhang) {
        this.qlQuanlykhachhang = qlQuanlykhachhang;
        this.next = next;
        this.prev = prev;
    }
        public void hienthikhachhang(){
            qlQuanlykhachhang.hienthithongtinkhachhang();
        }
	
}

