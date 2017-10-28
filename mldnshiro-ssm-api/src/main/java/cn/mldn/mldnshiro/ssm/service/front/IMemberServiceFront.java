package cn.mldn.mldnshiro.ssm.service.front;

import java.util.Map;
import java.util.Set;

import cn.mldn.mldnshiro.ssm.vo.Member;

public interface IMemberServiceFront {
	/**
	 * 根据id获得一个用户的完整信息，包含密码和锁定状态
	 * @param mid 要查询的id
	 * @return
	 */
   public Member get(String mid);
   /**
    * 根据编号获取授权信息
    * @param mid 用户id
    * @return返回的信息包含两类数据
    * key = allRoles value = 该用户具备的所有角色
    * key = allActions value = 该用户具备的所有权限
    */
   public Map<String,Set<String>> getRoleAndActionByMember(String mid);
}
