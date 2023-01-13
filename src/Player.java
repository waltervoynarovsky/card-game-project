public class Player {
    private String name;
    private int score;

    Commands commands = new Commands();

    public Player() {
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName(){
        System.out.println("What's your name?");
        return commands.getUserName();
    }
}
