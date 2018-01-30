package util.sort;

import java.util.ArrayList;
import java.util.List;

public class BSearch {

	public static void main(String[] args) {
		List<SortVO> list = new ArrayList<SortVO>();
		list.add(new SortVO(1));
		list.add(new SortVO(6));
		list.add(new SortVO(7));
		list.add(new SortVO(8));
		list.add(new SortVO(11));
		list.add(new SortVO(12));
		list.add(new SortVO(15));
		list.add(new SortVO(67));
		list.add(new SortVO(111));
		list.add(new SortVO(145));
		list.add(new SortVO(155));
		list.add(new SortVO(199));
		list.add(new SortVO(1999));
		
		System.out.println(binarySearch(list, new SortVO(15)));
	}

	public static <T extends Comparable<T>> int binarySearch(List<T> list, T target) {
		int low = 0;
		int high = list.size() - 1;
		int mid;
		while(low <= high){
			mid = (low + high) / 2;
			if(list.get(mid).compareTo(target) == 0){
				return mid;
			}else if(list.get(mid).compareTo(target) > 0){
				low = mid + 1;
			}else{
				high = mid - 1;
			}
		}
		return -1;
	}
	
}

class SortVO implements Comparable<SortVO>{
	private int hurt;
	
	public int getHurt() {
		return hurt;
	}

	public void setHurt(int hurt) {
		this.hurt = hurt;
	}

	public SortVO(int hurt){
		this.hurt = hurt;
	}
	
	@Override
	public int compareTo(SortVO o) {
		long sub = o.getHurt() - hurt;
		if (sub > 0) {
			return 1;
		}else if(sub < 0){
			return -1;
		}else{
			return 0;
		}
	}
}