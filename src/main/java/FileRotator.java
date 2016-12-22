import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileRotator {

    private final File dir;
    private final String path;
    private final String dateFormat;
    private final String prefix;
    private final String suffix;

    public FileRotator(File dir, String dateFormat, String prefix, String suffix) throws IOException {
        this.dir = dir;
        this.dateFormat = dateFormat;
        this.prefix = prefix;
        this.suffix = suffix;
        this.path = dir.getCanonicalPath();
    }

    public File getFile(LocalDateTime exportInitiated) throws IOException {
        return new File(getFilePath(exportInitiated));
    }

    public boolean removeAllExcept(File keep) throws IOException {
        boolean ok = true;

        File[] filesToDelete = dir.listFiles((d, name) -> name.matches(getPattern()) && !name.equals(keep.getName())
                && new File(path + "/" + name).isFile());

        if (filesToDelete != null) {
            for (File file : filesToDelete) {
                if (!file.delete()) {
                    ok = false;
                }
            }
        } else {
            ok = false;
        }

        return ok;
    }

    private String getFilePath(LocalDateTime timeStamp) {
        return String.format("%s/%s%s%s", path, prefix, timeStamp.format
                (DateTimeFormatter.ofPattern(dateFormat)),suffix);
    }

    private String getPattern() {
        return String.format("%s(.*)%s", prefix, suffix);
    }
}
