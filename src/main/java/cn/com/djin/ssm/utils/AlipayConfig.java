package cn.com.djin.ssm.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：  ksfxhw3818@sandbox.com   111111
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101400687588";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkvF3R2bEvVNGYB7z764HFXSlafMVTuXwXApvkaLC8xkGy7T4JCo7uFnSpYwad2RCf53odGkVhOlXNdSywI/rwnoRpqFoR3O9uu5blVSuTj6F1Eqsl5nQZ6ADJrMVLlsYiIuphpKS/cQZ48CxKsQJ3v9V9c29WjF0J6PISuvayTWBZGwggcdHF1HKly/rJ3e1dfpIya1DPNZX9bLD/ICxfsGLZfCArpGlJUghrLHLbAYcMfQ3EQfbnrnFxy9u04kMeBv8HuGwxXd8MYUiULxX/4xE+eJkVHnTt90ltP2kkdwL3usNG9bP5Gp+ng8ZS0b8BMl3odwQA3rY8wS6zwemDAgMBAAECggEBAI2s/pcavLqzHKq+hPa3vVyq2YTb560wyZrUgJP4wX8olpo2VPLbpL74iw6efrNEWnrbTfc3f9G1pNNVYcB+wTMtcT2ZLxVLyJOYO5cyH48BqN8R1iV7RAB/uiW4M9nlarMzChY9zf5jSyi5ja4S+8dXMVl57JgnkWCTeZVIPCBs7pe+G9iCM+5bvXc+LB8Z0ijnTdve/9HbybSluP7HUQUw55CIk5KHcP1YIQhs8zc6uQhlIjSKchbLO0x6FX0NXLJ8Q4DpI77nuoAhxrtVlYgYZfKAJHod4s/0o5Qa/BUTwstmfm3E7/0wHJnisL/ICiGAyhMRf3R4kqOxAGFRC1ECgYEA4YAmBg5iWkqjcC6CsF6/zTRNu5GmdyHVwbFBZrN38kH9jdqAJ7MwUa1dcMSv5Rw9fWBP5L4L6v7BHBNLTX3ewwueAPVg6cwiAJbuUiZnp5Ae3HYPV+8UW0EzxqUQM4WjBaqUjSIsCcsdGfBQHwvvZJfCEqeuExCuFEaWja8Xx1kCgYEAuwREKcTtnQEq/N2jFrKU4RVCKHb4PV/fJKXZQ3igPRM4dVgm4yi23JLMWmv9iLwLn3LWbaQ7o68OYWuc9z9qwkpMqNvP9uNbrwqEDNj6Qb/bCLU3X96R1CyRkb1prXcKfm8RSGWRhbmfySeyRaUN3aVEHIaXuMkqQ6jfZAEXuDsCgYBZ5s1B/NoZRUiDoQdZaWzuUrvq29aSCFtALXr1hvd7DfQYadRN/5Jz/boTC1dU8D1TjuJLP7lgptA2cTrEWjpFaIxFtY49p7Smp4W8GrOYikzDeA4F8Lsc5pHN0GD1KYIk8CzpV4ZXITMo9DX9KrT2Vcp5xYAQ+cVtL/c15WiFgQKBgCrKMBpqf3+D6+UNhSpFVAEoDwoZJnVMgU7uT4a7i9ZNloZq1AL3ptdTE0TBHrJxbCtgaCWQBUFPZXfu2mxL605nF6EvIic4IBstycbywlfW4PPeGrxpvW4RgIEbGJni+dix6v9oleJF9YRd+9EpkcGb9bidJwvVo0D1258TDO1PAoGAZ0tntkGVT35895+PxlBNfGBkHrEgqJMNn+W0HnjueF1f4fenLEgUgV3oBvb5zgwNTsRs8uXR0UxUnkTyhJKoJFWVh1L3YHt+olrr2uXq/YQ3sAB06vxCPJbhqcXXgy7xyrupPwz9b16VkWbw+aiMfUkru0k+Cz5Efq6DrsV7KWc=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApLxd0dmxL1TRmAe8++uBxV0pWnzFU7l8FwKb5GiwvMZBsu0+CQqO7hZ0qWMGndkQn+d6HRpFYTpVzXUssCP68J6EaahaEdzvbruW5VUrk4+hdRKrJeZ0GegAyazFS5bGIiLqYaSkv3EGePAsSrECd7/VfXNvVoxdCejyErr2sk1gWRsIIHHRxdRypcv6yd3tXX6SMmtQzzWV/Wyw/yAsX7Bi2XwgK6RpSVIIayxy2wGHDH0NxEH2565xccvbtOJDHgb/B7hsMV3fDGFIlC8V/+MRPniZFR507fdJbT9pJHcC97rDRvWz+Rqfp4PGUtG/ATJd6HcEAN62PMEus8HpgwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/orders/myOrdersPay";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

