import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class FileRotator {

    private final File dir;
    private final String path;
    private final DateTimeFormatter dateFormat;
    private final String prefix;
    private final String suffix;

    public FileRotator(File dir, DateTimeFormatter dateFormat, String prefix, String suffix) throws IOException {
        this.dir = dir;
        this.dateFormat = dateFormat;
        this.prefix = prefix;
        this.suffix = suffix;
        this.path = dir.getCanonicalPath();
    }

    public File getFile(LocalDateTime exportInitiated) throws IOException {
        return new File(getFilePath(exportInitiated));
    }

    public boolean deleteAllExcept(File keep) {
        return deleteAll(getAllExcept(keep));
    }

    private boolean deleteAll(List<File> files) {
        return !files.stream().filter(file -> !file.delete()).findAny().isPresent();
    }

    private List<File> getAllExcept(File keep) {
        return Arrays.asList(dir.listFiles((d, name) -> name.matches(getPattern()) &&
                !name.equals(keep.getName()) &&
                new File(path + "/" + name).isFile()));
    }

    private String getFilePath(LocalDateTime timeStamp) {
        return String.format("%s/%s%s%s", path, prefix, timeStamp.format(dateFormat), suffix);
    }

    private String getPattern() {
        return String.format("%s(.*)%s", prefix, suffix);
    }
}
