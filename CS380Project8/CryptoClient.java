import java.security.Key;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.interfaces.RSAPublicKey;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;


public class CryptoClient {
	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("45.50.5.238", 38008);
		File file = new File("public.bin");
//		FileReader reader = new FileReader(file);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		OutputStream stream = socket.getOutputStream(); // Output Stream
		InputStream reader = socket.getInputStream();

		RSAPublicKey pkey = (RSAPublicKey) in.readObject(); // Deserializes the RSA public key file
//		System.out.println(pkey.toString());
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pkey);

		Key key = KeyGenerator.getInstance("AES").generateKey();

		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(key);

		byte [] data = b.toByteArray();
		byte [] cipherText = cipher.doFinal(data);
		
		System.out.println(cipherText.length);


//============================================================================================================

//		byte [] packet = new byte [24]; // Array of bytes will be used

		int cipherTextLength = cipherText.length;
		byte [] keyPacket = new byte [28 + cipherTextLength]; // Array of bytes will be used
		keyPacket[0] = 0x45; // Version 4, HLen in groups of 4 bytes (32 bit words) which is 5
		int packetLength = keyPacket.length; // Length must be separated for byte conversion
		keyPacket[2] = (byte) (packetLength >> 8);
		keyPacket[3] = (byte) (packetLength); // Length is using bytes Hleng + data size (5 * 4) + 2 => in decimal 22
		keyPacket[6] = 0x40; // Flags not fragmented so first bit is 0, second bit is 1, and third bit is 0
		keyPacket[8] = 0x32; // TTL
		keyPacket[9] = 0x11; // Protocol
		keyPacket[12] = (byte) 0x86; // SourceAddr
		keyPacket[13] = (byte) 0x47; // SourceAddr
		keyPacket[14] = 0x16; // IP.Address 134.71.22.37
		keyPacket[15] = 0x25;
		keyPacket[16] = 0x4c; // Destination address which is 76.91.123.97 converted to bytes
		keyPacket[17] = 0x5b;
		keyPacket[18] = 0x7b;
		keyPacket[19] = 0x61;
		short temp = (checkSum(keyPacket, 20)); 
		keyPacket[10] = (byte) (temp >> 8);// Calculate checksum at the end, first half of it
		keyPacket[11] = (byte) temp; // Calculate checksum at the end, second half of it

		
		
		System.out.println(keyPacket[10] + " " +  keyPacket[11]);
		
		keyPacket[22] = (byte) (38008 >> 8); // Your response
		keyPacket[23] = (byte) 38008;
		keyPacket[24] = (byte) ((8 + cipherTextLength) >> 8);
		keyPacket[25] = (byte) (8 + cipherTextLength); 

		for(int i = 28; i < keyPacket.length; i++) {
			keyPacket[i] = cipherText[i - 28];
		}

		byte[] pheader = new byte[20 + cipherTextLength]; // Create psuedoheader for checkSum

		for (int k = 0; k < 8; k++) {
			pheader[k] = keyPacket[k + 12];
		}
		pheader[9] = keyPacket[9];
		pheader[10] = keyPacket[24];
		pheader[11] = keyPacket[25];

		for (int k = 12; k < pheader.length; k++) {
			pheader[k] = keyPacket[k + 8];
		}

		keyPacket[26] = (byte) (checkSum(pheader, 20 + cipherTextLength) >> 8);
		keyPacket[27] = (byte) checkSum(pheader, 20 + cipherTextLength);

		
		byte response [] = new byte[4]; // Array for input
		
		stream.write(keyPacket); // packet1 Send packet
		reader.read(response); // Print out response
		System.out.println(Integer.toHexString(response[0] & 0x000000ff) + // Printout response 
				Integer.toHexString(response[1] & 0x000000ff) +
				Integer.toHexString(response[2] & 0x000000ff) +
				Integer.toHexString(response[3] & 0x000000ff)); 

//=============================================================================================================

		cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		for (int i = 1; i < 11; i++) { // 12 packets will be sent
			int datalength = (int) Math.pow(2, i); // length that will be used
			byte [] packet1 = new byte [(int) (28 + datalength)]; // Array of bytes will be used
			Random random = new Random(); random.nextBytes(packet1); // Randomize the byte array
			for(int j = 0; j < 28; j++) {
				packet1[j] = 0x00; // Fill byte array with 0
			}
			packet1[0] = 0x45; // Version 4, HLen in groups of 4 bytes (32 bit words) which is 5
			int UDPlength = packet1.length; // Length must be separated for byte conversion

			packet1[2] = (byte)(UDPlength >> 8);
			packet1[3] = (byte)(UDPlength); // Length is using bytes Hleng + data size (5 * 4) + 2 => in decimal 22

			packet1[6] = 0x40; // Flags not fragmented so first bit is 0, second bit is 1, and third bit is 0
			packet1[8] = 0x32; // TTL
			packet1[9] = 0x11; // Protocol
			packet1[12] = (byte) 0x86; // SourceAddr
			packet1[13] = (byte) 0x47; // SourceAddr
			packet1[14] = 0x16; // IP.Address 134.71.22.37
			packet1[15] = 0x25;

			packet1[16] = 0x4c; // Destination address which is 76.91.123.97 converted to bytes
			packet1[17] = 0x5b;
			packet1[18] = 0x7b;
			packet1[19] = 0x61;

			packet1[10] = (byte)(checkSum(packet1, 20) >> 8); // Calculate checksum at the end, first half of it
			packet1[11] = (byte)(checkSum(packet1, 20)); // Calculate checksum at the end, second half of it

			packet1[22] = (byte) (38008 >> 8); // Your response
			packet1[23] = (byte) 38008;
			packet1[24] = (byte) ((8 + datalength) >> 8);
			packet1[25] = (byte) (8 + datalength); 


			pheader = new byte[20 + datalength]; // Create psuedoheader for checkSum

			for (int k = 0; k < 8; k++) {
				pheader[k] = packet1[k + 12];
			}
			pheader[9] = packet1[9];
			pheader[10] = packet1[24];
			pheader[11] = packet1[25];

			for (int k = 12; k < pheader.length; k++) {
				pheader[k] = packet1[k + 8];
			}

			packet1[26] = (byte) (checkSum(pheader, 20 + datalength) >> 8);
			packet1[27] = (byte) checkSum(pheader, 20 + datalength);

			response = new byte[4]; // Array for input

			cipherText = cipher.doFinal(packet1);


			stream.write(cipherText); // packet1 Send packet
			reader.read(response); // Print out response
			System.out.println(Integer.toHexString(response[0] & 0x000000ff) + // Printout response 
					Integer.toHexString(response[1] & 0x000000ff) +
					Integer.toHexString(response[2] & 0x000000ff) +
					Integer.toHexString(response[3] & 0x000000ff)); 

		}
	}


	public static short checkSum(byte [] b,int length){ //Check Sum Method
		int sum = 0; 
		int index = 0; 
		while (length > 1){ 
			int currentShort = (((int)b[index++] << 8) & 0x0000FFFF) + ((int)b[index++] & 0x00FF);
			sum += currentShort; 
			if ((sum & 0xFFFF0000) > 0){ 
				sum = sum & 0xFFFF; 
				sum ++;
			}
			length = length - 2; 
		}
		return (short)~(sum); 
	}
}
//after we find the way to encrpyt the data, copy it in as the data portion of the packet (until we know the keylength)