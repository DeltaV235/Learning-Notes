public class ex5_6 {
    public static void main(String[] args) {
        char[] seqs = new char[30];
        for (int i = 0; i < seqs.length; i++) {
            seqs[i] = 'G';
        }
        int pos = -1, seqInt[];
        seqInt = new int[30];
        for (int count = 0; count < 15; count++) {
            int times = 0;
            while (true) {
                if (times == 13) {
                    seqs[pos] = 'R';
                    seqInt[pos] = count + 1;
                    break;
                }
                times++;
                pos++;
                if (pos == 30) {
                    pos = 0;
                }
            }
        }
        for (int i =1; i<=30; i++)
            System.out.print(i + "\t");
        System.out.println();
        for (char seq : seqs
        ) {
            System.out.print(seq + "\t");
        }
        System.out.println();
        for (int seq : seqInt
        ) {
            System.out.print(seq + "\t");
        }
        System.out.println("\nresult is :");
        for (char seq : seqs
        ) {
            System.out.print(seq + "\t");
        }
        int countRed = 0,countGreen = 0;
        for (char seq : seqs
        ) {
            if (seq == 'R')
                countRed++;
            else if (seq == 'G')
                countGreen++;
        }
        System.out.println("\nRED: " + countRed + "\nGreen: " + countGreen);
    }
}
