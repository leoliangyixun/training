package com.yangkai.bean;

import java.io.File;
import java.util.Vector;

public class FileBean {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector showfiles(String path, String type) {
		Vector v = new Vector();
		String[] files;
		File f = new File(path);
		if (f.isDirectory()) {
			files = f.list();
			for (int i = 0; i < files.length; i++) {
				if (files[i].endsWith("." + type)) {
					v.addElement(files[i]);
				}
			}
		} else {
			v.addElement(new String(path + "��û��Ŀ¼"));
		}
		return v;
	}
}
