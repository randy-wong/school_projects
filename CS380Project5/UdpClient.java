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
public class UdpClient {

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("76.91.123.97", 38005)) {
			OutputStream stream = socket.getOutputStream(); // Output Stream
			InputStream reader = socket.getInputStream();

			byte [] packet = new byte [24]; // Array of bytes will be used

			for(int j = 0; j < 24; j++) {
				packet[j] = 0x00; // Fill byte array with 0
			}

//Handshaking=================================================================================================
			packet[0] = 0x45; // Version 4, HLen in groups of 4 bytes (32 bit words) which is 5
			int length = packet.length; // Length must be separated for byte conversion

			packet[2] = (byte)(length >> 8);
			packet[3] = (byte)(length); // Length is using bytes Hleng + data size (5 * 4) + 2 => in decimal 22

			packet[6] = 0x40; // Flags not fragmented so first bit is 0, second bit is 1, and third bit is 0
			packet[8] = 0x32; // TTL
			packet[9] = 0x11; // Protocol
			packet[12] = (byte) 0x86; // SourceAddr
			packet[13] = (byte) 0x47; // SourceAddr
			packet[14] = 0x16; // IP.Address 134.71.22.37
			packet[15] = 0x25;
			packet[16] = 0x4c; // Destination address which is 76.91.123.97 converted to bytes
			packet[17] = 0x5b;
			packet[18] = 0x7b;
			packet[19] = 0x61;

			packet[20] = (byte) 0xde; // handshaking
			packet[21] = (byte) 0xad; // handshaking
			packet[22] = (byte) 0xbe; // handshaking
			packet[23] = (byte) 0xef; // handshaking

			packet[10] = (byte)(checkSum(packet, 20) >> 8); // Calculate checksum at the end, first half of it
			packet[11] = (byte)(checkSum(packet, 20)); // Calculate checksum at the end, second half of it

			stream.write(packet); // Send packet
			byte response1 [] = new byte[2]; // Array for input
			reader.read(response1); //Print out response
			System.out.println("Packet: " + Integer.toHexString(response1[0] & 0x000000ff) + 
					Integer.toHexString(response1[1] & 0x000000ff));

//=============================================================================================================
			long average = 0;

			for (int i = 1; i < 13; i++) { // 12 packets will be sent
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

				packet1[22] = response1[0]; // Your response
				packet1[23] = response1[1];
				packet1[24] = (byte) ((8 + datalength) >> 8);
				packet1[25] = (byte) (8 + datalength); 

				
				byte[] pheader = new byte[20 + datalength]; // Create psuedoheader for checkSum

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

				byte response2 [] = new byte[4]; // Array for input

				long time = System.currentTimeMillis();
				stream.write(packet1); // packet1 Send packet
				reader.read(response2); //Print out response
				average += System.currentTimeMillis() - time;
				System.out.print("Time: " + (System.currentTimeMillis() - time) + " ");
				System.out.println(Integer.toHexString(response2[0] & 0x000000ff) + // Printout response 
						Integer.toHexString(response2[1] & 0x000000ff) +
						Integer.toHexString(response2[2] & 0x000000ff) +
						Integer.toHexString(response2[3] & 0x000000ff)); 

			}
			System.out.println("Average " + average/12); // Printout average time
		}


	}
    public static short checkSum(byte [] b,int length){ //Check Sum Method
        int sum = 0; 
        int index = 0; 
        while (length>1){ 
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