import java.util.Scanner;

public class InputCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);// ����������ɨ����
        System.out.println("请输入");// ��ʾ�û�����
        String line = scanner.nextLine();// ��ȡ�û������һ���ı�
        // ��ӡ�������ı�������
        System.out.println("你输入的内容" + line.length() + "");
    }
}
