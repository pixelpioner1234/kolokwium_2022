import java.util.ArrayList;
import java.util.List;

public class AmbigiousProductException extends Exception{

    public List<String> list = new ArrayList<>();

    public AmbigiousProductException(String message, List<String> list) {
        super(message);
        this.list = list;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\nException content:\n" + String.join("\n", list);
    }


}
