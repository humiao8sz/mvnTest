package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitSQL {
	public static void main(String[] args){
		List<String> olist = new ArrayList<String>();
		olist.add("CREATE TABLE log_vc_change");
		olist.add("n_roleid INT(11) NOT NULL COMMENT '角色ID',");
		olist.add("s_atype VARCHAR(3) NOT NULL COMMENT '行为类型',");
		olist.add("n_change INT(11) NOT NULL COMMENT '变化数',");
		olist.add("n_cur INT(11) NOT NULL COMMENT '当前数量',");
		olist.add("d_create DATETIME NOT NULL COMMENT '创建时间',");
		olist.add("KEY idx_log_vc_change1 (n_roleid)");
		olist.add(") ENGINE=MYISAM DEFAULT CHARSET=utf8 COMMENT='日志_玩家银币变化表';");
		try{
			initSQLFile(olist, "vcclog.sql");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void initSQLFile(List<String> olist,String name) throws IOException{
		FileWriter fw = new FileWriter(name);
        //缓冲FileWriter  
        BufferedWriter bufWriter = new BufferedWriter(fw);  
		int year = 2019;
		int month = 1;
		while(year<=2022){
			String first = olist.get(0)+year+"_"+month+" (";
			bufWriter.write(first);
			bufWriter.newLine();
			for(int i =1;i<olist.size();i++){
				bufWriter.write(olist.get(i));
				bufWriter.newLine();				
			}
			bufWriter.newLine();
			if(month == 12){
				month = 1;
				year++;
			}else{
				month++;
			}
		}
		bufWriter.close();
		fw.close();
	}
}
