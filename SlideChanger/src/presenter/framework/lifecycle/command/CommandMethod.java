package presenter.framework.lifecycle.command;

public enum CommandMethod {
    EXIT_SLIDE_SHOW('O'),
    NEXT_PAGE('N'),
    OPEN_PRESENTATION('2'),
    PREVIOUS_PAGE('P'),
    START_SLIDE_SHOW('1');

    private char symbol;

    CommandMethod(char symbol) {
        this.symbol = symbol;
    }

    public static CommandMethod determineType(String type){
        switch (type){
            case "N":return CommandMethod.NEXT_PAGE;
            case "P":return CommandMethod.PREVIOUS_PAGE;
            case "1":return CommandMethod.START_SLIDE_SHOW;
            case "2":return CommandMethod.EXIT_SLIDE_SHOW;
            case "O":return CommandMethod.OPEN_PRESENTATION;
            default:return null;
        }
    }
}
