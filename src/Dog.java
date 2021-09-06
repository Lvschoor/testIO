import java.io.Serializable;


// create basic class for the purpose of demonstrating writing and reading an object to a file -> Serializable
public class Dog implements Serializable {

    private String name;
    private boolean edible;

    public Dog(String name, boolean edible) {
        this.name = name;
        this.edible = edible;
    }

    @Override
    public String toString() {
        String edibleOrNot = edible?" ":"NOT ";
        return "My dog is named " + name + " and he is "+ edibleOrNot + "edible.";

    }
}
