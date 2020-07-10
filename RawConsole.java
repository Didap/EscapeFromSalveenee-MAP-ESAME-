

 import java.io.IOException; 
 import java.io.ByteArrayOutputStream; 
 import java.io.InputStream;
/**
 *
 * @author w
 */
public class RawConsole {

    private static String ttyConfig;


    public static void setTerminalToCBreak() throws IOException, InterruptedException {

        ttyConfig = stty("-g");

        // la console funziona con i caratteri e non con le stringhe
        stty("-icanon min 1");

        
        stty("-echo");
    }

   
    public static String stty(final String args)
                    throws IOException, InterruptedException {
        String cmd = "stty " + args + " < /dev/tty";

        return exec(new String[] {
                    "sh",
                    "-c",
                    cmd
                });
    }

    /**
     *  Execute the specified command and return the output
     *  (both stdout and stderr).
     */
    public static String exec(final String[] cmd)
                    throws IOException, InterruptedException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        Process p = Runtime.getRuntime().exec(cmd);
        int c;
        InputStream in = p.getInputStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }

        in = p.getErrorStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }

        p.waitFor();

        String result = new String(bout.toByteArray());
        return result;
    }

		
		
		
		
	
	

}
