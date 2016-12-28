# file-rotator

#### public FileRotator(File dir, DateTimeFormatter dateFormat, String prefix, String suffix)
Creates FileRotator object that will handle files in 'dir' with names that has 'prefix', 'suffix' and timestamp in 'dateFormat'.

#### public File getFile(LocalDateTime timestamp)
Returns file with given 'timestamp' (other components provided in constructor).

#### public boolean deleteAllExcept(File keep)
Deletes all files matching prefix and suffix except provided 'keep' file. Returns false if any of the delete operations return false. Otherwise returns true.
