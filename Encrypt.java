
/*
 * Logic for Encryption Application
 */
public class Encrypt {

   //Encrypt a String with a key.
	public String encrypt(String message, String key) {
		return encrypt(message, key, false);
	}
   
   //Encrypt a String with a key. Option to remove unknown characters.
	public String encrypt(String message, String key, boolean keepChar) {
		String eMsg = "";

		key = stripString(key);
		if (keepChar == false) {
			message = stripString(message);
		}

		for (int i = 0; i < message.length(); i++) {
			if (i == key.length()){
				key += key;
			}
			eMsg += turnChar(message.charAt(i), key.charAt(i));
		}

		return eMsg;
	}

   //Decrypt a String with key
	public String solve(String message, String key) {
		String oMsg = "";
		key = stripString(key);

		for (int i = 0; i < message.length(); i++) {
			if (i == key.length()){
				key += key;
			}
			oMsg += turnBackChar(message.charAt(i), key.charAt(i));
		}
		return oMsg;
	}
   
   //Remove all characters that don't fall into approved range (32-126)
	private String stripString(String s) {
		String newMsg = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 32 && c <= 126) {
				newMsg += c;
			}
		}

		return newMsg;
	}
   
   //Add to the char by value of the provided key and wrap around if it falls out of range
	private char turnChar(char msg, char key) {
		msg += key;
		
		while (msg > 126) {
			msg -= 94;
		}
		return msg;
	}
   
   //Subtract the char by value of the provided key and wrap around if it falls out of range
	private char turnBackChar(char msg, char key) {
		int iMsg = msg;
		int iKey = key;
		iMsg -= iKey;
		
		while (iMsg < 32) {
			
			iMsg += 94;
		}
		return (char) iMsg;
	}
}
