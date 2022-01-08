import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class App {

	public static void main(String[] args) throws Exception {
		String message = "https://drive.google.com/file/d/1_vJT7lKAMkSNIl4pnNNZwm3FiXzYaKMv/view?usp=drivesdk";
		File file = new File("cicada.png");
		BufferedImage image = ImageIO.read(file);
		
		// Cipher encode = new Cipher(image, message);
		// encode.cipherImage();

		Decipher decode = new Decipher(image);
		String res = decode.decipherImage();

		System.out.println("Go to : " + res);

		// File tar = new File("cicada.png");
		// if(ImageIO.write(image, "png", tar)) {
		// 	System.out.println("Write success");
		// }
		
	}
}


/*
Problem identified -> i am setting char into pixel but alpha is not setting at all 

11111111 01011101 01011101 01011101  -- image.getRGB()
11111100 01011110 01011100 01011100  -- setBits()
11111111 01011110 01011100 01011100  -- image.getRGB()

current solution -> store bits in rgb values and not alpha
*/