import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class Decipher {
	private BufferedImage image;
	private int contentLength;

	Decipher(BufferedImage image) {
		this.image = image;
	}

	private int getCharFromPixel(int pixel) {
		int LSBits[] = {0, 1, 2, 8, 9, 10, 16, 17};
		int character = 0;
		for(int i = 0;i < LSBits.length;i++) {
			if((pixel & (1 << LSBits[i])) != 0) {
				character = (character | (1 << i));
			}
		}
		return character;
	}

	private void getContentLength() {
		int pixel = image.getRGB(0, 0);
		pixel = getCharFromPixel(pixel);
		contentLength = pixel;
	}

	private String getContent() {
		StringBuilder str = new StringBuilder(contentLength);
		int height = image.getHeight(), width = image.getWidth();
		for(int i = 0, count = -1;i < height && count < contentLength;i++) {
			for(int j = 0;j < width && count < contentLength;j++, count++) {
				if(count != -1) {
					int pixel = image.getRGB(j, i);
					pixel = getCharFromPixel(pixel);
					// System.out.println(pixel);
					str.append((char)pixel);
				}
			}
		}
		return new String(str);
	}

	public String decipherImage() {
		getContentLength();
		return getContent();
	}
}