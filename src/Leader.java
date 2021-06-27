import java.io.Serializable;

public class Leader implements Serializable {

    String name;
    int score;

    Leader(String name, int score) {

        this.name = name;
        this.score = score;

    }

    @Override
    public String toString() { return name + ": " + score + "\n"; }
}
