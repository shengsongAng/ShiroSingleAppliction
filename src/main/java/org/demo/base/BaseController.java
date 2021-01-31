package org.demo.base;

import org.apache.shiro.session.Session;
import org.demo.base.util.Const;
import org.demo.base.util.Jurisdiction;
import org.demo.base.util.UuidUtil;
import org.demo.business.entity.system.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 说明：所有处理类父类  
 * 作者：BONY
 *
 */
public class BaseController {

	/**
	 * 得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
	
	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}

	public String getCurrentUserId() {
		Session session = Jurisdiction.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		return user.getSysUser().getUserId();
	}

	public User getCurrentUser() {
		Session session = Jurisdiction.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		return user;
	}

	public String getCurrentUserName() {
		return getCurrentUser().getSysUser().getUserName();
	}
}
