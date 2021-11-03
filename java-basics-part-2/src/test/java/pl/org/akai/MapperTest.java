package pl.org.akai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class MapperTest {

    @Test
    void mapperTest() throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            URL url = new URL("https://akai-recruitment.herokuapp.com/book");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            List<Book> books = objectMapper.readValue(reader, new TypeReference<>(){});
            System.out.println(books);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readFileOldWayTest() {
        Path path = Paths.get("src/test/resources/example.txt");
        BufferedReader reader = null;
        try{
             reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String currentLine;
            while((currentLine = reader.readLine()) != null){
                System.out.println(currentLine);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void fileBetterTest() {
        Path path = Paths.get("src/test/resources/example.txt");
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String currentLine;
            while((currentLine = reader.readLine()) != null){
                System.out.println(currentLine);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
