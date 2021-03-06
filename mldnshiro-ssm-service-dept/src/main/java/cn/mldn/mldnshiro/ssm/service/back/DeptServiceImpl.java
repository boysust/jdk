package cn.mldn.mldnshiro.ssm.service.back;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.mldn.mldnshiro.ssm.vo.Dept;
@Service
public class DeptServiceImpl implements IDeptServiceBack{

	@Override
	public boolean add(Dept vo) {
		System.err.println("1.【DeptServiceBack】增加新部门数据："+vo);
		return true;
	}

	@Override
	public List<Dept> list() {
		System.err.println("2.【IDeptServiceBack】查询全部的数据");
		List<Dept> allDepts = new ArrayList<Dept>();
		for(int x = 0;x<10;x++) {
			Dept vo = new Dept();
			vo.setDeptno((long)x);
			vo.setDname("mldn-"+x);
			vo.setLoc("北京-"+x);
			allDepts.add(vo);
		}
		return allDepts;
	}

	@Override
	public boolean remove(long deptno) {
		System.err.println("3.【IDeptServiceBack】部门数据删除，删除的部门编号："+deptno);
		return false;
	}

}
