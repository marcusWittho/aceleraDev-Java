package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> fiboList = new ArrayList();

		fiboList.add(0);
		fiboList.add(1);

		int lastFibo = 0;

		for (int i = 2; lastFibo <= 350; i += 1) {
			lastFibo = fiboList.get(i - 1) + fiboList.get(i - 2);
			fiboList.add(lastFibo);
		}

		return fiboList;
	}

	public static Boolean isFibonacci(Integer a) {
		List<Integer> fiboCheck = fibonacci();

		if (fiboCheck.contains(a)) return true;

		return false;
	}

}