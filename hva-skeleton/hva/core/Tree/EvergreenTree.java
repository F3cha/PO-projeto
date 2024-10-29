package hva.core.Tree;

public class EvergreenTree extends Tree {
    String _type;
    private Season _seasonAtual;


    public EvergreenTree(Season season, int age, int difficulty, String idTree, String nameTree) {
        super(season, age, difficulty, idTree, nameTree);
        _type = "PERENE";
    }


    public String getType() {
        return _type;
    }

    public int getSeasonalCleaningEffort() {
        if (_seasonAtual == Season.Summer) {
            return 1;
        } else if (_seasonAtual == Season.Autumn) {
            return 1;
        } else if (_seasonAtual == Season.Winter) {
            return 2;
        } else {
            return 1;
        }
    }
}
