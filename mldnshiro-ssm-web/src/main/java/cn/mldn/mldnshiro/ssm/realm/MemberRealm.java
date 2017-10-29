package cn.mldn.mldnshiro.ssm.realm;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.mldn.mldnshiro.ssm.service.front.IMemberServiceFront;
import cn.mldn.mldnshiro.ssm.vo.Member;
public class MemberRealm extends AuthorizingRealm {
    @Resource
    private IMemberServiceFront memberService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)  {
		//此方法主要是实现用户的授权处理操作
		System.err.println("=====2.进行用户授权处理操作(doGetPrincipalCollectioin()======");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  //获取授权
		String mid = (String) principals.getPrimaryPrincipal();   //获得用户名
		Map<String,Set<String>> map = this.memberService.getRoleAndActionByMember(mid);
		info.setRoles(map.get("allRoles"));    //将所有的角色信息保存在授权信息中
		info.setStringPermissions(map.get("allActions"));   //保存所有的权限
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.err.println("=====1.进行用户认证处理操作（doGetAuthenticationInfo()）======");
		String mid = (String) token.getPrincipal();  //获得用户名
		Member member = this.memberService.get(mid); //根据用户名查出用户的完整信息
		if(member  == null) {   //用户信息不存在。抛出未知的账户异常
			throw new UnknownAccountException("账户"+mid+"不存在。");
		}
		String password  = new String((char[])token.getCredentials());   //获得密码
		if(!member.getPassword().equals(password)) {  //密码错误
			throw new IncorrectCredentialsException("错误的用户名或密码");
		}
		if(member.getLocked().equals(1)) {    //用户被锁定
			throw new LockedAccountException(mid+"账户信息已经被锁定，无法登录");
		}
		return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),"memberRealm");
	}
	
}