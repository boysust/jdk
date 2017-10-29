package cn.mldn.mldnshiro.ssm.service.back;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

import cn.mldn.mldnshiro.ssm.vo.Dept;

public interface IDeptServiceBack {
	/**
	 * 增加新部门
	 * @param vo 部门信息
	 * @return 增加成功，返回true
	 */
	@RequiresRoles("dept")
	@RequiresPermissions("dept:add")
   public boolean add(Dept vo);
   /**
    * 获取部门的全部信息
    * @return所有部门信息
    */
	@RequiresRoles("dept")
	@RequiresPermissions("dept:list")
   public List<Dept> list();
   /**
    * 根据部门编号执行部门的删除操作
    * @param deptno 部门编号
    * @return 删除成功，返回true
    */
	@RequiresRoles("dept")
	@RequiresPermissions("dept:remove")
   public boolean remove(long deptno);
}
