package com.common.utils;

import java.util.Random;


public class GenerateSpaceTypesID {
	
	public static int generateSpaceTypes() {
		Random random = new Random();
		int rand = 0;
		while (true){
		    rand = random.nextInt(35);
		    if(rand !=0) break;
		}
		return rand;
	}

}
