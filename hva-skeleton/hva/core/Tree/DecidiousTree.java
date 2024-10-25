package hva.core.Tree;

public class DecidiousTree extends Tree {
    String _type;
    private Season _seasonAtual;


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

    public int getSeasonalCleaningEffort(){
        if (_seasonAtual == Season.Summer){
            return 2;
        } else if (_seasonAtual == Season.Autumn){
            return 5;
        } else if (_seasonAtual == Season.Winter){
            return 0;
        } else {
            return 1;
        }
    }
}


