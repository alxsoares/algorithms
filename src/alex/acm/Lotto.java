package alex.acm;

import java.io.IOException;
import java.util.StringTokenizer;

class Lotto {
    static String ReadLn(final int maxLg) { // utility function to read from
                                            // stdin

        byte lin[] = new byte[maxLg];

        int lg = 0, car = -1;
        String line = "";

        try {

            while (lg < maxLg) {

                car = System.

                in.read();

                if ((car < 0) || (car == '\n')) {
                    break;
                }
                lin[lg++] += car;

            }

        }

        catch (IOException e) {

            return (null);
        }

        if ((car < 0) && (lg == 0)) {
            return (null); // eof
        }

        return (new String(lin, 0, lg));
    }

    public static void solve(final int x, final int s[]) {

        for (int j = 0; j < x - 5; j++)
            for (int k = j + 1; k < x - 4; k++)
                for (int l = k + 1; l < x - 3; l++)
                    for (int m = l + 1; m < x - 2; m++)
                        for (int n = m + 1; n < x - 1; n++)
                            for (int o = n + 1; o < x; o++) {
                                System.out.println(s[j] + " " + s[k] + " " + s[l] + " " + s[m] + " " + s[n] + " "
                                        + s[o]);

                            }
    }

    public static void main(final String[] args) {
        String line = "";
        int i = 0;
        while ((line = ReadLn(250)) != null) {
            if ("0".equalsIgnoreCase(line.trim()))
                break;
            if (i != 0) {
                System.out.println();
            }
            i++;
            StringTokenizer tk = new StringTokenizer(line);
            int x = Integer.parseInt(tk.nextToken().trim());
            int s[] = new int[x];
            int j = 0;
            while (tk.hasMoreTokens()) {
                s[j++] = Integer.parseInt(tk.nextToken().trim());
            }
            solve(x, s);
        }

    }

}
