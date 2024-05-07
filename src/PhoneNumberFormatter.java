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

        Pattern pattern = Pattern.compile("\\+7\\s*(-?\\s*\\d\\s*){10}");
        Matcher matcher = pattern.matcher(content);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String digits = matcher.group().replaceAll("[^\\d]", "");
            if (digits.length() == 11) {
                String formattedNumber = "+1 (" + digits.substring(1, 4) + ") " +
                        digits.substring(4, 7) + "-" +
                        digits.substring(7, 9) + "-" +
                        digits.substring(9, 11);
                matcher.appendReplacement(sb, formattedNumber);
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}