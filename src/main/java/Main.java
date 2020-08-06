import java.util.Arrays;
import java.util.function.Function;

public class Main {

    Function<Git, Git> gitCheckOut = (git) -> {
        System.out.println("git checkout -b " + git.getBranch());
        return git;
    };

    Function<Git, Git> gitAdd = (git) -> {
        System.out.println("git add " + String.join(" ", git.getFiles()));
        return git;
    };

    Function<Git, Git> gitCommit = (git) -> {
        System.out.println(String.format("git commit -m '%s' --author=%s", git.getComment(), git.getAuthor()));
        return git;
    };
    Function<Git, Git> gitPush = (git) -> {
        System.out.println("git push");
        return git;
    };


    public static void main(String... args) {
        new Main().Start();
    }

    private void Start() {
        Git git = new Git(Arrays.asList("README.md", "Git.java"), "Initial commit", "master", "Andres");
        gitPush.compose(gitCheckOut.andThen(gitAdd).andThen(gitCommit)).apply(git);
        System.out.println("--------------");
        gitCheckOut.andThen(gitAdd).andThen(gitCommit).andThen(gitPush).apply(git);
    }

}
