package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import static java.lang.System.out;

/**
 *
 * @author allwi
 */
public class OutPuter {

    public static void vector_printer(int[] vector) {

        out.print("{");
        for (int i = 0; i < vector.length - 1; i++) {
            out.print(vector[i] + ",");
        }
        out.println(vector[vector.length - 1] + "}");
    }

    public static void text_writer(String info, String outputAddress) throws FileNotFoundException, IOException {

        FileOutputStream info_txt = new FileOutputStream(outputAddress, true);
        try (Writer writer = new OutputStreamWriter(info_txt, "utf-8")) {
            writer.write(info);
        }

    }

}
