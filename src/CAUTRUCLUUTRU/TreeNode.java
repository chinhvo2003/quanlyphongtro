package CAUTRUCLUUTRU;

import QUANLYPHONGTRO.QUANLYCHUYENPHONG;
import QUANLYPHONGTRO.QUANLYPHONG;
import QUANLYPHONGTRO.QUANLYTHUEPHONG;
import QUANLYPHONGTRO.QUANLYTRAPHONG;


public class TreeNode {
	
	public QUANLYPHONG qlp;
        public QUANLYCHUYENPHONG qlcp;
        public QUANLYTRAPHONG qltrp;
        public QUANLYTHUEPHONG qltp;
	public TreeNode left;
	public TreeNode right;

    public TreeNode(QUANLYPHONG qlp){
        this.qlp =qlp;
    }
    public void hienthiphong(){
        qlp.hienthithongtinphong();
    }
    public TreeNode(QUANLYTHUEPHONG qltp){
        this.qltp =qltp;
    }
    public void hienthithuephong(){
        qltp.hienthithongtinthuephong();
    }
    public TreeNode(QUANLYCHUYENPHONG qlcp){
        this.qlcp =qlcp;
    }
    public void hienthichuyenphong(){
        qlcp.hienthithongtinchuyenphong();
    }
    public TreeNode(QUANLYTRAPHONG qltrp){
        this.qltrp =qltrp;
    }
    public void hienthitraphong(){
        qltrp.hienthithongtintraphong();
    }
}
