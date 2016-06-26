package Bean;

import java.util.ArrayList;

public class Sector_I_NameBean {
	public ArrayList<String> sectorlist=new ArrayList<String>();
	public ArrayList<String> getSectorlist() {
		return sectorlist;
	}
	public void setSectorlist(ArrayList<String> sectorlist) {
		this.sectorlist = sectorlist;
	}
	public ArrayList<String> getSector_CountOF_Ilist() {
		return sector_CountOF_Ilist;
	}
	public void setSector_CountOF_Ilist(ArrayList<String> sector_CountOF_Ilist) {
		this.sector_CountOF_Ilist = sector_CountOF_Ilist;
	}
	public ArrayList<String> getIlist() {
		return Ilist;
	}
	public void setIlist(ArrayList<String> ilist) {
		Ilist = ilist;
	}
	public ArrayList<String> sector_CountOF_Ilist=new ArrayList<String>();// 包含指令的个数
	public ArrayList<String> Ilist=new ArrayList<String>();// 指令编号列表
	public ArrayList<String> getNOlist() {
		return NOlist;
	}
	public void setNOlist(ArrayList<String> nOlist) {
		NOlist = nOlist;
	}
	public ArrayList<String> NOlist=new ArrayList<String>();// 指令的自然数 编号列表
	

}
