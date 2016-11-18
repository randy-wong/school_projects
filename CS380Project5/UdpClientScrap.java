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
public class UdpClientScrap {

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("76.91.123.97", 38005)) {
			OutputStream stream = socket.getOutputStream(); // Output Stream
			InputStream reader = socket.getInputStream();

			byte [] packet = new byte [24]; // Array of bytes will be used

			for(int j = 0; j < 24; j++) {
				packet[j] = 0x00; // Fill byte array with 0
			}


			packet[0] = 0x45; // Version 4, HLen in groups of 4 bytes (32 bit words) which is 5
			int length = packet.length; // Length must be separated for byte conversion

			packet[2] = (byte)(length >> 8);
			packet[3] = (byte)(length); // Length is using bytes Hleng + data size (5 * 4) + 2 => in decimal 22

			packet[6] = 0x40; // Flags not fragmented so first bit is 0, second bit is 1, and third bit is 0
			packet[8] = 0x32; // TTL000000
			packet[9] = 0x11; // Protocol
			packet[12] = (byte) 0x86; // SourceAddr
			packet[13] = (byte) 0x47; // SourceAddr
			packet[14] = 0x16; // IP.Address 134.71.22.37
			packet[15] = 0x25;
			packet[16] = 0x4c; // Destination address which is 76.91.123.97 converted to bytes
			packet[17] = 0x5b;
			packet[18] = 0x7b;
			packet[19] = 0x61;

			packet[20] = (byte) 0xde;
			packet[21] = (byte) 0xad;
			packet[22] = (byte) 0xbe;
			packet[23] = (byte) 0xef;

			packet[10] = (byte)(checkSum(packet, 20) >> 8); // Calculate checksum at the end, first half of it
			packet[11] = (byte)(checkSum(packet, 20)); // Calculate checksum at the end, second half of it
			stream.write(packet); // Send packet
			byte response1 [] = new byte[2]; // Array for input
			reader.read(response1); //Print out response
			System.out.println("Packet: " + Integer.toHexString(response1[0] & 0x000000ff) + 
					Integer.toHexString(response1[1] & 0x000000ff));


			//


			for (int i = 1; i < 13; i++) { // 12 packets will be sent
				int datalength = (int) Math.pow(2, i); 
				byte [] UDPpacket = new byte [(int) (28 + Math.pow(2, i))]; // Array of bytes will be used
				Random random = new Random(); random.nextBytes(UDPpacket); // Randomize the byte array
				for(int j = 0; j < 28; j++) {
					UDPpacket[j] = 0x00; // Fill byte array with 0
				}


				UDPpacket[0] = 0x45; // Version 4, HLen in groups of 4 bytes (32 bit words) which is 5
				int UDPlength = UDPpacket.length; // Length must be separated for byte conversion

				UDPpacket[2] = (byte)(UDPlength >> 8);
				UDPpacket[3] = (byte)(UDPlength); // Length is using bytes Hleng + data size (5 * 4) + 2 => in decimal 22

				UDPpacket[6] = 0x40; // Flags not fragmented so first bit is 0, second bit is 1, and third bit is 0
				UDPpacket[8] = 0x32; // TTL
				UDPpacket[9] = 0x11; // Protocol
				UDPpacket[12] = (byte) 0x86; // SourceAddr
				UDPpacket[13] = (byte) 0x47; // SourceAddr
				UDPpacket[14] = 0x16; // IP.Address 134.71.22.37
				UDPpacket[15] = 0x25;

				UDPpacket[16] = 0x4c; // Destination address which is 76.91.123.97 converted to bytes
				UDPpacket[17] = 0x5b;
				UDPpacket[18] = 0x7b;
				UDPpacket[19] = 0x61;

				UDPpacket[10] = (byte)(checkSum(UDPpacket, 20) >> 8); // Calculate checksum at the end, first half of it
				UDPpacket[11] = (byte)(checkSum(UDPpacket, 20)); // Calculate checksum at the end, second half of it

				UDPpacket[20] = 0x00; // SrcPort
				UDPpacket[21] = 0x00; // SrcPort
				UDPpacket[22] = response1[0]; // DstPort DEADBEEF Response
				UDPpacket[23] = response1[1]; // DstPort DEADBEEF Response
				UDPpacket[24] = (byte) (8 + (int) datalength >> 8);
				UDPpacket[25] = (byte) (8 + datalength);

				byte psuedoheaderpacket [] = new byte[(int) (20 + datalength)];
				psuedoheaderpacket[0] = (byte) 0x86; // SourceAddr
				psuedoheaderpacket[1] = (byte) 0x47; // SourceAddr
				psuedoheaderpacket[2] = 0x16; // IP.Address 134.71.22.37
				psuedoheaderpacket[3] = 0x25;
				psuedoheaderpacket[4] = 0x4c; // Destination address which is 76.91.123.97 converted to bytes
				psuedoheaderpacket[5] = 0x5b;
				psuedoheaderpacket[6] = 0x7b;
				psuedoheaderpacket[7] = 0x61;
				psuedoheaderpacket[8] = 0x00;
				psuedoheaderpacket[9] = 0x11;
				psuedoheaderpacket[10] = (byte) ((byte) (8 + (int)Math.pow(2,i)) >> 8);
				psuedoheaderpacket[11] = (byte) ((byte) (8 + (int)Math.pow(2,i)));
				
				for(int k = 20; k < UDPpacket.length; k++) {
					psuedoheaderpacket[k - 8] = UDPpacket[k];
				}
				
				
				UDPpacket[26] = (byte) ((byte) checkSum(psuedoheaderpacket, 20 + (int) datalength) >> 8);
				UDPpacket[27] = (byte) checkSum(psuedoheaderpacket, 20 + (int)datalength);

				stream.write(UDPpacket); // UDPpacket Send packet
			}
			byte response2 [] = new byte[4]; // Array for input
			reader.read(response2); //Print out response
			System.out.println(Integer.toHexString(response2[0] & 0x000000ff) + 
					Integer.toHexString(response2[1] & 0x000000ff) +
					Integer.toHexString(response2[2] & 0x000000ff) +
					Integer.toHexString(response2[3] & 0x000000ff)); 
		}


	}
    public static short checkSum(byte [] b,int headerLength){ //Check Sum Method
        int sum = 0; // Initialize sum
        int currentIndex = 0; // Initialize current Index
        while (headerLength>1){ //While a pair of bytes still exists
            // Sign extension + Merging of two consecutive bytes into a 16-bit short
            int currentShort = (((int)b[currentIndex++] << 8) & 0x0000FFFF) + ((int)b[currentIndex++] & 0x00FF);
            sum += currentShort; // Adding current short to running sum
            if ((sum & 0xFFFF0000) > 0){ // Detect Overflow
                sum = sum & 0xFFFF; // Deletes the overflown bit
                sum ++; // Adds back to the begining (completed wrap aroung)
            }
            headerLength -= 2; // Decrement header length
        }
   
        return (short)~(sum); // Return the negated value casted to short.
    }
}