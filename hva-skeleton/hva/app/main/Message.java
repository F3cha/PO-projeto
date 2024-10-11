package hva.app.main;

public interface Message {
  static String fileNotFound() {
    return "O ficheiro não existe.";
  }

  static String openFile() {
    return "Insira o nome do ficheiro: ";
  }

  static String fileNotFound(String filename) {
    return "O ficheiro '" + filename + "' não existe.";
  }
}
