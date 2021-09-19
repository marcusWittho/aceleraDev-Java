package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int soma = 0;
		int media = 0;
		
		for (Integer num: elements) {
			soma += num.intValue();
		}
		
		media = soma / elements.length;

		return media;
	}

	public static int mode(int[] elements) {
		int numRepeticoes = 0;
		int moda = 0;

		for (int i = 0; i < elements.length; i += 1) {
			int count = 0;
			for (int j = 0; j < elements.length; j += 1) {
				if (elements[j] == elements[i]) count += 1;
			}

			if (count > numRepeticoes) {
				numRepeticoes = count;
				moda = elements[i];
			}
		}

		return moda;
	}

	public static int median(int[] elements) {
		int mediana = 0;

		if (elements.length % 2 != 0) {
			int indiceMediana = (int) Math.ceil(elements.length / 2);

			Arrays.sort(elements);

			mediana = elements[indiceMediana];
		} else {
			Arrays.sort(elements);

			int media1 = (elements.length / 2) - 1;
			int media2 = media1 + 1;

			mediana = (elements[media1] + elements[media2]) / 2;
		}

		return mediana;
	}
}