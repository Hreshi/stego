import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class Cipher {
	BufferedImage image;
	String message = "fa11en";
	int contentLength;

	Cipher(BufferedImage image, String message) {
		this.image = image;
		this.message = message;
		contentLength = message.length();
	}

	private int setPixelBits(int pixel, int character) {
		int LSBits[] = {0, 1, 2, 8, 9, 10, 16, 17};
		for(int i = 0;i < LSBits.length;i++) {
			boolean isCharBitZero = (character & (1 << i)) == 0;
			if (isCharBitZero) {
				if((pixel & (1 << LSBits[i])) != 0) {
					pixel -= (1 << LSBits[i]);
				}
			} else {
				pixel |= (1 << LSBits[i]);
			}
		}
		return pixel;
	}

	private void setContentLength() {
		int pixel = image.getRGB(0, 0);
		pixel = setPixelBits(pixel, contentLength);
		image.setRGB(0, 0, pixel);
	}

	private void setContent() {
		int count = -1, width = image.getWidth(), height = image.getHeight();
		for(int i = 0;i < height && count < contentLength;i++) {
			for(int j = 0;j < width && count < contentLength;j++, count++) {
				if(count != -1) {
					int pixel = image.getRGB(j, i);
					pixel = setPixelBits(pixel, message.charAt(count));
					image.setRGB(j, i, pixel);
				}
			}
		}
	}

	public void cipherImage() {
		setContentLength();
		setContent();
	}
}