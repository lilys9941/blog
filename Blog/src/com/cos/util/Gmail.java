package com.cos.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("lizzy9941", "chfhd1229@");
		/*return super.getPasswordAuthentication();*/
	}
}
