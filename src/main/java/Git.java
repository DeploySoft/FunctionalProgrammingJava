import java.util.List;

public class Git {
    private List<String> files;
    private String comment;
    private String branch;
    private String author;

    public Git(List<String> files, String comment, String branch, String author) {
        this.files = files;
        this.comment = comment;
        this.branch = branch;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getFiles() {
        return files;
    }

    public String getComment() {
        return comment;
    }

    public String getBranch() {
        return branch;
    }
}
