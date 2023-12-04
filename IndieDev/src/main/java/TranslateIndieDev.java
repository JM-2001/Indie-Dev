import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslateIndieDev {

        public static void main(String[] args) {

            List<String> filePaths = List.of(
                    "accountInfoScreen.html",
                    "adminview-profile.html",
                    "adminview-termOfService",
                    "chat.html",
                    "createProject.html",
                    "home.html",
                    "login.html",
                    "modview-profile.html",
                    "notifications.html",
                    "portfolio.html",
                    "profile.html",
                    "profile-example.html",
                    "register.html",
                    "reportsheet.html",
                    "settings.html",
                    "termsOfService.html",
                    "wecome.html"
            );


            List<String> translatedTxt = new ArrayList<>();

            for (String filePath : filePaths) {
                try {
                    Path path = Paths.get(filePath);
                    String htmlPages = Files.readString(path);

                    translatedTxt.add(htmlPages);

                    System.out.println(htmlPages);
                    System.out.println("---------------------------------");
                } catch (IOException e) {
                    System.err.println("Error reading file: " + filePath);
                    e.printStackTrace();
                }
            }

            String translateApi = "AIzaSyD1BjHnQb8a5EGEj2GK8XxIuGlF9fRmbUI";

            String targetLanguage = "es"; // Spanish

            Translate translate = TranslateOptions.newBuilder().setApiKey(translateApi).build().getService();

            Translation translation = (Translation) translate.translate(translatedTxt, Translate.TranslateOption.targetLanguage(targetLanguage));

            System.out.println("Original text: " + translatedTxt);
            System.out.println("Translated text: " + translation.getTranslatedText());


    }
}
