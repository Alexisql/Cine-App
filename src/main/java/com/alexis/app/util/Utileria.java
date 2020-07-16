package com.alexis.app.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {

	/**
	 * Metodo que regresa una Lista de Strings con las fechas siguientes, segun el
	 * parametro count
	 * 
	 * @param count
	 * @return
	 */
	public static List<String> getNextDays(int count) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		// Today's Date
		Date start = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, count); // Next N days from now
		Date endDate = cal.getTime();

		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(start);
		List<String> nextDays = new ArrayList<String>();
		while (!gcal.getTime().after(endDate)) {
			Date d = gcal.getTime();
			gcal.add(Calendar.DATE, 1);
			nextDays.add(sdf.format(d));
		}
		return nextDays;
	}

	public static String guardarImagen(MultipartFile multiPart, HttpServletRequest request) {
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-");
		String nombreFinal = randomAlphaNumeric() + nombreOriginal;
		String rutaFinal = request.getServletContext().getRealPath("/resources/images/");
		try {
			File imageFile = new File(rutaFinal + nombreFinal);
			multiPart.transferTo(imageFile);
			return nombreFinal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	
	public static String randomAlphaNumeric() {
		SecureRandom random = new SecureRandom();
		String text = new BigInteger(48, random).toString(32);
		return text;
	}

}