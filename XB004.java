public class XB004 {
    static int calculateScore (int likes,int saves){
        int score=likes*2+saves*3;
        return score;
    }
    public static void main(String[] args) {
        int result =  calculateScore (1,2);
        System.out.println(result);
    }
}

