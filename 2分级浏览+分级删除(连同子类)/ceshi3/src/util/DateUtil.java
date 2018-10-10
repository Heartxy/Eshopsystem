package util;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
* �����࣬һ�㹤������ķ������Ǿ�̬����
* @author soo
**/
public class DateUtil {
/**
* ��ȡ����ʱ��
* @return
*/
public static String getDate() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return sdf.format(date);
}
/**
* ��ȡ����
* @return
*/
public static String getDateStr() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	return sdf.format(date);
}
/**
* ��ȡʱ��
* @return
*/
public static String getTimeStr() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
	return sdf.format(date);
}
}
