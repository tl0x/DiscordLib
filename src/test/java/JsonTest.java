import me.tl0x.util.JsonHelper;

import java.io.IOException;

public class JsonTest {

    public static void main(String[] args) throws IOException {

        JsonHelper test = new JsonHelper(null);

        JsonHelper test2 = JsonHelper.fromString(test.toString());
        System.out.print(test2);
    }
}
