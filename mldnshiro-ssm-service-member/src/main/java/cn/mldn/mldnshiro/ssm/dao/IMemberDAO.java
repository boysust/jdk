package cn.mldn.mldnshiro.ssm.dao;

import cn.mldn.mldnshiro.ssm.vo.Member;

public interface IMemberDAO {
	
   public Member findById(String id);
}
