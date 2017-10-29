package cn.mldn.mldnshiro.ssm.web.action.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginActionFront {
   @RequestMapping("/login_pre")
   public String loginPre() {
	   return "login";
   }
   @RequestMapping("login")
   public String login(String mid,String password,HttpServletRequest request) {//登录处理
	   AuthenticationToken token = new UsernamePasswordToken(mid,password);
	   try {
		   SecurityUtils.getSubject().login(token);
		   return "back/welcome";
	   }catch(Exception e) {
		   request.setAttribute("eror", e.getMessage());
		   return "login";
	   }
   }
   @ResponseBody
   @RequestMapping("/unauth")
   public Object unauth() {
	   return "【Error-auth】没有此类处理权限";
   }
}
