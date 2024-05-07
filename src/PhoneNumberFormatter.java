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
        // Regex to find Russian phone numbers
        Pattern pattern = Pattern.compile("\\+7(\\d{10})");
        Matcher matcher = pattern.matcher(content);
        StringBuilder sb = new StringBuilder(); // Use StringBuilder for better performance in single-threaded context

        boolean found = false;
        while (matcher.find()) {
            found = true;
            // Format the found number to US phone number format
            String formattedNumber = "+1 (" + matcher.group(1).substring(0, 3) + ") " +
                    matcher.group(1).substring(3, 6) + "-" +
                    matcher.group(1).substring(6, 8) + "-" +
                    matcher.group(1).substring(8, 10);
            matcher.appendReplacement(sb, formattedNumber);
        }
        matcher.appendTail(sb);

        if (!found) {
            System.out.println("No valid phone numbers found to replace.");
        }
        return sb.toString();
    }
}