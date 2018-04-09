package com.wust.shiro;

import com.wust.entity.Users;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void encryptPassword(Users user) {
		//String salt=randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, user.getPwd(),  ByteSource.Util.bytes(user.getUsername()), hashIterations).toHex();
		//String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
		user.setPwd(newPassword);

	}
	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		Users user = new Users();
		user.setUsername("wxl");
		user.setPwd("123");
		passwordHelper.encryptPassword(user);
		System.out.println(user);
	}

}
