import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author randywong
 */
public class Ipv4Client {

	public static void main(String[] args) throws Exception {
		{
			try (Socket socket = new Socket("76.91.123.97", 38003)) {
				OutputStream stream = socket.getOutputStream(); // Output Stream
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Buffered Reader
				for (int i = 1; i < 13; i++) {
					byte [] packet = new byte [(int) (20 + Math.pow(2, i))]; // Array of bytes will be used

					for(int j = 0; j < 20; j++) {
						packet[i] = 0x00; // Fill byte array with 0
					}


					packet[0] = 0x45; // Version 4, HLen in groups of 4 bytes (32 bit words) which is 5
					int length = (int)Math.pow(2,i) + 20; // Length must be separated for byte conversion

					packet[2] = (byte)(length >> 8);
					packet[3] = (byte)(length); // Length is using bytes Hleng + data size (5 * 4) + 2 => in decimal 22
					
					packet[6] = 0x40; // Flags not fragmented so first bit is 0, second bit is 1, and third bit is 0
					packet[8] = 0x32; // TTL
					packet[9] = 0x06; // Protocol
					packet[12] = (byte) 0x86; // SourceAddr
					packet[13] = (byte) 0x47; // SourceAddr
					packet[14] = 0x16; // IP.Address 134.71.22.37
					packet[15] = 0x25;
					packet[16] = 0x4c; // Destination address which is 76.91.123.97 converted to bytes
					packet[17] = 0x5b;
					packet[18] = 0x7b;
					packet[19] = 0x61;

					packet[10] = (byte)(checksum(packet) >> 8); // Calculate checksum at the end, first half of it
					packet[11] = (byte)(checksum(packet)); // Calculate checksum at the end, second half of it

					stream.write(packet); // Send packet
					System.out.println("Packet: " + i + " " + br.readLine());

				}
			}
		}

	}
	public static int checksum(byte [] array) { // Checksum function

		int sum = 0x0; int cat[] = new int[10]; // Array of integers
		cat[0] = (int) ((((int) (array[0])) << 8) + (array[1] & 0xff) & 0xffff);
		cat[1] = (int) ((((int) (array[2])) << 8) + (array[3] & 0xff) & 0xffff);
		cat[2] = (int) ((((int) (array[4])) << 8) + (array[5] & 0xff) & 0xffff);
		cat[3] = (int) ((((int) (array[6])) << 8) + (array[7] & 0xff) & 0xffff);
		cat[4] = (int) ((((int) (array[8])) << 8) + (array[9] & 0xff) & 0xffff);
		cat[5] = (int) ((((int) (array[10])) << 8) + (array[11] & 0xff) & 0xffff);
		cat[6] = (int) ((((int) (array[12])) << 8) + (array[13] & 0xff) & 0xffff);
		cat[7] = (int) ((((int) (array[14])) << 8) + (array[15] & 0xff) & 0xffff);
		cat[8] = (int) ((((int) (array[16])) << 8) + (array[17] & 0xff) & 0xffff);
		cat[9] = (int) ((((int) (array[18])) << 8) + (array[19] & 0xff) & 0xffff);

		for(int i = 0; i < 10; i++) {
			sum += cat[i]; // Sum up all packets
			if ((sum & 0xffff0000) != 0) { // Check for bit overflow
				sum = sum & 0xffff;
				sum = sum + 1; 
			}
		}
		return ~(sum & 0xFFFF); // Ones compliment
	}
}