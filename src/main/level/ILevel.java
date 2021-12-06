package main.level;


import main.exceptions.LoadLevelException;

public interface ILevel {

  void loadLevel(String path) throws LoadLevelException;
}
