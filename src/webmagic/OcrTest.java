package webmagic;

import java.io.File;

import com.asprise.ocr.Ocr;

public class OcrTest {

	public static void main(String[] args) {
        Ocr.setUp(); // one time setup  
        Ocr ocr = new Ocr(); // create a new OCR engine  
        ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English  
        File filepicF = new File("D:\\workspace2\\mvnTest\\test.png");
        String s = ocr.recognize(new File[] {filepicF},Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);  
        System.out.println("Result: " + s);  
        System.out.println("图片文字为:" + s.replace(",", "").replace("i", "1").replace(" ", "").replace("'", "").replace("o", "0").replace("O", "0").replace("g", "6").replace("B", "8").replace("s", "5").replace("z", "2"));  
        // ocr more images here ...  
        ocr.stopEngine();  
	}

}
