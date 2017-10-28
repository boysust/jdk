package cn.mldn.mldnshiro.ssm.service.front;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.mldnshiro.ssm.dao.IActionDAO;
import cn.mldn.mldnshiro.ssm.dao.IMemberDAO;
import cn.mldn.mldnshiro.ssm.dao.IRoleDAO;
import cn.mldn.mldnshiro.ssm.vo.Member;
@Service
public class MemberServiceFront implements IMemberServiceFront {
    @Resource
    private IMemberDAO memberDAO;
    @Resource
    private IRoleDAO roleDAO;
    @Resource
    private IActionDAO actionDAO;
	@Override
	public Member get(String mid) {
		return this.memberDAO.findById(mid);
	}

	@Override
	public Map<String, Set<String>> getRoleAndActionByMember(String mid) {
		Map<String,Set<String>> map = new HashMap<>();
		map.put("allRoles", this.roleDAO.findAllByMember(mid));
		map.put("allActions", this.actionDAO.findAllByMember(mid));
		return map;
	}

}
