package hva.core.Tree;
import java.io.Serializable;

public abstract class Tree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Season _seasonCriada;
    private int _age;
    private int _difficulty;
    private String _idTree;
    private String _nameTree;

    public Tree (Season season, int age, int difficulty,String idTree, String nameTree){
        _seasonCriada = season;
        _age = age;
        _difficulty = difficulty;
        _idTree = idTree;
        _nameTree = nameTree;

    }
    abstract public void cleaningEffort();

    public Season getSeason(){
        return _seasonCriada;
    }
    public int getAge(){
        return _age;
    }
    public int getDifficulty(){
        return _difficulty;
    }
    public String getId (){
        return _idTree;

    }

}
