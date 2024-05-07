import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberFormatter {

    public static void main(String[] args) {
        String filePath = "C:/Users/TO THANH/IdeaProjects/TranferPhoneNumberRusToUs/src/text";
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String updatedContent = replacePhoneNumbers(content);
            System.out.println(updatedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replacePhoneNumbers(String content) {
        // Regex để tìm số điện thoại Nga, bạn có thể điều chỉnh cho phù hợp
        Pattern pattern = Pattern.compile("\\+7(\\d{10})");
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String newFormat = "+1 (" + matcher.group(1).substring(0, 3) + ") " +
                    matcher.group(1).substring(3, 6) + "-" +
                    matcher.group(1).substring(6, 8) + "-" +
                    matcher.group(1).substring(8, 10);
            matcher.appendReplacement(sb, newFormat);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}