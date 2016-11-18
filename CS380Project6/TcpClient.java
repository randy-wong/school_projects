import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author randywong
 */
public class TcpClient {

	public static void main(String[] args) throws Exception {
		{
			try (Socket socket = new Socket("45.50.5.238", 38006)) {
				OutputStream out = socket.getOutputStream(); // Output Stream
				InputStream in = socket.getInputStream(); // InputStream Reader

				byte[] connection = new byte[40]; // Ipv4 header 20 bytes + TCP header 20
				Random random = new Random();   
				int randomsequence = random.nextInt();
				connection[0] = 0x45; // Version 4, HLen in groups of 4 bytes (32 bit words) which is 5
				int length = connection.length;

				connection[2] = (byte) (length >> 8);
				connection[3] = (byte) (length); // Length is using bytes Hleng + data size (5 * 4) + 2 => in decimal 22

				connection[6] = 0x40; // Flags not fragmented so first bit is 0, second bit is 1, and third bit is 0
				connection[8] = 0x32; // TTL
				connection[9] = 0x06; // Protocol

				connection[12] = (byte) 0x86; // SourceAddr // Doesn't matter
				connection[13] = (byte) 0x47; // SourceAddr // Doesn't matter
				connection[14] = 0x16; // IP.Address 134.71.22.37
				connection[15] = 0x25; 
				connection[16] = 0x4c; // Destination address which is 76.91.123.97 converted to bytes
				connection[17] = 0x5b;
				connection[18] = 0x7b;
				connection[19] = 0x61;

				connection[10] = (byte) (checksum(connection, 20) >> 8); // Calculate checksum at the end, first half of it
				connection[11] = (byte) (checksum(connection, 20)); // Calculate checksum at the end, second half of it // IpvHeader as checksum

				connection[24] = (byte) (randomsequence >> 24);
				connection[25] = (byte) (randomsequence >> 16);
				connection[26] = (byte) (randomsequence >> 8);
				connection[27] = (byte) randomsequence;

				connection[32] = 0x50;
				connection[33] = 0x02;

				byte psuedoheader [] = psuedoheader(connection);
				short psuedoheadersum = checksum(psuedoheader, psuedoheader.length);
				connection[36] = (byte) (psuedoheadersum >> 8);
				connection[37] = (byte) psuedoheadersum;

				out.write(connection); // Send connection

				byte response[] = new byte[4]; // Array for input
				in.read(response); //Print out response
				System.out.println("Packet: " + Integer.toHexString(response[0] & 0x000000ff)
						+ Integer.toHexString(response[1] & 0x000000ff)
						+ Integer.toHexString(response[2] & 0x000000ff)
						+ Integer.toHexString(response[3] & 0x000000ff));

				byte responsepacket [] = new byte[20];
				in.read(responsepacket);

				int responseincrement = (responsepacket[4] << 24); // Must do bit manipulation for acknowledgment
				responseincrement += (responsepacket[5] << 16 & 0x00FFFFFF);
				responseincrement += (responsepacket[6] << 8 & 0x0000FFFF);
				responseincrement += (responsepacket[7] & 0x000000FF);
				responseincrement += 1; // Increase acknowledgment + 1

				randomsequence++; // Increment Sequence Number

				connection[24] = (byte) (randomsequence >> 24);
				connection[25] = (byte) (randomsequence >> 16);
				connection[26] = (byte) (randomsequence >> 8);
				connection[27] = (byte) randomsequence++; // Increase sequence + 1
				
				connection[28] = (byte) (responseincrement >> 24);
				connection[29] = (byte) (responseincrement >> 16);
				connection[30] = (byte) (responseincrement >> 8);
				connection[31] = (byte) responseincrement;

				connection[33] = 0x12;

				connection[36] = 0;
				connection[37] = 0;

				psuedoheader = psuedoheader(connection); //
				psuedoheadersum = checksum(psuedoheader, psuedoheader.length);
				connection[36] = (byte) (psuedoheadersum >> 8);
				connection[37] = (byte) psuedoheadersum;
				out.write(connection); // Send connection

				in.read(response); //Print out response
				System.out.println("Packet: " + Integer.toHexString(response[0] & 0x000000ff)
						+ Integer.toHexString(response[1] & 0x000000ff)
						+ Integer.toHexString(response[2] & 0x000000ff)
						+ Integer.toHexString(response[3] & 0x000000ff));

				for(int j = 1; j < 13; j++) { // Step 6
					int power = (int) Math.pow(2, j);
					byte [] packet = new byte[40 + power];

					for(int k = 0; k < 20; k++) {
						packet[k] = connection[k];
					}

					packet[2] = (byte) (packet.length >> 8);
					packet[3] = (byte) packet.length;

					packet[10] = 0;
					packet[11] = 0;
					packet[10] = (byte) (checksum(packet, 20) >> 8); // Calculate checksum at the end, first half of it
					packet[11] = (byte) (checksum(packet, 20)); // Calculate checksum at the end, second half of it // Ipv4 header as checksum

					packet[24] = (byte) (randomsequence >> 24);
					packet[25] = (byte) (randomsequence >> 16);
					packet[26] = (byte) (randomsequence >> 8);
					packet[27] = (byte) randomsequence;

					packet[32] = 0x50;

					psuedoheader = psuedoheader(packet);
					psuedoheadersum = checksum(psuedoheader, psuedoheader.length);
					packet[36] = (byte) (psuedoheadersum >> 8);
					packet[37] = (byte) psuedoheadersum;

					out.write(packet); // Send connection
					in.read(response); //Print out response
					System.out.println("Packet: " + Integer.toHexString(response[0] & 0x000000FF)
							+ Integer.toHexString(response[1] & 0x000000FF)
							+ Integer.toHexString(response[2] & 0x000000FF)
							+ Integer.toHexString(response[3] & 0x000000FF));

					randomsequence += power; // Increment counter for Step 4 (add sequence + 1)
				}

				// Step 7, begin connection teardown by sending a packet with the FIN flag set
				connection[33] = 0x01;
				// Must change psuedoheader and recalculate Check Sum
				connection[36] = 0;
				connection[37] = 0;
				psuedoheader = psuedoheader(connection);
				psuedoheadersum = checksum(psuedoheader, psuedoheader.length);

				connection[36] = (byte) (psuedoheadersum >> 8);
				connection[37] = (byte) psuedoheadersum;
				out.write(connection); // Send connection

				in.read(response); // Step 8,Print out response with a 4 byte code confirming the correctness of your FIN packet.
				System.out.println("Packet: " + Integer.toHexString(response[0] & 0x000000ff)
						+ Integer.toHexString(response[1] & 0x000000ff)
						+ Integer.toHexString(response[2] & 0x000000ff)
						+ Integer.toHexString(response[3] & 0x000000ff));

				in.read(responsepacket); // Step 9
				in.read(responsepacket); // Step 10

				connection[33] = 0x10; // Step 11
				connection[36] = 0;
				connection[37] = 0;

				psuedoheader = psuedoheader(connection);
				psuedoheadersum = checksum(psuedoheader, psuedoheader.length);

				connection[36] = (byte) (psuedoheadersum >> 8);
				connection[37] = (byte) psuedoheadersum;
				out.write(connection); // Send connection

				// Step 12
				in.read(response); //Print out response
				System.out.println("Packet: " + Integer.toHexString(response[0] & 0x000000FF)
						+ Integer.toHexString(response[1] & 0x000000FF)
						+ Integer.toHexString(response[2] & 0x000000FF)
						+ Integer.toHexString(response[3] & 0x000000FF));
				in.read(responsepacket);
				in.read(responsepacket);
			}
		}
	}


	public static short checksum(byte[] b, int length) { // Check Sum Method
		int sum = 0;
		int index = 0;
		
		while (length > 1) {
			int currentShort = (((int) b[index++] << 8) & 0x0000FFFF) + ((int) b[index++] & 0x00FF);
			sum += currentShort;
			if ((sum & 0xFFFF0000) > 0) {
				sum = sum & 0xFFFF;
				sum++;
			}
			length = length - 2;
		}
		return (short) ~(sum);
	}


	public static byte[] psuedoheader(byte[] connection) { // Creates Psuedoheader for Check Sum
		byte psuedoheader [] = new byte[connection.length - 8];

		for(int i = 12; i < 20; i++) { // Copies information from byte connection to Psuedoheader
			psuedoheader[i-12] = connection[i];
		}
		
		psuedoheader[9] = connection[9];
		psuedoheader[10] = (byte) ((psuedoheader.length - 12) >> 8) ; // This field length is psuedoheader length minus 12
		psuedoheader[11] = (byte) (psuedoheader.length - 12); // This field length is psuedoheader length minus 12

		for(int i = 20; i < connection.length; i++) { // Copies information from connection to Psuedoheader
			psuedoheader[i-8] = connection[i];
		}

		return psuedoheader;
	}

}