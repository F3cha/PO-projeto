package hva.core.Tree;
import java.io.Serializable;

public abstract class Tree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Season _season;
    private int _age;
    private int _difficulty;

    public Tree (Season season, int age, int difficulty){
        _season = season;
        _age = age;
        _difficulty = difficulty;

    }
    abstract public void cleaningEffort();
}
