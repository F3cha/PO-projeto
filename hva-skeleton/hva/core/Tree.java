package hva.core;

abstract class Tree {
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
