package hva.core.Tree;

public class DecidiousTree extends Tree {
    String _type;

    public DecidiousTree (Season season, int age, int difficulty,String idTree, String nameTree){
        super(season, age, difficulty, idTree, nameTree);
        _type = "CADUCA";
    }


    public void cleaningEffort() {
        //meter a função
    }

    public String getType(){
        return _type;
    }
}


