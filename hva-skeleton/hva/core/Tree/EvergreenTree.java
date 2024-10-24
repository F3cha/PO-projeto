package hva.core.Tree;

public class EvergreenTree extends Tree {
    String _type;

    public EvergreenTree (Season season, int age, int difficulty,String idTree, String nameTree){
        super(season, age, difficulty, idTree, nameTree);
        _type = "PERENE";
    }

    public void cleaningEffort() {
        //meter a função
    }
    public String getType(){
        return _type;
    }
}
