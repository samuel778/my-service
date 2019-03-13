package com.micro.core.config.shiro.back;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


/**
 * 自定义session规则（token获取规则）：
 * 1、从请求头中根据"jsessionid"获取session_id
 * 2、否则，从参数中根据"_jsessionid"获取session_id(该方式加"_"，不推荐采用，只有文件下载等不得不采用get方式访问时采用)
 * 3、否则，按默认规则，根据"JSESSIONID"从cookie取sessionId
 * @see org.apache.shiro.web.servlet.ShiroHttpSession#DEFAULT_SESSION_ID_NAME
 *
 * @author saml
 */
public class MySessionManager extends DefaultWebSessionManager {

	private static final String AUTHORIZATION = "jsessionid";

	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless property";

	public MySessionManager() {
		super();
	}

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
		// header没有token，从Parameter获得token
		if (StringUtils.isEmpty(id)) {
			id = WebUtils.toHttp(request).getParameter("_jsessionid");
		}
		//如果请求头中有 Authorization 则其值为sessionId
		if (!StringUtils.isEmpty(id)) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return id;
		} else {
			//否则按默认规则从cookie取sessionId,名称为：JSESSIONID
			return super.getSessionId(request, response);
		}
	}
}
