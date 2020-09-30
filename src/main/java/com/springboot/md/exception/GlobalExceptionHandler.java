package com.springboot.md.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

/**
 * @Author 秒度
 * @Email fangxin.md@Gmail.com
 * @Date 2020/9/28 下午3:26
 * @Description 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	private static final String REQUEST = "Request";
	private static final String ERROR = "Error!";

	private static String requestToString(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		if (null != request.getRequestURL()) {
			sb.append(">>> URL: ").append(request.getRequestURL()).append("\n");
		}
		if (null != request.getRequestURI()) {
			sb.append(">>> URI: ").append(request.getRequestURI()).append("\n");
		}
		if (null != request.getMethod()) {
			sb.append(">>> Method: ").append(request.getMethod()).append("\n");
		}
		sb.append(">>> Header: ").append("\n");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			String header = request.getHeader(name);
			sb.append(">>> ").append(name).append(": ").append(header).append("\n");
		}
		return sb.toString();
	}

	private static String exToString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity apiException(HttpServletRequest request, Exception ex) {
		log.error(REQUEST + ": " + request.getRequestURI() + ERROR + "\n" + requestToString(request), ex);
		String s = exToString(ex);
		return null;
	}
}
