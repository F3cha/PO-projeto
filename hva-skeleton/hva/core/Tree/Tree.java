package hva.core.Tree;

import java.io.Serializable;
import java.lang.Math;

public abstract class Tree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Season _seasonAtual;
    private Season _seasonBorn;
    private int _age;
    private int _difficulty;
    private String _idTree;
    private String _nameTree;
    private String Type; // CADUCA OU PERENE, feita para as classes filhas

    public Tree(Season season, int age, int difficulty, String idTree, String nameTree) {
        _seasonAtual = season;
        _seasonBorn = season;
        _age = age;
        _difficulty = difficulty;
        _idTree = idTree;
        _nameTree = nameTree;

    }

    abstract public void cleaningEffort();

    public Season getSeason() {
        return _seasonAtual;
    }

    public int getAge() {
        return _age;
    }

    public int getDifficulty() {
        return _difficulty;
    }

    public String getId() {
        return _idTree;

    }

    public String getName() {
        return _nameTree;
    }

    public String getCicle() {
        if (_seasonAtual == Season.Summer) {
            return "COMFOLHAS";
        } else if (_seasonAtual == Season.Autumn) {
            if (this instanceof DecidiousTree) {
                return "LARGARFOLHAS";
            }
            return "COMFOLHAS";
        } else if (_seasonAtual == Season.Winter) {
            if (this instanceof DecidiousTree) {
                return "SEMFOLHAS";
            }
            return "LARGARFOLHA";
        } else
            return "GERARFOLHAS";
    }

    public String getType() {
        return Type;
    }

    abstract public int getSeasonalCleaningEffort();



    public long getCleaningEffort() {
        return Math.round((getSeasonalCleaningEffort() * _difficulty * Math.log(_age + 1)));
    }

    public void treeAdvanceSeason() {
        if (_seasonAtual == Season.Winter) {
            _seasonAtual = Season.Spring;
        } else if (_seasonAtual == Season.Spring) {
            _seasonAtual = Season.Summer;
        } else if (_seasonAtual == Season.Summer) {
            _seasonAtual = Season.Autumn;
        } else {
            _seasonAtual = Season.Winter;
        } if (_seasonAtual == _seasonBorn) {
            _age++;
        }


    }

}
