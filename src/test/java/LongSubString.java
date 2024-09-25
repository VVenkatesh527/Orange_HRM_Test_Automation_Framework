public class LongSubString {

    public static void main(String[] args) {
        String string = "abcabcabcabc";
        int output = lonestSubString(string);
        System.out.println(output);

    }

    public static int lonestSubString(String s){
    int n = s.length();

    int res = 0;

      int[] lastIndex = new int[256];
        for(int i = 0; i < 256; i++) {
        lastIndex[i] = -1;
    }

    int start = 0;

        for (int j = 0; j < n; j++) {

        start = Math.max(start, lastIndex[s.charAt(j)] + 1);

        res = Math.max(res, j - start + 1);

        lastIndex[s.charAt(j)] = j;
    }

        return res;
}
}
