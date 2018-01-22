package tools;

public class HexChange {

	public static void main(String[] args) {
		// 十进制转化为十六进制，结果为C8。

		System.out.println(Integer.toHexString(18253));

		// 十六进制转化为十进制，结果140。

		Integer.parseInt("8C",16);

	}

}
