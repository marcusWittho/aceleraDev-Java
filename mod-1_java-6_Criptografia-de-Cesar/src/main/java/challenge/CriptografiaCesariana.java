package challenge;

public class CriptografiaCesariana implements Criptografia {

  @Override
  public String criptografar(String texto) {
    StringBuilder encryptedText = new StringBuilder();

    if (texto.isEmpty() || texto.equals(null)) throw new IllegalArgumentException("Não existe texto!!!");

    texto = texto.toLowerCase();

    for (int w = 0; w < texto.length(); ++w) {
      int j = (int) texto.charAt(w) + 3;

      if (j >= 123) j -= 26;

      if (j == 35 || (j >= 48 && j <= 60)) j -= 3;

      encryptedText.insert(encryptedText.length(), (char)j);
    }

    return encryptedText.toString();
  }

  @Override
  public String descriptografar(String texto) {
    StringBuilder decryptedText = new StringBuilder();

    if (texto.isEmpty() || texto.equals(null)) throw new IllegalArgumentException("Não existe texto!!!");

    texto = texto.toLowerCase();

    for (int w = 0; w < texto.length(); ++w) {
      int j = (int) texto.charAt(w) - 3;

      if (j <= 96 && j >= 94) j += 26;

      if (j == 29 || (j >= 45 && j <= 54)) j += 3;

      decryptedText.insert(decryptedText.length(), (char)j);
    }

    return decryptedText.toString();
  }
}
