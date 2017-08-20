package com.yangkai.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.yangkai.vo.Animal;

public class NoDefaultConstructorDemo {

	public static void main(String[] args) {
		Class<Animal> classType=Animal.class;
		try {
			//Animal animal=classType.newInstance();
			Constructor<Animal> cons=classType.getConstructor(new Class<?>[]{});
			Animal animal=cons.newInstance(new Object[]{});
			animal.setSpecies("dog");
			System.out.println(animal.getSpecies());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
