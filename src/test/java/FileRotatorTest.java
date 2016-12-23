import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class FileRotatorTest {

    @Test
    public void getFile() throws Exception {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        FileRotator fileRotator = new FileRotator(new File(""), dateTimeFormatter, "", "");
        File f = fileRotator.getFile(time);
        assertTrue(f.toString().contains(time.format(dateTimeFormatter)));
    }

}
